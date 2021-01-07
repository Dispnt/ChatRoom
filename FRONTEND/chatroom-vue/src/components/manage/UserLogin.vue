<template>
	<el-row class="bg " type="flex" justify="center">
		<el-col type="flex" align="middle">
			<div class="grid-content" id="login">
				<el-row>
					<el-input class="inputField" v-model="loginInfo.mail" placeholder="邮箱"></el-input>
					<el-input class="inputField" type="password" v-model="loginInfo.password" placeholder="密码"></el-input>
				</el-row>
				<el-row style="padding-bottom: 3rem; margin-top: 5rem;">
					<el-button type="primary" @click="login">登录</el-button>
					<el-button @click="registerFormVisible = true">注册</el-button>

					<el-dialog title="注册" :visible.sync="registerFormVisible">

						<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
							<el-form-item label="邮箱" prop="mail">
								<el-input class="inputField" v-model="registerInfo.mail" placeholder="yuyuanjiajian@gmail.com"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="">
								<el-input class="inputField" type="password" v-model="registerInfo.password" placeholder="123321?"></el-input>
							</el-form-item>
							<el-form-item label="昵称" prop="name">
								<el-input class="inputField" v-model="registerInfo.nickname" placeholder="Yu"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="registerFormVisible = false">取 消</el-button>
							<el-button type="primary" @click="register();registerFormVisible = false;">确 定</el-button>
						</div>
					</el-dialog>
				</el-row>
			</div>
		</el-col>
	</el-row>


</template>


<script>
	export default {
		data() {
			return {
				loginInfo: {
					mail: '',
					password: ''
				},
				registerInfo: {
					mail: '',
					password: '',
					nickname: ''
				},
				registerFormVisible: false
			}
		},

		methods: {
			message_box(message_box_content = none) {
				this.$message(message_box_content);
			},
			login() {
				var param = new URLSearchParams();
				param.append('mail', this.loginInfo.mail);
				param.append('pwd', this.loginInfo.password);
				this.$axios
					.post('/api/user/login', param)
					.then(response => {
						if (response.data.status=="MailErr"){
							this.message_box("请检查邮箱")
						}
						else if (response.data.status=="PwdErr"){
							this.message_box("请检查密码")
						}
						else {
							console.log(response.data.name)
							sessionStorage.setItem("user_name", response.data.name);
							sessionStorage.setItem("user_id", response.data.id);
							this.$router.push("/chitchat");
						}
						
					})
			},
			register() {
				var param = new URLSearchParams();
				param.append('mail', this.registerInfo.mail);
				param.append('pwd', this.registerInfo.password);
				param.append('nickname', this.registerInfo.nickname);
				this.$axios
					.post('/api/user/register', param)
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
