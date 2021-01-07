import Vue from 'vue'
import Router from 'vue-router'
import UserLogin from '@/components/manage/UserLogin.vue'
import ChatPage from '@/components/home/ChatPage.vue'
import GroupManage from '@/components/home/GroupManage.vue'
import UserManage from '@/components/home/UserManage.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/chitchat',
      name: 'ChatPage',
      component: ChatPage,
		meta: {requireAuth: true, }	
    },   
	{
      path: '/login',
      name: 'UserLogin',
      component: UserLogin,

    },
	{
	  path: '/groupmanage',
	  name: 'GroupManage',
	  component: GroupManage,
	  meta: {requireAuth: true,}
	},
	{
	  path: '/usermanage',
	  name: 'UserManage',
	  component: UserManage,
	  meta: {requireAuth: true,}
	}
	
  ]
})