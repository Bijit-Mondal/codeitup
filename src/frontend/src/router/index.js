import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/auth',
    children: [
      {
        path: '',
        name: 'auth',
        component: () => import("../views/auth/AuthView")
      },
      {
        path: 'otp',
        name: 'otp',
        component: () => import("../views/auth/OTPView")
      }
    ]
  },
  {
    path: "/problems",
    children: [
      {
        path: '',
        name: 'problem',
        component: () => import("../views/problem/ProblemList")
      },
      {
        path: 'id/:slug',
        name: 'problem-solve',
        component: () => import("../views/problem/ProblemSolve")
      }
    ]
  },
  {
    path: "/admin",
    redirect: { name: 'admin-home' },
    component: () => import("../views/admin/AdminView"),
    children: [
      {
        path: 'problems',
        children: [
          {
            path: '',
            name: 'admin-home',
            component: () => import("../views/admin/HomeView/HomeView")
          },
          {
            path: 'create',
            name: 'create-problem',
            component: () => import("../views/admin/ProblemView/CreateProblemView")
          },
          {
            path: 'edit/:slug',
            name: 'edit-problem',
            component: () => import("../views/admin/ProblemView/EditProblemView")
          },
          {
            path: 'default-code/:id',
            name: 'default-code',
            component: () => import("../views/admin/ProblemView/CreateDefaultCodeView")
          }
        ]
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
