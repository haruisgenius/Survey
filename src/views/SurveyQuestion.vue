<script>
import { mapActions } from "pinia"
import indexStore from "../stores/indexStore";
import TableView from "../components/Table.vue"

export default {
  components: {
    TableView
  },
  data() {
    return {
      questionColumn: [
        { key: "index", column: "#" }, { key: "question", column: "題目" },
        { key: "needs", column: "必填" }, { key: "multiple", column: "單複選" }
      ],
      questionData: [],  // 問題table
      showCheckBox: true, // 是否顯示勾選框
      showWatchBtn: false, // 是否顯示觀看統計

      // input
      surveyNumber: '',
      question: '',      // 題目
      needs: '',         // 必填與否
      multiple: '',      // 單複選
      option: '',        // 選項
      index: 0,          // table自動產生編號index

      // error
      quesitonError: '',  // 題目錯誤
      multipleError: '',  // 單複選錯誤
      optionError: '',    // 選項錯誤


    }
  },
  methods: {
    // ...mapActions(indexStore, ["updateLocation"])
    addQuestion() {
      let oneQuestion = {};      // 一個問題物件
      // 題目
      if (!this.question) {
        this.quesitonError = "請輸入題目"
      } else {
        this.quesitonError = ''
      }

      // 必填
      if (this.needs == true) {
        this.needs = this.needs;
      } else {
        this.needs = false;
      }
      // console.log(this.needs)
      // 單複選
      if (!this.multiple) {
        this.multipleError = '請選擇單複選';
      } else {
        this.multipleError = ''
      }
      if (this.multiple == true) {
        this.multiple = this.multiple;
      } else {
        this.multiple = this.multiple;
      }
      // console.log(this.multiple)

      // 選項
      if (!this.option) {
        this.optionError = '請輸入選項'
      } else {
        this.optionError = ''
      }
      // 錯誤訊息
      if (this.quesitonError) {
        return this.quesitonError;
      } else if (this.optionError) {
        return this.optionError
      } else if (this.multipleError) {
        return this.multipleError
      }
      // 存進物件
      let question = {
        // 'index' : index + 1,
        'qOption' : this.option,
        'multiple' : this.multiple,
        'needs' : this.needs,
        'question' : this.question,
      }
      // 存進list和sessionStorage
      this.questionData.push(question)
      sessionStorage.setItem('questionData', JSON.stringify(this.questionData))

      // 拿index
      let nowQuestion = sessionStorage.getItem('questionData')
      console.log(nowQuestion)
      // nowQuestion = nowQuestion.map(item => {
      //   return {

      //   }
      // })

      // 重置input
      this.question = ''
      this.needs = ''
      this.multiple = ''
      this.option = ''
    },
    createSurvey() {
      let body = JSON.parse(sessionStorage.getItem('survey'));
      fetch("http://localhost:8080/create_survey", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(body)
      })
      .then(res => res.json())
      .then(data => {
        console.log(data.questionnaire.serialNumber)
        let surveyNumber = data.questionnaire.serialNumber; // 撈新增當筆問卷之流水號(res內的questionnaire)
        let questionData = JSON.parse(sessionStorage.getItem('questionData'))  // 拿回題目list, 轉成js語言(物件)
        questionData = questionData.map(item => {
          // 把問卷流水號塞進題目
          return {
            ...item, // ...表原本的東西
            'surveyNumber' : surveyNumber,
          }
        })
        console.log(questionData)
        let questionBody = {
          'questionList' : questionData
        }
        console.log(questionBody)
        fetch("http://localhost:8080/create_question", {
          method: "POST",
          headers: {
            "Content-Type" : "application/json"
          },
          body: JSON.stringify(questionBody)
        })
        .then(res => res.json())
        .then(data => {
          console.log(data)
        })
      })

      sessionStorage.removeItem('survey')
      sessionStorage.removeItem('questionData')
      window.location.href = '/manager'
    }
  },
  mounted() {
    // this.updateLocation(2)
    let questionList = sessionStorage.getItem('questionData');
    if(questionList) {
      let questionData = JSON.parse(questionList)
      console.log(questionData)
      for(let i = 0; i < questionData.length; i++) {
        let index = i + 1;
        console.log(questionData[i])
        this.question.assign()
      }
      // questionData.forEach(item =>  {
        // let index = 0;
        // console.log(item)
        // return {
        //   ...item,
        //   'index' : index + 1
        // }
      // })
      this.questionData = questionData;
    }
  }
}
</script>

<template>
  <div class="create-question-warp">
    <div class="q-input">
      <div class="question-input mb-2">
        <label for="question">題目</label>
        <input type="text" name="question" id="question" class="ms-2" v-model="question">
        <label for="question" class="error ms-2">{{ quesitonError }}</label>
      </div>

      <input type="checkbox" name="needs" id="needs" value="true" v-model="needs">
      <label for="needs">必填</label>
      <select name="multiple" id="multiple" class="ms-3" v-model="multiple">
        <!-- <option value="" disabled selected>單複選</option> -->
        <option value="false">單選</option>
        <option value="true">複選</option>
      </select>
      <label for="mutiple" class="error ms-2">{{ multipleError }}</label>

      <div class="option-area d-flex">
        <div class="option-input mt-2">
          <label for="option">選項</label>
          <input type="text" name="option" id="option" class="ms-2" v-model="option">
          <label for="option" class="ms-2">(多個選項以;做分隔)</label>
          <label for="option" class="error ms-2">{{ optionError }}</label>
        </div>
        <div class="fcBtn qAdd-btn" @click="addQuestion">新增</div>

      </div>
    </div>

    <TableView :columns="questionColumn" :showCheckBox="showCheckBox" :showWatchBtn="showWatchBtn" :data="questionData" />

    <div class="btn-area d-flex justify-content-space-between">
      <RouterLink to="/manager/create-survey" class="fcBtn qUp-btn">上一頁</RouterLink>
      <div class="fcBtn qNext-btn" @click="createSurvey">確定新增</div>
      <!-- <RouterLink to="/manager/create-survey/question" class="fcBtn qNext-btn">確定新增</RouterLink> -->
    </div>
  </div>
</template>

<style lang="scss" scoped>
.create-question-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  background-color: white;

  .error {
    color: red;
  }

  .option-area {
    justify-content: space-between;

    .qAdd-btn {
      padding: 0.2rem 1rem;
      border: solid 2px;
      border-radius: 0.5rem;
      text-decoration: none;
      color: #ff77b0;
      background-color: #FDD7EE;
      border-color: #F895BE;

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

  }

  .btn-area {
    justify-content: space-between;
    margin-top: 1rem;

    .fcBtn {
      padding: 0.2rem 1rem;
      border: solid 2px;
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

    .qUp-btn {
      color: white;
      border-color: #4b4b4b;
      background-color: #8c8c8c;
    }

    .qNext-btn {
      color: #ff77b0;
      background-color: #FDD7EE;
      border-color: #F895BE;
    }
  }
}
</style>