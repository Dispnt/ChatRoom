<template>
	<el-container style="height:100vh">
		<el-aside id="menu" width="200px">
			<el-avatar id="avatar" :size="150" style="font-size: 36px;">江</el-avatar>
			<el-menu style="margin-top: 100px;" default-active="2" class="el-menu-vertical-demo" background-color=transparent
			 text-color="#fff" active-text-color="#0058CE" @open="handleOpen" @close="handleClose">
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
			<el-row style="padding: 2rem; padding-bottom: 0;" class="title">群名称</el-row>
			<el-row class="grouplist">
				<el-card class="box-card" v-for="o in 40" :key="o">
					<el-row>
						<el-col>
							<a style="float: left;font-size: 20px;">我是卡片{{o}}</a>
							<el-button style="float: right; padding: 3px 0" type="text">15:22</el-button>
						</el-col>
						<el-col>
							<div style="float: left;font-size: 14px;" class="text item">
								内容{{o}}
							</div>
						</el-col>
					</el-row>
				</el-card>
			</el-row>
		</el-aside>
		<el-main>
			<el-row class="grouplist">
				<el-card class="box-card" style="width: 90%;box-shadow: 0 2px 12px 0 rgba(0,0,0,0);">
					<div v-for="o in 40" :key="o" >
					<el-row>
						<el-col>
							<a style="float: left;font-size: 20px;">我是卡片{{o}}</a>
							<el-button style="float: right; padding: 3px 0" type="text">15:22</el-button>
						</el-col>
						<el-col>
							<div style="float: left;font-size: 14px;" class="text item">
								内容
							</div>
						</el-col>
					</el-row>
					<el-divider></el-divider>
					</div>
					
				</el-card>
			</el-row>
			<el-row>
				<el-form ref="form" :model="form" label-width="80px">
					<el-form-item label="我:">
						<el-input type="textarea" v-model="messageInfo.msg_content"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="onSubmit">发送</el-button>
						<el-button>清空</el-button>
					</el-form-item>
				</el-form>
			</el-row>
		</el-main>
	</el-container>
</template>

<script>
	export default {
		data() {
			return {
				// allUserInfo:null
				allUserInfo: this.$axios.get('/api/user/all')
								.then(response => (this.allUserInfo = response.data)),
				messageInfo: {
					group_id: 1,
					user_id: 1,
					msg_content:''
				},
				getResult:[],
				addResult:[]
			}
		},
		methods: {
			onSubmit() {
				console.log('submit!');
			}
		}
	}
</script>

<style>
	#menu {
		background: #A8AABD;
	}

	#avatar {
		background: #348bff;
		filter: drop-shadow(16px 14px 36px rgba(0, 0, 0, 0.06));
		margin-top: 2rem;
	}

	.el-aside {
		background-color: #fff;
		color: #333;
		text-align: center;
		/* line-height: 200px; */
	}

	.title {
		font-weight: normal;
		font-size: 38px;
		text-align: left;
		color: #348bff;
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
</style>
