<template>
  <el-menu
    default-active="news" router 
    class="el-menu-vertical-demo"
    active-text-color="#409EFF"
    :unique-opened=true
    @open="handleOpen"
    @close="handleClose">
    <el-menu-item index="/mergant/detail">
      <i class="el-icon-tickets"></i>
      <span slot="title">商品管理</span>
    </el-menu-item>
    <el-menu-item index="/mergant/order">
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