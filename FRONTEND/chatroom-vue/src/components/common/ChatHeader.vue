<template>
	<el-aside id="menu" width="200px">
    <el-avatar id="avatar" :size="150" style="font-size: 36px;">{{userName}}</el-avatar>
    	<p>你是{{userName}}</p>
    	<el-menu style="margin-top: 100px;" :default-active="current_index" class="el-menu-vertical-demo" background-color=transparent
    	 text-color="#2b2b2b" active-text-color="#0058CE" @open="handleOpen" @close="handleClose">
    		<el-menu-item index="1" @click="push(1,'/chitchat')">
    			<span slot="title">群聊天</span>
    		</el-menu-item>
    		<el-menu-item index="2" @click="push(2,'/groupmanage')">
    			<span slot="title">群成员管理</span>
    		</el-menu-item>
    		<el-menu-item index="3" @click="push(3,'/usermanage')">
    			<span slot="title">注册用户管理</span>
    		</el-menu-item>
    	</el-menu>
    	<el-button @click="logout" style="position: absolute; left: 0; bottom: 1rem; margin-left: 4rem; margin-right: auto;">登出</el-button>
    </el-aside>
    
</template>

<script>
export default {
  name: 'ChatHeader',
  props: ['userName'],
  data() {
  	return {
		current_index:sessionStorage.getItem("current_index")
	}
	},
  methods:{
	  logout() {
	  	sessionStorage.removeItem("user_name");
	  	sessionStorage.removeItem("user_id");
	  	this.$router.push("/login");
	  
	  },
	  push(index,router_){
		  sessionStorage.setItem("current_index", index);
		  this.$router.push(router_)
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
