<script>
import TableView from "../components/Table.vue"

export default {
  components: {
    TableView
  },
  data() {
    return {
      surveyColumn: [
        { key: "surveyName", column: "問卷" },
        { key: "surveyStatus", column: "狀態" }, { key: "startDate", column: "開始日期" },
        { key: "endDate", column: "結束日期" }
      ],
      surveyData: [], // 表格內容
      // 是否顯示修改問卷
      showUpdateS: false,
      showUpdateQ: false,  // 顯示修改題目
      showResBox: true,   // 作答
      showWatchAnsBtn: false,  // 詳細答案內容 M才能看
      showCountBtn: true,   // 是否顯示觀看統計 U也可以看
      showAllResBtn: false,   // 全部作答M
      isCQuestion: true,   // manager題目流水號

      searchText: '', // 搜尋關鍵字
      searchStartDate: '', //開始時間
      searchEndDate: '',    //結束時間
      searchStatus: ''  // 狀態搜尋
    }
  },
  mounted() {
    this.findAllData();
    this.changeSurveyStatus();
  },
  methods: {
    keywordSreach() {
      this.updatefileredData();
    },
    findAllData() {
      fetch("http://localhost:8080/find_all_survey")
        .then(res => res.json())
        .then(data =>
          // console.log(data.questionnaireList)
          this.surveyData = data.questionnaireList
        )
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
      if (this.searchText) {
        keyword = this.searchText.toLowerCase()
        newData = newData.filter(item =>
          item.surveyName.toLowerCase().includes(keyword))
      }
      if (this.searchStartDate) {
        keyStartDate = this.searchStartDate
        newData = newData.filter(item =>
          item.startDate.includes(keyStartDate))
      }
      if (this.searchEndDate) {
        keyEndDate = this.searchEndDate
        newData = newData.filter(item =>
          item.endDate.includes(keyEndDate))
      }
      if (this.searchStatus) {
        keyStatus = this.searchStatus
        newData = newData.filter(item =>
          item.surveyStatus.includes(keyStatus))
      }

      return newData
    },
    createAnswer(item) {
      this.item = item
      // console.log(this.item)
      // window.location.href = '/userAnswer'
    },

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
    filteredData() {   // 更新顯示資料
      let keyword = ''
      let keyStartDate = ''
      let keyEndDate = ''
      let keyStatus = ''
      let newData = this.surveyData
      // console.log(newData)
      newData.forEach(item => {
        if (item.surveyStatus == 1) {
          item.surveyStatus = '開放中'
        } else if (item.surveyStatus == 2) {
          item.surveyStatus = '已結束'
        } else if (item.surveyStatus == 0) {
          item.surveyStatus = '未開放'
        }
      })
      if (this.searchText) {
        keyword = this.searchText.toLowerCase()
        newData = newData.filter(item =>
          item.surveyName.toLowerCase().includes(keyword))
      }
      if (this.searchStartDate) {
        keyStartDate = this.searchStartDate
        newData = newData.filter(item =>
          item.startDate.includes(keyStartDate))
      }
      if (this.searchEndDate) {
        keyEndDate = this.searchEndDate
        newData = newData.filter(item =>
          item.endDate.includes(keyEndDate))
      }
      if (this.searchStatus) {
        keyStatus = this.searchStatus
        newData = newData.filter(item =>
          item.surveyStatus.includes(keyStatus))
      }

      return newData
    }
  }
}
</script>

<template>
  <div class="user-warp">
    <h2 class="">問卷一覽</h2>
    <div class="sreach-area">
      <div class="input-area d-flex">
        <div class="another-input d-flex">
          <input type="text" placeholder="請輸入關鍵字" @blur="" class="text-input" v-model="searchText">
          <!-- <div type="button" class="button text-center ms-2" @click="keywordSreach">關鍵字搜尋</div> -->
        </div>

        <div class="date-input d-flex">
          <input type="date" v-model="searchStartDate"><span class="mx-2">~</span>
          <input type="date" v-model="searchEndDate">

          <select name="statusSreach" id="" class="ms-2" v-model="searchStatus">
            <option value="" disabled selected>狀態查詢</option>
            <option value="">全部</option>
            <option value="開放中">開放中</option>
            <option value="已結束">已結束</option>
            <option value="未開放">未開放</option>
          </select>
        </div>
      </div>

      <!-- <div class="button-area">
        <div type="button" class="button text-center">時間搜尋</div>
      </div> -->
    </div>

    <div class="table-area">
      <TableView :columns="surveyColumn" :showUpdateS="showUpdateS" :showUpdateQ="showUpdateQ" :showAllResBtn="showAllResBtn" :data="filteredData"
        :isCQuestion="isCQuestion" :showResBox="showResBox" :showWatchAnsBtn="showWatchAnsBtn" :showCountBtn="showCountBtn"
        @answer="createAnswer(item)" />
    </div>

  </div>
</template>

<style lang="scss" scoped>
.user-warp {
  width: 100%;
  height: 100%;
  padding: 1rem;
  padding-bottom: 0;
  // border-radius: 5rem;
  background-color: white;
  // z-index: 5;

  .sreach-area {
    display: flex;
    justify-content: start;

    .input-area {
      // width: 50%;

      .text-input {
        border: 1px solid black;
        border-radius: 0.5rem;
        padding-left: 0.5rem;
        outline: none;
      }

      .date-input {
        height: 2rem;
        margin-left: 1rem;
      }
    }

    // .button-area {
    //   width: 15%;
    //   margin-left: 1rem;
    //   display: flex;
    //   flex-direction: column;
    //   justify-content: space-between;

    // }

    .button {
      // width: 100%;
      height: 1.75rem;
      padding: 0 0.75rem;
      color: #ff7300;
      border-radius: 0.5rem;
      border: solid 2px #F8DC95;
      background-color: #FDF2D7;

      transition: 0.3s;

      &:hover {
        cursor: pointer;
        scale: 1.05;
      }

      &:active {
        scale: 0.95;
      }
    }
  }
}
</style>