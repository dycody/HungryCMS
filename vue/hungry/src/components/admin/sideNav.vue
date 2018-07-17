<template>
  <el-menu
    default-active="news" router 
    class="el-menu-vertical-demo"
    active-text-color="#409EFF"
    :unique-opened=true
    @open="handleOpen"
    @close="handleClose">
    <el-menu-item index="/admin/mergant">
      <i class="el-icon-tickets"></i>
      <span slot="title">商家管理</span>
    </el-menu-item>
    <el-menu-item index="/admin/user">
      <i class="el-icon-tickets"></i>
      <span slot="title">用户管理</span>
    </el-menu-item>
    <el-menu-item index="/admin/courier">
      <i class="el-icon-goods"></i>
      <span slot="title">外卖员管理</span>
    </el-menu-item>
    <el-menu-item index="/admin/order">
      <i class="el-icon-view"></i>
      <span slot="title">订单管理</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
.el-submenu .el-menu-item{
  min-width: 150px;
}

</style>