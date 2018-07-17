<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="账户信息">
      <router-link to="/client/mine" slot="left">
        <mt-button icon="back"/>
      </router-link>
    </mt-header>
    <mt-cell title="头像" is-link>
    </mt-cell>
    <mt-cell :title="userInfo.account.username" is-link>
    </mt-cell>
    <p>账号设置</p>
    <mt-cell title="手机" is-link>
      <span slot="title">手机 : {{userInfo.tel}}</span>
      <img slot="icon" src="@/assets/icon/shouji-blue.svg" width="24" height="24">
    </mt-cell>
    <p>安全设置</p>
      
    <mt-cell title="登录密码" is-link>
    </mt-cell>
    <mt-button style="position: fixed; bottom: 100px;" type="danger" size="large" @click="logout">账户退出</mt-button>
  </div>
</template>

<script>
import { MessageBox } from 'mint-ui';
export default {
  data() {
    return {
      userInfo:{
        account:{
          username:"用户名",
        }
      },
    }
  },
  methods: {
    logout() {
      MessageBox.confirm('确定退出账户?').then(action => {
        sessionStorage.removeItem('UserInfo');
        this.$router.push('/client/login');
      });
    },
  },
  mounted() {
    if(Boolean(sessionStorage.getItem("UserInfo"))){
      this.userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
    }
  }
}
</script>

<style scoped>

p {
  margin-left: 20px;
  font-size: 8px;
  font-color: ;
}

.header {
  font-weight: 700; 
  font-size: 1em;
  height: 44px;
  text-overflow: ellipsis;
}
.view {
  padding-top: 44px; 
  background-color: #f5f5f5;
}

</style>