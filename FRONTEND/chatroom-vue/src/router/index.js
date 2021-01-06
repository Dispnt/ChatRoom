import Vue from 'vue'
import Router from 'vue-router'
import BlogIndex from '@/components/home/BlogIndex.vue'
import UserRegister from '@/components/manage/UserRegister.vue'
import UserLogin from '@/components/manage/UserLogin.vue'
import ChatPage from '@/components/home/ChatPage.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/chitchat'
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
      path: '/chitchat',
      name: 'ChatPage',
      component: ChatPage
    },   
	{
      path: '/login',
      name: 'UserLogin',
      component: UserLogin
    },
    {
      path: '/register',
      name: 'UserRegister',
      component: UserRegister
    }
  ]
})