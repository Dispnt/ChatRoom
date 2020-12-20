<template>
  <div>
    <blog-header></blog-header>
    <hr/>
	
		
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