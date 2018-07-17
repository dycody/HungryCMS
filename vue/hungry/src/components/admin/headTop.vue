<template>
  <div class="headtop">
    <div class="headtop-left">外卖管理</div>
    <div class="headtop-right">
      <el-dropdown @command="handleCommand">
        <label><i class="el-icon-setting" style="margin-right: 15px"></i>
        {{adminName}}</label>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看详情</el-dropdown-item>
          <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'headTop',
  data() {
    return {
      adminInfo:{},
      adminName:'管理员',
    }
  },
  methods: {
    handleCommand(command){
      // console.log(command);
      if(command==="loginout"){
        this.loginOut();
      }
    },
    loginOut() {
      localStorage.removeItem("AdminInfo");
      this.$router.push({path:"/admin/login"});
    }
  },
  created() {
    if(Boolean(localStorage.getItem("AdminInfo"))) {
      this.adminInfo = JSON.parse(localStorage.getItem("AdminInfo"));
      this.adminName = this.adminInfo.username;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.headtop {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
.headtop-left {
  float: left;
}
.headtop-right {
  float: right;
}
</style>
