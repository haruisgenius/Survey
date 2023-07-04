<script>
import { mapActions } from "pinia"
import indexStore from "../stores/indexStore"
import { RouterLink } from "vue-router"
export default {
  data() {
    return {
      title: '',     // 問卷標題
      caption: '',   // 說明
      startDate: '', // 開始日期
      endDate: '',    // 結束日期
      titleError: '',  // 標題錯誤訊息
      dateError: '',   // 結束時間錯誤標題
    }
  },
  methods: {
    // ...mapActions(indexStore, ["updateLocation"])
    addSurvey() {
      // 問卷名稱
      if (!this.title) {
        this.titleError = "請輸入問卷名稱"
      }else if(this.title) {
        this.titleError = ''
      }
      // 開始時間
      if (!this.startDate) {
        const now = new Date();
        this.startDate = now;
      }else if(this.startDate) {
        this.startDate = new Date(this.startDate);
        // this.startDate.setDate();
      }

      // 結束時間
      if (!this.endDate) {
        //                 整串日期
        this.endDate = new Date();       // 取得日期
        this.endDate.setDate(this.startDate.getDate() + 7);
      }else if(this.endDate) {
        this.endDate = new Date(this.endDate);
      } // 時間判斷
      if (this.startDate > this.endDate) {
        this.dateError = "請輸入正確時間"
        console.log(this.dateError)
      }else {
        this.dateError = ''
      }
      if(this.titleError) {
        return this.titleError;
      }else if(this.dateError) {
        return this.dateError;
      }
      let survey = { 
        'surveyName': this.title,
        'surveyCaption': this.caption,
        'startDate': this.startDate.toISOString().split('T')[0],  // 修改日期格式
        'endDate': this.endDate.toISOString().split('T')[0]
      };


      sessionStorage.setItem('survey', JSON.stringify(survey))
      window.location.href = '/manager/create-survey/question'
    },
    cancelCreate() {
      sessionStorage.removeItem('survey')
      sessionStorage.removeItem('questionData')
      window.location.href = '/manager'
    }
  },
  watch: {

  },
  computed: {
    showSession() {
    },
  },
  mounted() {
    // this.updateLocation(1)
    // if(survey) {
      let survey = sessionStorage.getItem('survey');   // 回傳之資料為字符串
      if(survey) {
        survey = JSON.parse(survey);    // 將字符串轉為js屬性
        console.log(survey)
        // console.log(survey.surveyName)
        this.title = survey.surveyName
        this.caption = survey.surveyCaption
        this.startDate = survey.startDate
        this.endDate = survey.endDate

      }

    // }
    
  }
}

</script>

<template>
  <!-- <RouterLink>資訊</RouterLink> -->
  <div class="create-sv-warp">

    <div class="title-area w-100 mb-2">
      <label for="surveyTitle">問卷標題</label>
      <input class="ms-3" type="text" name="surveyTitle" id="surveyTitle" v-model="title">
      <label class="error ms-2">{{ titleError }}</label>
    </div>
    <div class="caption-area w-100 mb-2">
      <label for="surveyCaption">問卷說明</label>
      <textarea class="caption-input ms-3" name="surveyCaption" id="surveyCaption" rows="5" cols="9"
        v-model="caption"></textarea>

    </div>
    <div class="startdate-area w-100 mb-2">
      <label>開始時間</label>
      <input class="ms-3" type="date" name="startDate" id="startDate" v-model="startDate">
    </div>
    <div class="enddate-area w-100">
      <label>結束時間</label>
      <input class="ms-3" type="date" name="endDate" id="endDate" v-model="endDate">
      <label class="error ms-2">{{ dateError }}</label>
    </div>

    <div class="btn-area d-flex justify-content-space-between">
      <!-- <RouterLink to="/manager" class="fcBtn cancel-btn">取消</RouterLink> -->
      <div class="fcBtn cancel-btn" @click="cancelCreate">取消</div>
      <div class="fcBtn next-btn" @click="addSurvey">下一頁</div>
      <!-- <RouterLink to="/manager/create-survey/question" class="fcBtn></RouterLink> -->
    </div>

  </div>
</template>

<style lang="scss" scoped>
.create-sv-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  background-color: white;


  .caption-area {
    display: flex;
    justify-content: start;

    .caption-input {
      width: 70%;
      // height: 15rem;
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

    .cancel-btn {
      color: white;
      border-color: #4b4b4b;
      background-color: #8c8c8c;
    }

    .next-btn {
      color: #ff77b0;
      background-color: #FDD7EE;
      border-color: #F895BE;
    }


  }

  .error {
    color: red;
  }

}
</style>