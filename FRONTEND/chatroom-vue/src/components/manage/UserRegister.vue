<template>
	<el-row class="bg " type="flex" justify="center">
		<el-col type="flex" align="middle">
			<div class="grid-content" id="login">
				<el-row>
					<el-input class="inputField" v-model="loginInfo.mail" placeholder="邮箱"></el-input>
					<el-input class="inputField" type="password" v-model="loginInfo.password" placeholder="密码"></el-input>
					<el-input class="inputField" v-model="loginInfo.nickname" placeholder="昵称"></el-input>
				</el-row>
				<el-row style="padding-bottom: 3rem; margin-top: 5rem;">
					<el-button type="primary" @click="register">注册</el-button>
				</el-row>
			</div>


		</el-col>
	</el-row>



</template>

<script>
	export default {
		data() {
			return {
				registerInfo: {
					mail: '',
					password: '',
					nickname: ''
				},
				responseResult: [],
				info: null
			}
		},
		methods: {
			message_box(message_box_content = none) {
				this.$message(message_box_content);
			},
			login(){
				var param = new URLSearchParams();
				param.append('mail', this.loginInfo.mail);
				param.append('pwd', this.loginInfo.password);
				this.$axios
					.post('/api/user/login', param)
					.then(response => {
						this.message_box(JSON.stringify(response.data))
					})
			}
			

		}
	}
</script>

<style>
	.bg {
		height: 100vh;
		background: #cbcbcb;
		align-items: center;
		justify-content: center;
	}

	#login {
		width: 30vw;
		/* 		height: 50vh; */
		border-radius: 10px;
		background: #fff;
		box-shadow: 24px 26px 19px rgba(0, 0, 0, 0.16);
		padding-top: 3rem;

	}

	.inputField {
		width: 70%;
		margin-bottom: 1rem;
	}
</style>
