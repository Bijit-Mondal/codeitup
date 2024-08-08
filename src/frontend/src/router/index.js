import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView'
import {useAuthStore} from "@/lib/store/auth.store";

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
    path: "/profile",
    meta: { requiresAuth: true },
    name: 'profile',
    component: () => import("../views/profile/ProfileView"),
  },
  {
    path: "/contest",
    name: 'contest',
    component: () => import("../views/contest/ContestView"),
  },
  {
    path: "/admin",
    meta: { requiresAuth: true },
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

router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    if(to.meta.requiresAuth && !authStore.isLoggedIn) {
        next({ name: 'auth' });
    } else {
        next();
    }
})

export default router
