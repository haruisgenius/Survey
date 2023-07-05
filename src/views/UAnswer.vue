<script>
export default {
  props: {
    // survey: [],
    // question: []
  },
  data() {
    return {
      path: '',  // 網址
      survey: [],
      question: [],
      answer: [],
      // 錯誤訊息
      nameError: '',
      ageError: '',
      phoneError: '',
      emailError: '',

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
        serialNumber : serialNumber
      }
      // 找問卷資訊
      fetch("http://localhost:8080/find_survey", {
        method: "POST",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify(body)
      })
      .then(res => res.json())
      .then(data => {
        this.survey = data.questionnaire
        console.log(this.survey)
      })
      let qBody = {
        surveyNumber : serialNumber
      }
      // 找問卷題目
      fetch("http://localhost:8080/find_survey_question", {
        method: "POST",
        headers: {
          "Content-Type" : "application/json"
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
    
  }



}
</script>

<template>
  <div class="answer-warp">
    <RouterLink to="/" class="cancelBtn">取消</RouterLink>
    
    <!-- 滾動條區域 -->
    <div style="overflow-y:scroll" class="question-area mt-2">
      
      <!-- 問卷資料 -->
      <div class="survey-info">
        <h2 class="mb-0 ms-4">{{ this.survey.surveyName }}</h2>
        <p class="surveyCaption p-3">{{ this.survey.surveyCaption }}</p>
        
      </div>

      <!-- 回答者資料 -->
      <div class="info-input-area">
        <div class="input-up d-flex justify-content-between">
          <div class="name-area">
            <label for="nameInput">姓名：</label>
            <input type="text" name="nameInput" id="nameInput" class="input">
            <p for="nameInput" class="error text-end">{{ nameError }}</p>
          </div>
          <div class="age-area">
            <label for="ageInput">年齡：</label>
            <input type="number" name="ageInput" id="ageInput" class="input">
            <p for="ageInput" class="error text-end">{{ ageError }}</p>
          </div>
        </div>
  
        <div class="input-down d-flex justify-content-between">
          <div class="phone-area">
            <label for="phoneInput">電話：</label>
            <input type="text" name="phoneInput" id="phoneInput" class="input">
            <p for="phoneInput" class="error text-end">{{ phoneError }}</p>
          </div>
          <div class="email-area">
            <label for="emailInput">信箱：</label>
            <input type="email" name="emailInput" id="emailInput" class="input">
            <p for="emailInput" class="error text-end">{{ emailError }}</p>
          </div>
        </div>
      </div>
      
      
      <!-- 題目 -->
      <div class="one-question" v-for="(question, index) in this.question">

        <div class="question mt-2">
          <p class="mb-1">第 {{ index + 1 }} 題</p>
            <span v-if="question.needs" class="hint-text">(此題必填)</span>
          <p class="mb-1">{{ question.question }}</p>
            <span v-if="question.multiple" class="hint-text">(複選)</span>
            <span v-if="!question.multiple" class="hint-text">(單選)</span>
        </div>
        <div class="option" v-for="option in question.qOption.split(';')">
          <input type="checkbox" name="" id="" v-if="question.multiple">
          <input type="radio" name="" id="" v-if="!question.multiple">
          <label>{{ option }}</label>
        </div>

      </div>
    </div>
    <div class="d-flex justify-content-end"><div class="btn qNext-btn" @click="createAnswer">提交</div></div>
    
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

}
</style>