<template>
	<el-container>
		<el-header>
			<chatHeader />
		</el-header>
		<el-container>
			<el-container>
				<el-main>
					<div>当前群组{{messageInfo.group_id}}</div>
					<div>
						<div class="" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
							<el-divider content-position="left">小江</el-divider>
							<span>答案有伐？</span>
						</div>
						<div class="" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
							<el-divider content-position="left">小泽</el-divider>
							<span>什么答案？</span>
						</div>
						<div class="" style="box-shadow: 0 1px 12px 0 rgba(0, 0, 0, 0.1)">
							<el-divider content-position="left">小民</el-divider>
							<span>javaweb我有</span>
						</div>
						<div class="" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
							<el-divider content-position="left">小江</el-divider>
							<span style="padding: -10px ;">javaweb发一下啊</span>
						</div>
						<el-divider><i class="el-icon-mobile-phone"></i>小习加入了群</el-divider>
						<div class="" style="box-shadow: 0 1px 12px 0 rgba(0, 0, 0, 0.1)">
							<el-divider content-position="left">小习</el-divider>
							<span>我要改餡法！</span>
						</div>
					</div>
					<div>
						<el-button type="success" icon="el-icon-check" v-on:click="queryMsg" circle></el-button>
						<el-input type="textarea" v-model="getResult" placeholder=""></el-input>
					</div>
					<br /><br /><br />
					<el-row :gutter="20">
						<el-col :span="20">
							<el-input type="textarea" v-model="messageInfo.msg_content" placeholder="请输入内容"></el-input>
						</el-col>
						<el-col :span="1">
							<el-button type="success" icon="el-icon-check" v-on:click="sendMsg" circle></el-button>
						</el-col>
					</el-row>
				</el-main>
				<el-footer>
					<chatFooter />
				</el-footer>
			</el-container>
			<el-aside width="200px">
			  <el-table :data="allUserInfo">
			    <el-table-column label="昵称" prop="nickname" width="60"> </el-table-column>
			    <el-table-column label="邮箱" prop="mail" width="140"> </el-table-column>
			  </el-table>
			</el-aside>
			</el-container>
			</el-container>
			<!-- <el-container>
	  <el-header>Header</el-header>
	  <el-main>Main</el-main>
	</el-container> -->
</template>

<script>
	import chatHeader from "@/components/common/ChatHeader.vue";
	import chatFooter from "@/components/common/ChatFooter.vue";

	export default {
		name: "Chatpage",
		components: {
			chatHeader,
			chatFooter
		},
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
			sendMsg() {
				var param = new URLSearchParams();
				param.append('group_id', this.messageInfo.group_id);
				param.append('user_id', this.messageInfo.user_id);
				param.append('msg_content', this.messageInfo.msg_content);
				this.$axios
					.post('/api/chat/add', param)
					.then(successResponse => {
						this.addResult = JSON.stringify(successResponse.data)
						this.messageInfo.msg_content=this.addResult;
						
					})
			},
			queryMsg(){
				var param = new URLSearchParams();
				param.append('group_id', this.messageInfo.group_id);
				this.$axios
					.post('/api/chat/get', param)
					.then(successResponse => {
						this.getResult = JSON.stringify(successResponse.data)
						
					})
				}}
	};
</script>
