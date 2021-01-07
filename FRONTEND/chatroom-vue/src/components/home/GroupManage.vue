<template>
	<el-container style="height:100vh">
		<chatHeader :userName=userName>

		</chatHeader>

		<el-aside width="300px" class="grouplist">
			<el-row style="padding: 2rem; padding-bottom: 0;" class="title">
				<a>所有群</a> 
				<el-button icon="el-icon-plus" circle @click="addGroupFromVisible = true"></el-button>
				
				<el-dialog title="添加新群" :visible.sync="addGroupFromVisible">
				
					<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
						<el-form-item label="群名" prop="mail">
							<el-input class="inputField" v-model="addGroupInfo.name" placeholder="数据挖掘"></el-input>
						</el-form-item>
						<el-form-item label="简介" prop="">
							<el-input class="inputField" v-model="addGroupInfo.intro" placeholder="黄金矿工"></el-input>
						</el-form-item>
					</el-form>
					
					<div slot="footer" class="dialog-footer">
						<el-button @click="addGroupFromVisible = false">取 消</el-button>
						<el-button type="primary" @click="addGroup();addGroupFromVisible = false;">确 定</el-button>
					</div>
				</el-dialog>
				
			</el-row>
			
			<el-row class="grouplist">
				<el-card class="box-card" v-for="group in allGroupInfo" :key="group.id">
					<div @click="groupBoxClick(group.id)">
						<el-row>
							<el-col>
								<a style="float: left;font-size: 18px;">{{group.name}}</a>
								<el-button style="float: right; padding: 3px 0" type="text">{{group.time| formatDate("Date")}}</el-button>
							</el-col>
							<el-col>
								<div style="float: left;font-size: 14px;" class="text item">
									{{group.intro}}
								</div>
							</el-col>
						</el-row>
					</div>
				</el-card>
			</el-row>
		</el-aside>
		<el-container>
			<el-header class="grouptitle">
				<el-row style="padding: 2rem; padding-bottom: 0;" class="">{{groupTitle}}</el-row>
			</el-header>
			<el-main>
				<div style="margin-bottom: 20px; float: left;">
				    <el-button @click="addUserFromVisible = true">往群里添加用户</el-button>
				    <el-button @click="kickUsertoGroup">踢出用户</el-button>
					<el-dialog title="添加用户" :visible.sync="addUserFromVisible">
					
						<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
							<el-form-item label="ID" prop="mail">
								<el-input class="inputField" v-model="addID" placeholder="添加人ID"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer">
							<el-button @click="addUserFromVisible = false">取 消</el-button>
							<el-button type="primary" @click="addUsertoGroup();addUserFromVisible = false;">确 定</el-button>
						</div>
					</el-dialog>
					
				  </div>
				<el-table ref="multipleTable" :data="userTableData" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column prop="userid" label="ID" width="120">
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
		name: "GroupManage",
		components: {
			chatHeader
		},
		data() {
			return {
				allGroupInfo: this.$axios.get('/api/group/all')
					.then(response => (this.allGroupInfo = response.data)),
				groupTitle: "选择群管理",
				manageInfo: {
					group_id: null,
					user_id: sessionStorage.getItem("user_id")
				},
				addGroupInfo:{
					intro:null,
					name:null,
				},
				userName: sessionStorage.getItem("user_name"),
				userTableData: null,
				multipleSelection: [],
				addUserFromVisible: false,
				addGroupFromVisible:false,
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
			groupBoxClick(group_id) {
				this.manageInfo.group_id = group_id
				this.queryUser(group_id)
				
			},
			message_box(message_box_content = none) {
				this.$message(message_box_content);
			},
			scrollToBottom() {
				let el = this.$el.getElementsByClassName('msgCard')
				let ellen = el.length
				el[ellen - 1].scrollIntoView();
			},
			queryUser(group_id){
				this.groupTitle = this.allGroupInfo[group_id-1].name
				var param = new URLSearchParams();
				param.append('group_id', this.manageInfo.group_id);
				this.$axios
					.post('/api/usergroup/get/user', param)
					.then(response => {
						this.userTableData = response.data
						
					})
			},
			kickUsertoGroup(){
				let user_selected = this.$refs.multipleTable.selection
				for (let i=0;i<user_selected.length;i++){
				     console.log("移除"+user_selected[i].userid);
					 let param = new URLSearchParams();
					 param.append('user_id', user_selected[i].userid);
					 param.append('group_id', this.manageInfo.group_id);
					 this.$axios
					 	.post('/api/usergroup/remove', param)
					 	.then(response => {
					 		this.message_box(response.data)
					 		this.queryUser(this.manageInfo.group_id)
					 	})
				}
			},
			addUsertoGroup(){
				let param = new URLSearchParams();
				param.append('user_id', this.addID);
				param.append('group_id', this.manageInfo.group_id);
				this.$axios
					.post('/api/usergroup/add', param)
					.then(response => {
						this.message_box(response.data)
						this.queryUser(this.manageInfo.group_id)
					})
			},
			addGroup(){
				let param = new URLSearchParams();
				param.append('name', this.addGroupInfo.name);
				param.append('intro', this.addGroupInfo.intro);
				this.$axios
					.post('/api/group/add', param)
					.then(response => {
						this.message_box(response.data)
						this.$axios.get('/api/group/all')
							.then(response => (this.allGroupInfo = response.data))
					})
			}
		}
	}
</script>

<style>
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
