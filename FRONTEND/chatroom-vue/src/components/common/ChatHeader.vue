<template>
    
    	<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
    		<el-menu-item index="1">Chitchat</el-menu-item>
    		<el-submenu index="2">
    			<template #title>Registered User</template>
    			<el-submenu index="2-1">
    				<template #title>小江</template>
    				<el-menu-item index="2-1-1">飞了他</el-menu-item>
    				<el-menu-item index="2-1-2">拉进群</el-menu-item>
    			</el-submenu>
    			<el-submenu index="2-2">
    				<template #title>小澤</template>
    				<el-menu-item index="2-1-1">飞了他</el-menu-item>
    				<el-menu-item index="2-1-2">拉进群</el-menu-item>
    			</el-submenu>
    			<el-submenu index="2-3">
    				<template #title>小民</template>
    				<el-menu-item index="2-1-1">飞了他</el-menu-item>
    				<el-menu-item index="2-1-2">拉进群</el-menu-item>
    			</el-submenu>
    			<el-menu-item index="2-4">管理</el-menu-item>
    		</el-submenu>
    		<el-menu-item index="3">新人登记</el-menu-item>
    		<el-menu-item index="4"><a href="dispnt.com" target="_blank">部署实例</a></el-menu-item>
    	</el-menu>
    
</template>

<script>
export default {
  name: 'ChatHeader'
}
</script>