import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '@/page/HomePage.vue'
import BasicLayout from '@/layouts/BasicLayout.vue'
import ACCESS_ENUM from '@/access/accessEnum.ts'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: BasicLayout,
      children: [
        {
          path: '',
          redirect: '/home',
        },
        {
          path: '/home',
          name: '首页',
          component: HomePage,
          meta: { key: '/home' },
        },
        {
          path: '/admin/manage',
          name: '用户管理',
          component: () => import('@/page/admin/UserManagePage.vue'),
        },
        {
          path: '/about',
          name: 'about',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/AboutView.vue'),
          meta: { key: '/about' },
        },
      ]
    },
    {
      path: '/user/login',
      name: '用户登录',
      component: () => import('@/page/user/UserLoginPage.vue'),
    },
    {
      path: '/user/register',
      name: '用户注册',
      component: () => import('@/page/user/UserRegisterPage.vue'),
    },
  ],
})


export default router
