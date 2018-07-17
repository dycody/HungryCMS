<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="我的">
      <router-link to="/client" slot="left">
        <mt-button icon="back"/>
      </router-link>
    </mt-header>

    <div class="header-login blue-background" @click="godetail">
      <div class="header-login-1">
        <img src="@/assets/mine_log.png"/>

      </div>
      <div class="header-login-2">
        <span class="p1">{{userInfo.name}}</span>
        <img src="@/assets/icon/Mobile-phone.svg"><span class="p2">{{userInfo.tel}}</span>
      </div>
      <div class="header-login-3">
        <img src="@/assets/icon/right-more.svg">
      </div>
    </div>
    <div class="nav-select" style="height: 80px;">
      <div>
        <img src="@/assets/icon/menoy.svg"/>
        <p>钱包</p>
      </div>
      <div>
        <img src="@/assets/icon/red-packet.svg"/>
        <p>红包</p>
      </div>
      <div>
        <img src="@/assets/icon/coin.svg"/>
        <p>金币</p>
      </div>
    </div>

    <div class="cell-item">
      <mt-cell title="我的地址" is-link>
        <img slot='icon' src='@/assets/icon/map-location.svg' width="24" height="24"/>
      </mt-cell>
    </div>

    <div class="cell-item">
      <mt-cell title="金币商城" is-link>
        <img slot='icon' src='@/assets/icon/shangcheng.svg' width="24" height="24"/>
      </mt-cell>
      <mt-cell title="分享拿5元现金" is-link>
        <img slot='icon' src='@/assets/icon/liwu.svg' width="24" height="24"/>
      </mt-cell>
    </div>

    <div class="cell-item" style="height: 300px;">
      <mt-cell title="我的客服" is-link>
        <img slot='icon' src='@/assets/icon/kefu.svg' width="24" height="24"/>
      </mt-cell>
      <mt-cell title="下载hungryAPP" is-link>
        <img slot='icon' src='@/assets/icon/logo.svg' width="24" height="24"/>
      </mt-cell>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value:'',
      userInfo:{
        name:'登录/注册',
        tel:'登录后享受更多权限',
      },
    }
  },
  methods: {
    godetail() {
      this.$router.push({path:'/client/mine/account'});
    }
  },
  mounted() {
    if(Boolean(sessionStorage.getItem("UserInfo"))){
      this.userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
    }
    console.log(this.userInfo);
  }
}
</script>

<style scoped>
.cell-item {
  margin-top: 10px;
}

.nav-select > div{
  box-sizing:border-box;
  float: left;
  width: 33.3%;
  text-align: center;
  line-height: 80px;
  height: 80px;
  border: 1px solid #eeeeee;
  border-top: 0px;
  position: relative;
  background-color: white;
}
.nav-select img{
  display: inline;
  height: 30px;
  width: 30px;
}
.nav-select div p {
/*  height: 40px;*/
  position: absolute;
  top: 0;
  width: 100%;
  margin: 49px 0;
  font-size: .32rem;
  line-height: 3.733333vw;
  color: #666;
  font-weight: 700;
}
.header-login {
  box-sizing:border-box;
  height: 110px;
  padding: 25px 15px;
}
.header-login div {
  float: left;
}
.header-login .header-login-2 {
  margin-left: 25px;
  float: left;
}
.header-login .header-login-2 .p1{
  margin: 0;
  color: #FFFFFF;
  font-size: 22px;
  font-weight: 700;
}

.header-login .header-login-2 .p2{
  margin: 8px 0;
  color: #FFFFFF;
  font-size: 10px;
  display: block;
}
.header-login .header-login-2 img{
  width: 20px;
  height: 20px;
  border-radius: 0;
}

.header-login .header-login-3 {
  float: right!important;
  line-height: 80px; 
}
.header-login .header-login-3 img {
  width: 30px;
  height: 30px;
}
/*.header-login-1 {
  width: 60;
}*/
.header-login img {
  height: 60px;
  width: 60px;
  border-radius: 50%;
}
</style>