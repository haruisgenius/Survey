<script>
import TableView from "../components/Table.vue";
import { RouterLink } from "vue-router"
export default {
  components: {
    TableView,
  },
  data() {
    return {
      surveyColumn: [ // 表格標題
        { key: "serialNumber", column: "#" }, { key: "surveyName", column: "問卷" },
        { key: "surveyStatus", column: "狀態" }, { key: "startDate", column: "開始日期" },
        { key: "endDate", column: "結束日期" }
      ],
      surveyData: [], // 資料內容
      showCheckBox: true, // 是否顯示勾選框
      showWatchBtn: true, // 是否顯示觀看統計
      isCQuestion: false,  // 題目流水號

      searchText: '', // 搜尋關鍵字
      searchStartDate: '', // 開始時間
      searchEndDate: '',    // 結束時間
      searchStatus: ''    //  狀態
    }
  },
  mounted() {
    this.findAllData();
    this.changeSurveyStatus();
  },
  methods: {
    findAllData() {
      fetch("http://localhost:8080/find_all_survey")
        .then(res => res.json())
        .then(data => this.surveyData = data.questionnaireList)
    },
    changeSurveyStatus() {
      fetch("http://localhost:8080/change_survey_status")
        .then(res => res.json())
        .then(data => this.surveyData = data.questionnaireList.reverse())
    },
    updatefileredData() {
      let keyword = ''
      let keyStartDate = ''
      let keyEndDate = ''
      let keyStatus = ''
      let newData = this.surveyData
      newData.forEach(item => {
        if (item.surveyStatus == 1) {
          item.surveyStatus = '開放中'
        } else if (item.surveyStatus == 2) {
          item.surveyStatus = '已結束'
        } else if (item.surveyStatus == 0) {
          item.surveyStatus = '未開放'
        }
      })
      if (this.searchText) { // this.searchText上面的data變數
        keyword = this.searchText.toLocaleLowerCase()  // keyword此方法內的變數
        newData.filter(item =>
          item.surveyName.toLocaleLowerCase.includes(keyword))
      }
      if (this.searchStartDate) {
        keyStartDate = this.searchStartDate
        newData.filter(item =>
          item.startDate.includes(keyStartDate))
      }
      if (this.searchEndDate) {
        keyEndDate = this.searchEndDate
        newData.filter(item =>
          item.endDate.includes(keyEndDate))
      }
      if (this.searchStatus) {
        keyStatus = this.searchStatus
        newData.filter(item =>
          item.surveyStatus.includes(keyStatus))
      }

      return newData
    }
  },
  watch: {
    searchStartDate: function (newDate, oldDate) {
      this.updatefileredData();
    },
    searchEndDate: function (newDate, oldDate) {
      this.updatefileredData();
    },
    searchStatus: function (newStatus, oldStatus) {
      this.updatefileredData();
    }
  },
  computed: {
    filteredData() {
      let keyword = ''
      let keyStartDate = ''
      let keyEndDate = ''
      let keyStatus = ''
      let newData = this.surveyData
      newData.forEach(item => {
        if (item.surveyStatus == 1) {
          item.surveyStatus = '開放中'
        } else if (item.surveyStatus == 2) {
          item.surveyStatus = '已結束'
        } else if (item.surveyStatus == 0) {
          item.surveyStatus = '未開放'
        }
      })
      if (this.searchText) { // this.searchText上面的data變數
        keyword = this.searchText.toLocaleLowerCase()  // keyword此方法內的變數
        newData.filter(item =>
          item.surveyName.toLocaleLowerCase.includes(keyword))
      }
      if (this.searchStartDate) {
        keyStartDate = this.searchStartDate
        newData.filter(item =>
          item.startDate.includes(keyStartDate))
      }
      if (this.searchEndDate) {
        keyEndDate = this.searchEndDate
        newData.filter(item =>
          item.endDate.includes(keyEndDate))
      }
      if (this.searchStatus) {
        keyStatus = this.searchStatus
        newData.filter(item =>
          item.surveyStatus.includes(keyStatus))
      }

      return newData
    }
  }
}
</script>

<template>
  <div class="manager-warp">
    <h2 class="">問卷一覽</h2>
    <div class="sreach-area d-flex">
      <div class="input-area">
        <div class="another-input d-flex">
          <input type="text" placeholder="請輸入關鍵字" class="text-input mb-2" v-model="searchText">
          <!-- <div type="button" class="button text-center ms-2">關鍵字搜尋</div> -->
        </div>

        <div class="date-input d-flex">
          <input type="date" v-model="searchStartDate"><span class="mx-2">~</span>
          <input type="date" v-model="searchEndDate">

          <select name="statusSreach" id="" class="ms-2">
            <option value="" disabled selected>狀態查詢</option>
            <option value="">全部</option>
            <option value="開放中">開放中</option>
            <option value="已結束">已結束</option>
            <option value="未開放">未開放</option>
          </select>
        </div>
      </div>

      <!-- 建新問卷 -->
      <div class="fc-btn d-flex">
        <RouterLink to="/manager/create-survey" type="button" class="create-btn button"></RouterLink>
      </div>

      <!-- <div class="button-area">
        <div type="button" class="button text-center">時間搜尋</div>
      </div> -->
    </div>

    <div class="table-area">
      <TableView :columns="surveyColumn" :showCheckBox="showCheckBox" :data="filteredData" :showWatchBtn="showWatchBtn" />
    </div>

  </div>
</template>

<style lang="scss" scoped>
.manager-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  background-color: white;

  .sreach-area {
    justify-content: space-between;

    .text-input {
      border: 1px solid black;
      border-radius: 0.5rem;
      padding-left: 0.5rem;
      outline: none;
    }

    .fc-btn {
      align-items: end;
    }
  }


  .create-btn {
    width: 1.2rem;
    height: 1.2rem;
    margin-left: 0.5rem;
    background-image: url(../../public/create.png);
    background-repeat: no-repeat;
    background-size: contain;

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