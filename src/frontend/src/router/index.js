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
    component: () => import("../views/admin/AdminView"),
    children: [
      {
        path: '',
        name: 'admin-home',
        component: () => import("../views/admin/HomeView/HomeView")
      },
      {
        path: 'create-problem',
        name: 'create-problem',
        component: () => import("../views/admin/ProblemView/CreateProblemView")
      }
    ]
  }
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
