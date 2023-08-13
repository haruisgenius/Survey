<script>
import { RouterLink } from 'vue-router';

export default {
    props: {
        // 表格欄位名稱
        columns: {
            type: Array,  // 指定資料型態以確保可靠性及安全性
            required: true  // 指定資料是否必須被傳遞
        },
        // 表格內容
        data: {
            type: Array,
            required: true
        },
        // 分頁
        itemsPerPage: {
            type: Number,
            default: 5 // 1頁預設為5筆資料
        },
        // 顯示修改問卷
        showUpdateS: false,
        showUpdateQ: false,
        showResBox: false,
        showCountBtn: false,
        showAllResBtn: false,
        showWatchAnsBtn: false,
        isCQuestion: false, // 前端流水號
    },
    data() {
        return {
            currentPage: 1,
            displayedPages: [],
            item: {} // 子層參數
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
        createAnswer(item) {
            // let serialNumber = this.columns.serialNumber
            this.$emit('answer', item);
        },
        getQuestion(item, index){
          console.log(item)
          let qIndex = index
          this.$emit('updateQ', item, qIndex)
        },
        // 前一頁方法
        previousPage() {
            if (this.currentPage > 1) {
                this.currentPage--;
            }
            this.updateDisplayedPages();
        }, nextPage() {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
            }
            this.updateDisplayedPages();
        }, goToPage(page) {
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
            }
            else if (this.totalPages > 4) {
                this.displayedPages.push(1);
                this.displayedPages.push(2);
                this.displayedPages.push(3);
            }
            else {
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
            const list = this.paginatedData.filter(item => item.selected);
            this.$emit('choose', list); // 触发choose事件并将索引作为参数传递给父组件
        }
    },
    watch: {
        data(newData) {
            this.updateDisplayedPages();
        }
    },
    components: { RouterLink }
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
            <!-- <th v-if="showCheckBox">
              <div type="button" class="delete-btn button"></div>
            </th> -->
            <th v-if="isCQuestion">#</th>
            <th v-for="column in columns">{{ column.column }}</th>
            <!-- User -->
            <th v-if="showResBox">問卷作答</th>
            <th v-if="showCountBtn">觀看統計</th>
            
            <!-- Manager -->
            <th v-if="showAllResBtn">所有作答</th>   <!-- 觀看作答一覽 -->
            <th v-if="showWatchAnsBtn">觀看細節</th>  <!-- 答案細節 -->
            <th v-if="showUpdateS">修改問卷</th>
            <th v-if="showUpdateQ">修改題目</th>
          </tr>
        </thead>

        <!-- 表格內容 -->
        <tbody>
          <!-- 印出該分頁筆數(列) -->
          <tr v-for="(item, index) in paginatedData" :key="item.id">
            <!-- 勾選框 -->
            <!-- <td v-if="showCheckBox">
              <input type="checkbox" name="chooseBox" id="chooseBox">
            </td> -->
            <!-- 迴圈印出該頁對應標題欄位之內容 -->
            <td v-if="isCQuestion">{{ index + 1 }}</td>  <!-- 序號 -->
            <td v-for="column in columns">{{ item[column.key] }}</td>
            <!-- =================User=================== -->
            <!-- User作答 -->
            <td v-if="showResBox" calss="d-flex justify-content-center">
              <!--               到``中間的網址 取此行表格資料之問卷流水號 -->      <!-- 不用@click="createAnswer(item)" -->             
              <RouterLink :to="`/userAnswer/${ item.serialNumber }`" class="work text-center" v-if="item.surveyStatus == '開放中'">作答</RouterLink>
              <div class="cannot text-center" v-else>作答</div>
            </td>
            <!-- 觀看統計 -->
            <td v-if="showCountBtn" calss="d-flex justify-content-center">
              <div class="cannot btn text-center" v-if="item.surveyStatus == '未開放'">統計</div>
              <RouterLink :to="`/watchCount/${ item.serialNumber }`" type="button" class="work-btn text-center" v-else>統計</RouterLink>
            </td>

            <!-- ===============Manager=================== -->
            <!-- 觀看答案總覽 -->
            <td v-if="showAllResBtn" calss="d-flex justify-content-center">
              <div class="cannot btn text-center" v-if="item.surveyStatus == '未開放'">觀看</div>
              <RouterLink :to="`/watchAnswer/${ item.serialNumber }`" type="button" class="work-btn text-center" v-else>觀看</RouterLink>
            </td>
            <!-- 觀看詳細作答 -->
            <td v-if="showWatchAnsBtn" calss="d-flex justify-content-center">
              <RouterLink :to="`/watchAnswer/${ item.serialNumber }`" type="button" class="work-btn text-center">細節</RouterLink>
            </td>
            <!-- 修改問卷 -->
            <td v-if="showUpdateS" calss="d-flex justify-content-center">
              <RouterLink to="/manager" type="button" class="work-btn text-center" @click="editItem(item)" v-if="item.surveyStatus == '未開放'">修改</RouterLink>
              <div class="cannot btn text-center" v-else>修改</div>
            </td>
            <!-- 修改題目 -->
            <td v-if="showUpdateQ" calss="d-flex justify-content-center">
              <button type="button" class="work-btn text-center" @click="getQuestion(item, index)">修改</button>
            </td>
            <!-- 題目修改 -->
            <!-- <td v-if="updateQBtn">
              <div class="work-btn btn text-center">修改</div>
            </td> -->
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

    // .watch-count {
    //   width: 3rem;
    //   text-decoration: none;
    //   padding: 0.2rem 0.7rem;
    //   color: #ff6a00;
    //   background-color: #F8DC95;
    //   border: solid #ff8400 2px;
    //   border-radius: 0.5rem;

    //   // 按鈕變絲滑
    //   transition: 0.3s;

    //   &:hover {
    //     cursor: pointer;
    //     scale: 1.05;
    //   }

    //   // 點擊時效果
    //   &:active {
    //     scale: 0.95;
    //   }
    // }

    .work-btn {
      width: 3.5rem;
      text-decoration: none;
      padding: 0.1rem 0.63rem 0.1rem 0.63rem;
      color: #ff6a00;
      background-color: #F8DC95;
      border: solid #ff8400 2px;
      border-radius: 0.5rem;

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

    .cannot {
      text-decoration: none;
      padding: 0.1rem 0.63rem 0.1rem 0.63rem;
      color: white;
      width: 3.5rem;
      background-color: #949494;
      border: solid #575757 2px;
      border-radius: 0.5rem;
      &:hover {
        cursor: default;
      }
}

    .work {
      text-decoration: none;
      padding: 0.2rem 0.7rem;
      margin-top: 0.1rem;
      color: #ff6a00;
      width: 3rem;
      background-color: #F8DC95;
      border: solid #ff8400 2px;
      border-radius: 0.5rem;

      // 按鈕變絲滑
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

    // .update-btn {
    //   width: 3rem;

    // }
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