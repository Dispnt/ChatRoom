<template>
	<div>
		<blog-header></blog-header>
		<hr />
		<div>
			邮箱：<input type="text" v-model="loginInfoVo.mail" placeholder="请输入邮箱" />
			<br />
			密码：<input type="password" v-model="loginInfoVo.pwd" placeholder="请输入密码" />
			<br />
			昵称：<input type="text" v-model="loginInfoVo.nickname" placeholder="请输入昵称" />
			<br />
			<button v-on:click="login">录入</button>
			<br />
			<button v-on:click="findAll">查找</button>
			<br />
			<ul>
				<li v-for="item in info" :key="item.message">
				    {{ item.mail }}
				 </li>
			</ul>
			<br />
			<textarea cols="30" rows="10" v-model="responseResult"></textarea>
		</div>
		<hr />
		<blog-		></blog->
	</div>
</template>

<script>
	import chatHeader from '@/components/common/ChatHeader.vue'
	import chatFooter from '@/components/common/ChatFooter.vue'

	export default {
		name: 'BlogLogin',
				components: {
			chatHeader,
			chatFooter
		},
		data() {
			return {
				loginInfoVo: {
					username: '',
					password: ''
				},
				responseResult: [],
				info: null
			}
		},
		methods: {
			login() {
				var param = new URLSearchParams();
				param.append('mail', this.loginInfoVo.mail);
				param.append('pwd', this.loginInfoVo.pwd);
				param.append('nickname', this.loginInfoVo.nickname);
				this.$axios
					.post('/api/add', param)
					.then(successResponse => {
						this.responseResult = JSON.stringify(successResponse.data)
						if (successResponse.data.code === 200) {
							this.$router.replace({
								path: '/index'
							})
						}
					})
					.catch(failResponse => {})
			},
			findAll() {
				this.$axios
					.get('/api/all')
					.then(response => (this.info = response.data))
					.catch(failResponse => {})
			}
		}
	}
</script>
