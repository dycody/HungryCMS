<template>
  <div>
    <router-view/>
    <mt-tabbar fixed v-model="selected">
      <mt-tab-item id="home">
        <img slot="icon" src="@/assets/icon_home.png">
        外卖
      </mt-tab-item>
      <mt-tab-item id="order">
        <img slot="icon" src="@/assets/icon_order.png">
        订单
      </mt-tab-item>
      <mt-tab-item id="find">
        <img slot="icon" src="@/assets/icon_find.png">
        发现
      </mt-tab-item>
      <mt-tab-item id="mine">
        <img slot="icon" src="@/assets/icon_mine.png">
        我的
      </mt-tab-item>
    </mt-tabbar>  
  </div>
</template>

<script>
export default {

  data() {
    return {
      selected: this.$route.fullPath.match(/\/client\/(\S*)/)===null?'home':this.$route.fullPath.match(/\/client\/(\S*)/)[1],
    }
  },
  methods: {

  },
  watch: {
    selected(curVal,oldVal) {
      this.$router.push({path:'/client/'+this.selected});
    }
  },
}
</script>

<style>
body {
  background-color: #f5f5f5;
}
.view {
  padding-top: 44px; 
  background-color: #f5f5f5;
  padding-bottom: 100px;
}
.blue-background{
  background-image: linear-gradient(90deg,#0af,#0085ff);
}
.header {
  font-weight: 700; 
  font-size: 1em;
  height: 44px;
  text-overflow: ellipsis;
}
</style>