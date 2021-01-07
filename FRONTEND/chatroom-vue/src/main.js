import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
// 引用axios，并设置基础URL为后端服务api地址
var axios = require('axios')
axios.defaults.baseURL = 'http://127.0.0.1:8080/'
// 将API方法绑定到全局
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI);
/* eslint-disable */

router.beforeEach((to, from, next) => {
	if (to.meta.requireAuth) {
		if (sessionStorage.getItem("user_name") == null) {
			next('/login');
		} else {
			next();
		}
	} else {
		next();
	}
});

new Vue({
	el: '#app',
	router,
	components: {
		App
	},
	template: '<App/>',
	render: h => h(App)
})
