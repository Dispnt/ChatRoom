<template>
	<el-container style="height:100vh">
		<chatHeader :userName=userName>
			
		</chatHeader>
		
		<el-aside width="300px" class="grouplist">
			<el-row style="padding: 2rem; padding-bottom: 0;" class="title">群聊天</el-row>
			<el-row class="grouplist" >
				<el-card class="box-card" v-for="group in UserGroupInfo" :key="group.id" >
					<div @click="groupBoxClick(group.id)">
						<el-row >
							<el-col>
								<a style="float: left;font-size: 18px;" >{{group.name}}</a>
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
	import chatHeader from "@/components/common/ChatHeader.vue";
	
	export default {
		name: "ChatPage",
		components: {
			chatHeader
		},
		data() {
			return {
				UserGroupInfo: null,
				groupTitle: "选择群开始聊天",
				messageInfo: {
					group_id: null,
					user_id: sessionStorage.getItem("user_id"),
					msg_content: ''
				},
				allMessage: [],
				refreshInterval:null,
				userName:sessionStorage.getItem("user_name")
			}
		},
		filters: {
			formatDate(value,requirement) {
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
				if (requirement == "Full") {return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;}
				else if (requirement="Date"){return  MM + '月' + d + '日'}
				
			}
		},
		mounted() {
			// this.queryMsg(1)
			this.queryMyGroup()
		},
		methods: {
			queryMyGroup(){
				var param = new URLSearchParams();
				param.append('user_id', this.messageInfo.user_id);
				this.$axios.post('/api/usergroup/get/group', param)
					.then(response => (this.UserGroupInfo = response.data))
			},
			groupBoxClick(group_id){
				clearInterval(this.refreshInterval);
				this.messageInfo.group_id = group_id
				this.refreshInterval = setInterval(()=>{this.queryMsg(group_id);},300)
				
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
						this.delMsg()
					})
					this.scrollToBottom()
				
			},
			queryMsg(query_group_id ) {
				console.log(this.UserGroupInfo)
				console.log("refreshed:"+query_group_id)
				for(let item of this.UserGroupInfo) {
					if(item.id === query_group_id) {
						this.groupTitle = item.name
				  }
				}
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

