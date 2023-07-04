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
    }
  ]
})

export default router
