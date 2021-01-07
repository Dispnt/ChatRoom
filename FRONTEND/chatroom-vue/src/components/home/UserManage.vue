<template>
	<el-container style="height:100vh">
		<chatHeader :userName=userName>

		</chatHeader>

		<el-container>
			<el-header class="grouptitle">
				<el-row style="padding: 2rem; padding-bottom: 0;" class="">{{groupTitle}}</el-row>
			</el-header>
			<el-main>
				<div style="margin-bottom: 20px; float: left;">
				    <el-button @click="registerFormVisible = true">注册新用户</el-button>
				    <el-button @click="deleteUser">删用户</el-button>
					
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
							<el-button type="primary" @click="addUser();registerFormVisible = false;">确 定</el-button>
						</div>
					</el-dialog>
					
				  </div>
				<el-table ref="multipleTable" :data="userTableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column prop="id" label="ID" width="120">
					</el-table-column>
					<el-table-column prop="nickname" label="昵称" width="120">
					</el-table-column>
					<el-table-column prop="mail" label="邮箱" show-overflow-tooltip>
					</el-table-column>
				</el-table>
			</el-main>
		</el-container>

	</el-container>
</template>

<script>
	import chatHeader from "@/components/common/ChatHeader.vue";

	export default {
		name: "UserManage",
		components: {
			chatHeader
		},
		data() {
			return {
				groupTitle: "所有已注册用户",
				manageInfo: {
					group_id: null,
					user_id: sessionStorage.getItem("user_id")
				},
				registerInfo: {
					mail: '',
					password: '',
					nickname: ''
				},
				userName: sessionStorage.getItem("user_name"),
				userTableData: this.$axios.get('/api/user/all')
					.then(response => (this.userTableData = response.data)),
				multipleSelection: [],
				registerFormVisible: false,
				addID:null
			}
		},
		filters: {
			formatDate(value, requirement) {
				let date = new Date(value);
				let y = date.getFullYear();
				let MM = date.getMonth() + 1;
				MM = MM < 10 ? ('0' + MM) : MM;
				let d = date.getDate();
				d = d < 10 ? ('0' + d) : d;
				let h = date.getHours();
				h = h < 10 ? ('0' + h) : h;
				let m = date.getMinutes();
				m = m < 10 ? ('0' + m) : m;
				let s = date.getSeconds();
				s = s < 10 ? ('0' + s) : s;
				if (requirement == "Full") {
					return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
				} else if (requirement = "Date") {
					return MM + '月' + d + '日'
				}

			}
		},
		mounted() {
			// this.queryMsg(1)

		},
		methods: {
			message_box(message_box_content = none) {
				this.$message(message_box_content);
			},
			deleteUser(){
				let user_selected = this.$refs.multipleTable.selection
				for (let i=0;i<user_selected.length;i++){
				     console.log("删除"+user_selected[i].nickname);
					 let param = new URLSearchParams();
					 param.append('mail', user_selected[i].mail);
					 this.$axios
					 	.post('/api/user/delete', param)
					 	.then(response => {
					 		this.message_box(response.data)
					 		this.$axios.get('/api/user/all')
					 			.then(response => (this.userTableData = response.data))
					 	})
				}
			},
			addUser(){
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
	.inputField {
		width: 70%;
		margin-bottom: 1rem;
	}
	
	#menu {
		background: #fff;
		border-right: 1px solid #348bff;
	}

	#avatar {
		background: #348bff;
		filter: drop-shadow(5px 5px 8px rgba(0, 0, 0, 0.12));
		margin-top: 2rem;
	}

	.el-aside {
		background-color: rgb(250, 250, 250);
		color: #333;
		text-align: center;
		/* line-height: 200px; */
	}

	.el-main {
		background-color: rgb(250, 250, 250);
	}

	.el-footer {
		background-color: rgb(250, 250, 250);
	}

	.title {
		font-weight: bold;
		font-size: 24px;
		text-align: left;
		color: #000;
	}

	.grouplist {
		overflow-x: hidden;
		overflow-y: auto;

		/* overflow: hidden; */
	}

	.grouplist::-webkit-scrollbar {
		display: none;
		/* Chrome Safari */
	}

	.text {
		font-size: 12px;
	}

	.item {
		margin-bottom: 10px;
	}


	.box-card {
		width: 80%;
		margin: 20px 4px;
		margin-left: auto;
		margin-right: auto;
	}


	.el-form-item {
		margin: 0 0 0 0;
	}

	.el-form-item__content {
		margin: 0 0 0 0;
	}

	.inputbox {
		background: #fff;
		filter: drop-shadow(16px 14px 36px rgba(0, 0, 0, 0.06));
		margin-top: -2rem;
		padding-top: 1rem;
		padding-bottom: 1rem;
		bottom: 0;
		width: 100%;
	}

	.grouptitle {
		background: #fff;
		border-bottom: 1px #348bff solid;
		padding-top: 1rem;
		padding-bottom: 8rem;
		top: 0;
		/* position: absolute; */
		width: 100%;
		font-weight: bolder;
		font-size: 42px;
		text-align: left;
		color: #348bff;
	}
</style>
