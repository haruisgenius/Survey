<script>
//               ArcElement : 定義及渲染圓餅圖的圓弧元素
//               Tooltip : 展示鼠標懸停時的數據訊息(值or其他)
//               Legend : 圖表中的圖例(說明每個參數所代表的標籤)
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Doughnut } from 'vue-chartjs';  // Doughnut : 圓環
import ChartDataLabels from '../../node_modules/chartjs-plugin-datalabels';
// 將()內元素or插件引入全局Chart且供整個應用程式使用
ChartJS.register(ArcElement, Tooltip, Legend, ChartDataLabels)

export default {
  extends: Doughnut,  // 使用組件Doughnut(渲染Chart.js圓餅圖)擴展vue，將繼承Doughnut組件之所有功能及屬性
  props: {
    chartData: {
      type: Object,
      required: true
    }, 
    // 'option',
  },
  data() {
    return {
      // 圖表設定
      options: {
        responsive: true,   // 靈敏度
        maintainAspectRatio: false,  // 固定寬比
        legend: {           // 圖例
          position: 'top'   // 位置(預設top)
        }
      },
      colors: [
        '#FFB800', '#FFDA7C', '#FFEDBE', '#C1693C', '#FF8A00', 
        '#FFBA7C', '#FFD9BE', '#FFF500', '#FFE27C', '#FFF5BE', 
        '#DEA000', '#E7C570', '#EBDAAF', 
      ]
    }
  },
  mounted() {
    this.renderChart();
  },
  methods: {
    renderChart() {
      if(this.chartData && this.chartData.question){
        const dataSets = this.chartData.questions;
  
        const data = {
          labels: dataSets.map(question => question.question),
          datasets: dataSets.map(question => ({
            data: question.options.map(option => option.value),
            backgroundColor: this.colors
          }))
        }
        this.renderChart(data, this.options);

      }

    // // 遍歷問題數據生成圓餅圖
    // chartData.questions.forEach((question, index) => {
    //   const chartConfig = {
    //     type: 'Doughnut',
    //     data: {
    //       labels: question.options.map(option => option.label),
    //       datasets: [
    //         {
    //           data: question.options.map(option => option.value),
    //           backgroundColor: this.$data.colors,  // 使用chart.vue的colors數據
    //         },
    //       ],
    //     },
    //     options: this.$data.options,  // 使用chart.vue的options數據
    //   };

    //   //創建圖表
    //   new Doughnut(ctx, chartConfig);
    // })

    }
  }
}
</script>

<template>
  <div>
    <canvas :chart-data="data" :chart-options="options"></canvas>
  </div>
</template>

<style>
</style>