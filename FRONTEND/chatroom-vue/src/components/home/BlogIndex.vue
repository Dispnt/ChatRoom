<template>
  <div>
    <blog-header></blog-header>
    <hr/>
	
		<!-- <el-aside width="200px">
	      <el-table :data="tableData" style="width: 100%">
	        <div>群成员</div>
	        <el-table-column prop="user_id" width="60"> </el-table-column>
	        <el-table-column prop="user_name" width="80"> </el-table-column>
	      </el-table>
	    </el-aside> -->
    <div>
      这是首页
    </div>
    <hr/>
    <chat-footer></chat-footer>
  </div>
</template>

<script>
import chatHeader from '@/components/common/ChatHeader.vue'
import chatFooter from '@/components/common/ChatFooter.vue'

export default {
  name: 'BlogIndex',
  // chatHeader/blogFooter组件给申明到components里面然后在template里面使用
  components: { chatHeader, chatFooter }
}
</script>