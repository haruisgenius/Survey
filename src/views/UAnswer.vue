<script>
export default {
  props: {

  },
  data() {
    return {
      path: '',  // 網址
      survey: [],
      question: [],
      checkAnswer: [], // 複選之單題答案
      checkA: {},    // 複選題答案物件
      oneChoseA: {},
      oneAns: [],  // 單選
      // 錯誤訊息
      nameError: '',
      ageError: '',
      phoneError: '',
      emailError: '',
      needsError: '',   // 必填錯誤訊息
      // 存答案
      test: ',',
      space: '',
      // 回答方法
      resName: '',
      resAge: '',
      resPhone: '',
      resEmail: '',

      isReadOnly: false,  // 答案唯讀
      createAnsBtnShow: false,  // 顯示提交答案按鈕
      inputReadOnlyShow: true,  // 顯示檢查答案按鈕
      updateAnsBtn: false,  // 顯示返回修改答案按鈕


    }
  },
  mounted() {
    this.findSurvey()
    // this.findSurveyAndQuestion()
  },
  methods: {
    findSurvey() {
      // 抓網址內的serial number
      let serialNumber = this.$route.params.serialNumber
      console.log(serialNumber)
      let body = {
        serialNumber: serialNumber
      }
      // 找問卷資訊
      fetch("http://localhost:8080/find_survey", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          this.survey = data.questionnaire
          console.log(this.survey)
        })
      let qBody = {
        surveyNumber: serialNumber
      }
      // 找問卷題目
      fetch("http://localhost:8080/find_survey_question", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(qBody)
      })
        .then(res => res.json())
        .then(data => {
          this.question = data.questionList
          console.log(this.question)
          this.question.qOption
        })
    },
    createAnswer() {
      const saveAns = this.makeInputReadOnly();

      let body = {
        "respondentName": this.resName,
        "respondentAge": this.resAge,
        "respondentPhone": this.resPhone,
        "respondentEmail": this.resEmail,
        "surveyNumber": parseInt(this.$route.params.serialNumber),
        "answer": saveAns
      }

      console.log(body)
      fetch("http://localhost:8080/create_respondent", {
        method: "POST",
        headers: {
          // "Content-Type": "application/json; charset=utf-8"
          "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          console.log(data)
        })
      setTimeout(function () {
        // sessionStorage.removeItem('survey')
        // sessionStorage.removeItem('questionData')
        window.location.href = '/';
      }, 500);

    },
    inputReadOnly() {
      this.makeInputReadOnly()
    },
    // 檢查
    makeInputReadOnly() {
      this.inputReadOnlyShow = false;  // 檢查答案按鈕 隱藏
      this.createAnsBtnShow = true;   // 提交答案按鈕 顯示
      this.updateAnsBtn = true;
      this.isReadOnly = true;      // 唯讀
      // this.updateAnsBtn = true;       
      //正則表達式
      const phonePtn2 = /(\d{2,3}-?|\(\d{2,3}\))\d{3,4}-?\d{4}/; // 使用正则表达式匹配数字
      const phonePtn3 = /09\d{2}(\d{6}|-\d{3}-\d{3})/; // 手機
      const emailPtn = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 電子信箱
      // this.isValid = pattern.test(this.inputValue);
      if (!this.resName) {
        this.nameError = '請輸入名字'
      } else { this.nameError = '' }
      if (!this.resAge || this.resAge <= 0) {
        this.ageError = '請輸入有效年齡'
      } else { this.ageError = '' }
      if (!this.resPhone) {
        this.phoneError = '請輸入電話號碼'    // 電話號碼正規
      } else if (!this.resPhone.match(phonePtn2) || !this.resPhone.match(phonePtn3)) {
        this.phoneError = '請輸入有效電話號碼'
      } else { this.phoneError = '' }
      if (!this.resEmail) {
        this.emailError = '請輸入電子信箱'
      } else if (!this.resEmail.match(emailPtn)) {  // 電子信箱正規
        this.emailError = '請輸入有效電子信箱'
      } else { this.emailError = '' }
      if (this.nameError) {
        this.createAnsBtnShow = false    // 提交作答按鈕 隱藏
        return this.nameError
      } else if (this.ageError) {
        this.createAnsBtnShow = false    // 提交作答按鈕 隱藏
        return this.ageError
      } else if (this.phoneError) {
        this.createAnsBtnShow = false    // 提交作答按鈕 隱藏
        return this.phoneError
      } else if (this.emailError) {
        this.createAnsBtnShow = false    // 提交作答按鈕 隱藏
        return this.emailError
      }

      // console.log(this.oneChoseA[13])  // this.oneChoseA.13; 若13為字串，這裡會導致語法錯誤，因為key是一個數字
      // 接答案
      let ansAry = {}
      // 處理複選答案
      for (let ans of this.checkAnswer) { // foreach答案陣列
        const [answer, qNumber] = ans.split(",") // 切答案字串
        if (!ansAry[qNumber]) { // 若ansAry物件內無此題目流水號
          ansAry[qNumber] = []   // 宣告一個此題目流水號空間
        }
        ansAry[qNumber].push(answer)  // 答案加進對應題目流水號空間
      }
      // console.log(ansAry)

      // 處理單選答案
      // 物件轉成陣列方法(會失去key值)
      Object.values(this.oneChoseA).forEach((item) => {
        const [answer, qNumber] = item.split(",")
        console.log(item)
        if (!ansAry[qNumber]) {
          ansAry[qNumber] = []
        }
        ansAry[qNumber].push(answer)
      })

      console.log(ansAry)

      console.log(typeof ansAry)

      // console.log()
       // 檢查必填
        for (let i = 0; i < this.question.length; i++) {
          let qNum = this.question[i].questionNumber
          // console.log(this.question[i].needs)
          // console.log(qNum)
          // console.log(typeof ansAry[qNum])
          if(this.question[i].needs == true && ansAry[qNum] == undefined) {
            console.log(this.question[i].needs);
            console.log(ansAry[qNum]);
            this.needsError = `有${this.question[i].question}必填項目未填`
            // console.log(this.question[i], "錯誤")
          }else {
            this.needsError = ``
          }
          
          if(this.needsError) {
            this.createAnsBtnShow = false    // 提交作答按鈕 隱藏
            return this.needsError;
          }
        }

      // 檢查若無作答則存入空字串
      for (let i = 0; i < this.question.length; i++) {
        const questionNum = this.question[i].questionNumber.toString()
        if (!ansAry[questionNum]) {
          ansAry[questionNum] = []
          ansAry[questionNum].push(this.space)
        }
      }
      console.log(ansAry)
      let ansString = ''
      const ansValues = Object.values(ansAry)
      ansValues.forEach((item, index) => {
        ansString += item.toString()
        if (index < ansValues.length - 1) {
            ansString += ';'
          }

        // for (let i = 0; i < item.length; i++) {
        //   ansString += item[i]
        //   if (i < item.length - 1) {
        //     ansString += ','
        //   }
        // }
        // ansString += ';'
      })
      // console.log(typeof ansString === 'string')

      console.log(ansString)
      return ansString;
    },
    updateAnswer() {
      this.inputReadOnlyShow = true;    // 檢查 顯示
      this.createAnsBtnShow = false;    // 提交 隱藏
      this.updateAnsBtn = false;        // 修改 隱藏
      this.isReadOnly = false;          // 唯讀取消
    }

  },
  watch: {

  },
  computed: {

  }



}
</script>

