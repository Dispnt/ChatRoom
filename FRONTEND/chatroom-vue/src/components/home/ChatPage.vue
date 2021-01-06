<template>
	<el-container style="height:100vh">
		<el-aside id="menu" width="200px">
			<el-avatar id="avatar" :size="150" style="font-size: 36px;">fofu</el-avatar>
			<el-menu style="margin-top: 100px;" default-active="2" class="el-menu-vertical-demo" background-color=transparent
			 text-color="#2b2b2b" active-text-color="#0058CE" @open="handleOpen" @close="handleClose">
				<el-menu-item index="1">
					<span slot="title">群聊天</span>
				</el-menu-item>
				<el-menu-item index="2">
					<span slot="title">成员管理</span>
				</el-menu-item>
				<el-menu-item index="3">
					<span slot="title">录入新用户</span>
				</el-menu-item>
				<el-menu-item index="4">
					<span slot="title">查询聊天记录</span>
				</el-menu-item>
			</el-menu>
		</el-aside>
		
		<el-aside width="300px" class="grouplist">
			<el-row style="padding: 2rem; padding-bottom: 0;" class="title">群聊天</el-row>
			<el-row class="grouplist" >
				<el-card class="box-card" v-for="group in allGroupInfo" :key="group.id" >
					<div @click="groupBoxClick(group.id)">
						<el-row >
							<el-col>
								<a style="float: left;font-size: 20px;" >{{group.name}}</a>
								<el-button style="float: right; padding: 3px 0" type="text">{{group.time| formatDate("Time")}}</el-button>
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
				<el-col>
					<el-row class="grouplist">
						<el-card id="msgScroll" class="box-card" style="width: 90%;box-shadow: 0 2px 12px 0 rgba(0,0,0,0);">
							<div class="msgCard" v-for="msg in allMessage" :key="msg.content">
								<el-row>
									<el-col>
										<a style="float: left;font-size: 14px;">{{ msg.nickname }}</a>
										<el-button style="float: right; padding: 3px 0" type="text">{{ msg.time | formatDate("Full")}}</el-button>
									</el-col>
									<el-col>
										<div style="float: left;font-size: 20px;" class="text item">
											{{ msg.content }}
										</div>
									</el-col>
								</el-row>
								<el-divider></el-divider>
							</div>

						</el-card>
					</el-row>

				</el-col>
			</el-main>
			<el-footer style="position: relative">
				<el-col class="inputbox">
					<el-form ref="form" :model="form" label-width="0px" style="display: flex; justify-content: space-between; align-content: center">
						<el-form-item style="width: 100%; margin-left: 1rem">
							<el-input v-model="messageInfo.msg_content"></el-input>
						</el-form-item>
						<el-form-item style="display: flex; white-space: nowrap; justify-content: space-between; align-content: center; margin: 0 1rem">
							<el-button type="primary" @click="sendMsg">发送</el-button>
							<el-button @click="scrollToBottom">最底</el-button>
						</el-form-item>
					</el-form>
				</el-col>
			</el-footer>
		</el-container>

	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				allGroupInfo: this.$axios.get('/api/group/all')
					.then(response => (this.allGroupInfo = response.data)),
				groupTitle: "选择群开始聊天",
				messageInfo: {
					group_id: 1,
					user_id: 1,
					msg_content: ''
				},
				allMessage: []
			}
		},
		filters: {
			formatDate(value,full) {
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
				if (full == "Full") {return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;}
				else {return h + ':' + m + ':' + s}
				
			}
		},
		mounted() {
			// this.queryMsg(1)
			
		},
		methods: {
			groupBoxClick(group_id){
				this.messageInfo.group_id = group_id
				return setTimeout(this.queryMsg(group_id),200)
			},
			message_box(message_box_content = none) {
				this.$message(message_box_content);
			},
			sendMsg() {
				var param = new URLSearchParams();
				param.append('group_id', this.messageInfo.group_id);
				param.append('user_id', this.messageInfo.user_id);
				param.append('msg_content', this.messageInfo.msg_content);
				this.$axios
					.post('/api/chat/add', param)
					.then(response => {
						this.message_box(JSON.stringify(response.data))
						this.queryMsg()
					})
					this.scrollToBottom()
				
			},
			queryMsg(query_group_id ) {
				console.log("refreshed")
				this.groupTitle = this.allGroupInfo[query_group_id-1].name
				var param = new URLSearchParams();
				param.append('group_id', query_group_id);
				this.$axios.post('/api/chat/get', param)
					.then(response => {
						this.allMessage = response.data

					})
					this.scrollToBottom()
			},
			delMsg() {
				this.messageInfo.msg_content = ""
			},
			scrollToBottom() {
				let el = this.$el.getElementsByClassName('msgCard')
				let ellen = el.length
				el[ellen - 1].scrollIntoView();
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
