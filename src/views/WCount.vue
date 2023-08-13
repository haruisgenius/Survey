<script>
import DoughnutChart from '../components/Chart.vue'
export default {
  components: {
    DoughnutChart
  },
  data() {
    return {
      survey: [],  // 問卷資訊
      questions: [], // 問卷題目
      respondents: [], // 回答
      qtInChartData: [],   // 父層單題資料結構
      chartData: [],   // 圖表資料props進去的
    }
  },
  created() {
    this.findSurvey()
    this.findQuestions()
    this.findRespondents()
  },
  mounted() {
    this.createChartData()
  },
  methods: {
    // 找問卷資訊
    findSurvey() {
      let serialNumber = this.$route.params.serialNumber
      console.log("serialNumber: ", serialNumber)
      let body = {
        serialNumber : serialNumber
      }
      fetch("http://localhost:8080/find_survey", {
        method: "POST",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          // console.log(data)
          this.survey = data.questionnaire
          console.log("findSurvey", this.survey)
        })
    },
    // 找問卷題目
    findQuestions() {
      let surveyNumber = this.$route.params.serialNumber
      let body = {
        surveyNumber : surveyNumber
      }
      fetch("http://localhost:8080/find_survey_question", {
        method: "POST",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          this.questions = data.questionList
          console.log("findQuestion", this.questions)
        })
    },
    // 找問卷回答
    findRespondents() {
      let surveyNumber = this.$route.params.serialNumber
      let body = {
        surveyNumber : surveyNumber
      }
      fetch("http://localhost:8080/find_respondent", {
        method: "POST",
        headers: {
          "Content-Type" : "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          this.respondents = data.respondentList
          console.log("findRespondents: ", this.respondents)
        })
    },
    //創造chartData資料
    createChartData() {
      let resLength = this.respondents.length
        console.log("resLength:", resLength)
      let resAnsAry = []
      for( let i = 0; i < resLength; i++) {
        let resAns = this.respondents[i].answer
        resAnsAry.push(resAns)
      }
      console.log("resAnsAry:", resAnsAry)

      // forEach : 創各題全部答案 = allDenoAry
      // 生成(題目數)長度的陣列
      // let allDenoAry = new Array(this.questions.length).fill([])
      let allDenoAry = []
      resAnsAry.forEach((resData, resIndex) => {
        // console.log("resIndex:",resIndex, "resData:" , resData.split(";"))
        let resDataAry = resData.split(";")  // 單回答的各題答案陣列
        // console.log("resDataAry", resDataAry)

        // 將每個作答的答案拆成各題
        let oneQAnsAry = []
        for(let i = 0; i < resDataAry.length; i++) {
          // console.log("allDenoAry", i, allDenoAry[i])
          oneQAnsAry.push(resDataAry[i].split(","))
        }
        // console.log("oneQAnsAry", oneQAnsAry)
        
        // 將各題答案加入各題陣列
        for(let i = 0; i < oneQAnsAry.length; i++) {
          if(!allDenoAry[i]) {  // 若allDenoAry無此題答案群組 宣告一個空陣列存所有回答之該題總答案
            allDenoAry[i] = []
          }
          // console.log("allDenoAry[]", allDenoAry[i].push(i))
          for(let j = 0; j < oneQAnsAry[i].length; j++){
            let ans = oneQAnsAry[i][j]
            // console.log(i, j, ans)
            allDenoAry[i].push(ans)
          }
          // console.log(allDenoAry[i])
          // console.log("---")
          // 
        }
        
      })
      console.log("allDenoAry:", allDenoAry)

      // 計算各選項被選次數
      // 用findQuestion()的結果 > 生成一個問題結構
      this.questions.forEach((findQt) => {
        let oneQuestion = {
          question: findQt.question,
          totalAnswers: 0,
          options: []
        }
        this.qtInChartData.push(oneQuestion)
      })
      // console.log("this.qtInChartData:", this.qtInChartData) // props資料初始結構

      // 塞options
      for(let i = 0; i < this.qtInChartData.length; i++) {
        console.log(this.qtInChartData[i].options)
        for(let j = 0; j < allDenoAry[i].length; j++) {
          let labelName = allDenoAry[i][j]
          // console.log(labelName)
          let findLabel = this.qtInChartData[i].options.some(option => option.label == labelName)
          if(!findLabel) {
            let oneOp = {
              label: labelName,
              value: 0
            }
            this.qtInChartData[i].options.push(oneOp)
          }
          // 算該選項被選次數
          this.qtInChartData[i].options.find(option => {
            if(option.label == labelName) {
              option.value += 1
            }
          })
        }
        }
        
        // 算totalAnswers
        this.qtInChartData.forEach((data) => {
          data.totalAnswers = data.options.reduce((total, option) => total + option.value, 0)
        })
        
        this.chartData = {
          resTotal: this.respondents.length,
          questions: this.qtInChartData
        }
        
        console.log("this.chartData", this.chartData)
        // return this.chartData
    }
  }
}
</script>

<template>
  <div class="count-warp">
    <div class="back-btn">
      <RouterLink to="/" class="cancelBtn">返回</RouterLink>
      <button type="button" class="btn btn-success" @click="createChartData()">console.log</button>
    </div>
    <h2 class="mt-1 ms-4">{{ this.survey.surveyName }}</h2>
  </div>
  <div class="chart-div">
    <DoughnutChart :chartData="chartData" />
  </div>
</template>

<style lang="scss" scoped>

.count-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  // padding-top: 4.5rem;
  background-color: white;

  .back-btn {
    // margin-bottom: 1rem;

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
  }

  
}
  
</style>