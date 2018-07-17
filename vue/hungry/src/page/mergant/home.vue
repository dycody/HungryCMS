<template>
  <el-container height="100%">
    <el-header><headTop/></el-header>
    <el-container height="100%">
      <el-aside width="22%"><sideNav/></el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import sideNav from '@/components/mergant/sideNav'
import headTop from '@/components/mergant/headTop'
export default {
  name: 'home',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  components: {
    sideNav,headTop,
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-breadcrumb {
    background-color: #EFF2F7;
    padding: 10px;
    margin-bottom: 10px;
  }
  .el-button-group {
    margin-top: 15px;
    float: right;
  }
  .el-button-group .el-button {

  }
  .el-pagination {
    float: right;
    margin-top: 10px;
  }
</style>
