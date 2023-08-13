import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'user',
      component: () => import('../views/User.vue')
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('../views/Manager.vue')
    },
    {
      path: '/manager/create-survey',
      name: 'create-survey',
      component: () => import('../views/CreateSurvey.vue')
    },
    {
      path: '/manager/create-survey/question',
      name: 'manager-create-question',
      component: () => import('../views/SurveyQuestion.vue')
    },
    {
      path: '/userAnswer/:serialNumber?',  // ? > 可有可無
      name: 'user-answer',
      component: () => import('../views/UAnswer.vue')
    },
    {
      path: '/watchAnswer/:serialNumber?',
      name: 'watch-answer',
      component: () => import('../views/WAnswer.vue')
    },
    {
      path: '/answerDetail/:serialNumber?',
      name: 'answer-detail',
      component: () => import('../views/AnsDetail.vue')
    },
    {
      path: '/watchCount/:serialNumber?',
      name: 'watch-count',
      component: () => import('../views/WCount.vue')
    }
  ]
})

export default router
