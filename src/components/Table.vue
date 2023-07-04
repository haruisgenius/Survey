<script>
export default {
  props: {
    // 表格欄位名稱
    columns: {
      type: Array,  // 需為陣列形式
      required: true
    },
    // 表格內容
    data: {
      type: Array,
      required: true
    },
    // 分頁
    itemsPerPage: {
      type: Number,
      default: 5    // 1頁預設為5筆資料
    },

    // 顯示勾選框
    showCheckBox: false,
    showWatchBtn: false,  // 觀看統計
  },
  data() {
    return {
      currentPage: 1,
      displayedPages: [],
      item: {}      // 子層參數
    };
  },
  // 宣告一個純粹顯示用的變數，此變數須執行{}內程式碼
  computed: {
    totalPages() {
      // 計算總頁數的方法
      return Math.ceil(this.data.length / this.itemsPerPage);
    },
    paginatedData() {
      // 顯示當前頁數資料總筆數的方法
      const sortedData = this.data.slice(); // 最新資料顯示在最上面
      const startIndex = (this.currentPage - 1) * this.itemsPerPage; // 起始筆數
      const endIndex = startIndex + this.itemsPerPage; // 終止筆數
      return sortedData.slice(startIndex, endIndex); // 回傳該頁筆數之序號
    }
  },
  methods: {
    createAnswer() {
      // let serialNumber = this.columns.serialNumber
      console.log(this.columns)
    },
    // 前一頁方法
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
      this.updateDisplayedPages();
    }, nextPage() { // 後一頁方法
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
      this.updateDisplayedPages();
    }, goToPage(page) { // 跳至該分頁
      this.currentPage = page;
      this.updateDisplayedPages();
    }, updateDisplayedPages() {
      const range = 2; // 顯示的頁碼範圍，根據需求進行調整
      const startPage = Math.max(1, this.currentPage - range);
      const endPage = Math.min(this.totalPages, this.currentPage + range);
      this.displayedPages = [];
      if (endPage > startPage) {
        for (let i = startPage; i <= endPage; i++) {
          this.displayedPages.push(i);
        }
      } else if (this.totalPages > 4) {
        this.displayedPages.push(1);
        this.displayedPages.push(2);
        this.displayedPages.push(3);
      } else {
        for (let i = 1; i <= this.totalPages; i++) {
          this.displayedPages.push(i);
        }
      }
    }, editItem(item) {
      this.$emit('edit', item); // 触发edit事件并将索引作为参数传递给父组件
    }, deleteItem(item) {
      this.$emit('delete', item); // 触发delete事件并将索引作为参数传递给父组件
    }, completeItem(item) {
      this.$emit('complete', item); // 触发complete事件并将索引作为参数传递给父组件
    }, chooseItem() {
      const list = this.paginatedData.filter(item => item.selected)
      this.$emit('choose', list); // 触发choose事件并将索引作为参数传递给父组件
    }
  },
  watch: {
    data(newData) {
      this.updateDisplayedPages();
    }
  }
}
</script>

<template>
  <div class="table-warp">
    <div class="table-area">

      <table class="table table-warning table-striped table-fixed table-hover">
        <!-- 表格標題 -->
        <thead>
          <tr class="table-warning">
            <!-- 迴圈印出表格標題名稱 -->
            <th v-if="showCheckBox">
              <div type="button" class="delete-btn button"></div>
            </th>
            <th v-for="column in columns">{{ column.column }}</th>
            <th v-if="!showCheckBox">問卷作答</th>
            <th v-if="showWatchBtn">觀看統計</th>
            <th v-if="showCheckBox">修改</th>
          </tr>
        </thead>

        <!-- 表格內容 -->
        <tbody>
          <!-- 印出該分頁筆數(列) -->
          <tr v-for="(item, index) in paginatedData" :key="item.id">
            <!-- 勾選框 -->
            <td v-if="showCheckBox">
              <input type="checkbox" name="chooseBox" id="chooseBox">
            </td>
            <!-- 迴圈印出該頁對應標題欄位之內容 -->
            <td v-for="column in columns">{{ item[column.key] }}</td>
            <td v-if="!showCheckBox" calss="d-flex justify-content-center">
              <div type="button" class="answer-btn text-center" @click="createAnswer">作答</div>
            </td>
            <td v-if="showWatchBtn" calss="d-flex justify-content-center">
              <div type="button" class="watch-count text-center">觀看</div>
            </td>
            <td v-if="showCheckBox" calss="d-flex justify-content-center">
              <div type="button" class="update-btn text-center">修改</div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 頁碼 -->
    <div class="pages-changer d-flex justify-content-center">
      <nav aria-label="Page navigation example" class="mb-0">
        <!-- 分頁切換按鈕 -->
        <ul class="pagination mb-0">
          <li class="page-item"> <!-- 前頁 -->
            <a class="page-link" aria-label="Previous" @click="previousPage" :disabled="currentPage === 1">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li> <!-- 至該分頁 -->
          <li class="page-item" v-if="currentPage > 3">
            <a class="page-link" @click="goToPage(1)">1</a>
          </li>
          <li class="page-item" v-if="currentPage > 4">
            <span class="page-link">...</span>
          </li>
          <li class="page-item" v-for="page in displayedPages" :key="page" :class="{ active: page === currentPage }">
            <a class="page-link" @click="goToPage(page)">{{ page }}</a>
          </li>
          <li class="page-item" v-if="currentPage < totalPages - 3">
            <span class="page-link">...</span>
          </li>
          <li class="page-item" v-if="currentPage < totalPages - 2">
            <a class="page-link" @click="goToPage(totalPages)">{{ totalPages }}</a>
          </li>
          <li class="page-item"> <!-- 後頁 -->
            <a class="page-link" aria-label="Next" @click="nextPage" :disabled="currentPage === totalPages">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.delete-btn {
  width: 1.2rem;
  height: 1.2rem;
  // margin-left: 0.5rem;
  background-image: url(../../public/trashcan.png);
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



.table-warp {
  // height: 100%;
  margin-top: 0.2rem;
  display: flex;
  flex-direction: column;
  align-items: space-between;

  .table {
    border: solid #F8DC95 2px;
    border-radius: 10%;
    margin-bottom: 0.5rem;

    .watch-count {
      width: 3rem;
      // background-color: aqua;
    }

    .answer-btn {
      width: 3rem;
    }

    .update-btn {
      width: 3rem;

    }
  }

  .pages-changer {
    // height: 100%;

    nav {
      // height: 5rem;

      .page-item {
        padding: 0;

        // .page-link {
        //   padding: 0 1rem;
        // }
      }
    }
  }
}
</style>