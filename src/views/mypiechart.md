const chartData = {
  resTotal: 100,

  // WCount > = qtInChartData
  questions: [

    // WCount > oneQuestion: {}
    {
      question: "Question 1",
      totalAnsswers: 100, // 初始化為該題的初始總回答數
      options: [
        { label: "Option A", value: 40 },
        { label: "Option B", value: 60 },
      ],
    },
    {
      question: "Question 2",
      totalAnswers: 120, // 初始化為該題的初始總回答數
      options: [
        { label: "Option X", value: 50 },
        { label: "Option Y", value: 70 },
      ],
    },
    // ...其他問題
  ],
};