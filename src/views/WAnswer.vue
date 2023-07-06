<script>
import TableView from '../components/Table.vue'
export default {
  components: {
    TableView
  },
  data() {
    return {
      resColumn: [
        { key: "respondentName", column: "姓名" },
        { key: "answerTime", column: "回答時間" }],
      resData: [], // 表格內容
      survey: [], // 問卷內容
      // 是否顯示勾選框
      showCheckBox: false,
      showResBox: false,   // 作答
      showWatchAnsBtn: true,  // 觀看詳細作答
      showWatchBtn: false,   // 是否顯示觀看統計
      isCQuestion: true,   // manager題目流水號
    }
  },
  mounted() {
    this.findAnswer()
  },
  methods: {
    findAnswer() {
      let surveyNumber = this.$route.params.serialNumber
      let body = {
        surveyNumber: surveyNumber
      }
      // 找問卷資訊
      let surveyBody = {
        serialNumber: surveyNumber
      }
      fetch("http://localhost:8080/find_survey", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(surveyBody)
      })
        .then(res => res.json())
        .then(data => {
          this.survey = data.questionnaire
          console.log(this.survey)
        })
      // 找全部答案
      fetch("http://localhost:8080/find_respondent", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
      })
        .then(res => res.json())
        .then(data => {
          console.log(data.respondentList)
          data.respondentList.forEach((data) => {
            data.answerTime = data.answerTime.split("T")[0] + "  " + data.answerTime.split("T")[1]
          })
          // this.resData.answerTime = this.resData.answerTime.split("T")[0]
          this.resData = data.respondentList
        })
    }
  }
}
</script>

<template>
  <div class="w-result-warp">
    <div class="back-btn">
      <RouterLink to="/" class="cancelBtn">返回</RouterLink>
      <h2 class="mt-1">{{ this.survey.surveyName }}</h2>
    </div>
    <TableView :columns="resColumn" :showCheckBox="showCheckBox" :showWatchBtn="showWatchBtn" :data="this.resData"
      :showResBox="showResBox" :isCQuestion="isCQuestion" :showWatchAnsBtn="showWatchAnsBtn" />
  </div>
</template>

<style lang="scss" scoped>
.w-result-warp {
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