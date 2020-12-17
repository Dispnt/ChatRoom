import Vue from 'vue'
import Router from 'vue-router'
import BlogIndex from '@/components/home/BlogIndex.vue'
import BlogLogin from '@/components/manage/BlogLogin.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/index',
      name: 'BlogIndex',
      component: BlogIndex
    },
    {
      path: '/manage',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'BlogLogin',
      component: BlogLogin
    }
  ]
})