<template>
  <div class="answer-warp">
    <RouterLink to="/" class="cancelBtn">取消</RouterLink>
    <!-- <span>{{ checkAnswer }}</span>
    <span>{{ oneChoseA }}</span> -->

    <!-- 滾動條區域 -->
    <div style="overflow-y:scroll" class="question-area mt-2">

      <!-- 問卷資料 -->
      <div class="survey-info">
        <span class="mb-0 ms-4 fs-2">{{ this.survey.surveyName }}</span><span class="error">{{ needsError }}</span>
        <p class="surveyCaption p-3">{{ this.survey.surveyCaption }}</p>

      </div>

      <!-- 回答者資料 -->
      <div class="info-input-area">
        <div class="input-up d-flex justify-content-between">
          <div class="name-area">
            <label for="nameInput">姓名：</label>
            <input type="text" name="nameInput" id="nameInput" class="input" placeholder="請輸入姓名" v-model="resName"
              :disabled="isReadOnly">
            <p for="nameInput" class="error text-end">{{ nameError }}</p>
          </div>
          <div class="age-area">
            <label for="ageInput">年齡：</label>
            <input type="number" name="ageInput" id="ageInput" class="input" placeholder="請輸入年齡" v-model="resAge"
              :disabled="isReadOnly">
            <p for="ageInput" class="error text-end">{{ ageError }}</p>
          </div>
        </div>

        <div class="input-down d-flex justify-content-between">
          <div class="phone-area">
            <label for="phoneInput">電話：</label>
            <input type="text" name="phoneInput" id="phoneInput" class="input" placeholder="請輸入電話號碼" v-model="resPhone"
              :disabled="isReadOnly">
            <p for="phoneInput" class="error text-end">{{ phoneError }}</p>
          </div>
          <div class="email-area">
            <label for="emailInput">信箱：</label>
            <input type="email" name="emailInput" id="emailInput" class="input" placeholder="請輸入電子信箱" v-model="resEmail"
              :disabled="isReadOnly">
            <p for="emailInput" class="error text-end">{{ emailError }}</p>
          </div>
        </div>
      </div>


      <!-- 題目 -->
      <div class="one-question" v-for="(question, index) in this.question">

        <div class="question mt-2">
          <p>----------------------------</p>
          <span class="mb-1">第 {{ index + 1 }} 題</span>
          <span v-if="question.needs" class="hint-text error">（此題必填）</span>
          <div class="que">
            <span class="mb-1">{{ question.question }}</span>
            <span v-if="question.multiple" class="hint-text">（複選）</span>
            <span v-if="!question.multiple" class="hint-text">（單選）</span>
          </div>
        </div>
        <!-- 複選 -->
        <div class="option" v-if="question.multiple" v-for="(option, index) in question.qOption.split(';')">
          <input type="checkbox" name="" :id="option + question.questionNumber"
            :value="option + test + question.questionNumber" v-model="checkAnswer" :disabled="isReadOnly">
          <label :for="option + question.questionNumber">{{ option }}</label>
        </div>
        <!-- 單選 -->
        <select @change="putAns" name="" id="" v-if="!question.multiple"
          v-model="oneChoseA[question.questionNumber.toString()]" :disabled="isReadOnly">
          <option value="" v-if="question.needs" disabled>請選擇</option>
          <option :value="space + test + question.questionNumber" v-else>請選擇</option>
          <option :value="option + test + question.questionNumber" v-for="(option, index) in question.qOption.split(';')">
            {{
              option }}</option>
        </select>

      </div>
    </div>
    <div class="d-flex justify-content-end" v-if="createAnsBtnShow || updateAnsBtn">
      <div class="btn cancelBtn" @click="updateAnswer" v-if="updateAnsBtn">修改</div>
      <div class="btn qNext-btn" @click="createAnswer" v-if="createAnsBtnShow">提交</div>
    </div>
    <div class="d-flex justify-content-end" v-if="inputReadOnlyShow">
      <div class="btn qNext-btn" @click="inputReadOnly">確定</div>
    </div>

  </div>
</template>

<style lang="scss" scoped>
.answer-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  background-color: white;

  .cancelBtn {
    padding: 0.2rem 1rem;
    border: solid 2px;
    border-radius: 0.5rem;
    text-decoration: none;
    color: white;
    border-color: #4b4b4b;
    background-color: #8c8c8c;

    transition: 0.3s;

    &:hover {
      cursor: pointer;
      scale: 1.05;
    }

    // 點擊時效果
    &:active {
      scale: 0.95;
    }

  }

  .error {
    color: red;
  }

  .input {
    width: 15rem;
  }

  .question-area {
    width: 100%;
    height: 85%;

    .hint-text {
      size: 15px;
    }
  }

  .qNext-btn {
    color: #ff77b0;
    background-color: #FDD7EE;
    padding: 0.2rem 1rem;
    border: solid 2px #F895BE;
    border-radius: 0.5rem;
    text-decoration: none;

    transition: 0.3s;

    &:hover {
      cursor: pointer;
      scale: 1.05;
    }

    // 點擊時效果
    &:active {
      scale: 0.95;
    }
  }

}</style>