<template>
	<el-row class="bg " type="flex" justify="center">
		<el-col type="flex" align="middle">
			<div class="grid-content" id="login">
				<el-row>
					<el-input class="inputField" v-model="input" placeholder="请输入内容"></el-input>
					<el-input class="inputField" v-model="input" placeholder="请输入内容"></el-input>
				</el-row>
				<el-row style="padding-bottom: 3rem; margin-top: 5rem;">
					<el-button type="primary">主要按钮</el-button>
					<el-button>默认按钮</el-button>
				</el-row>
			</div>
			
			
		</el-col>
	</el-row>
	
	
	
</template>

<script>
</script>

<style>
	.bg {
		height: 100vh;
		background: #cbcbcb;
		align-items: center;
		justify-content: center;
	}
	#login{
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
