<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="点击搜索商家">
    </mt-header>
    <div class="nav-foodtype">
      <div><img src="@/assets/img/meishi.jpg"/><p>美食</p></div>
      <div><img src="@/assets/img/wancan.jpg"/><p>晚餐</p></div>
      <div><img src="@/assets/img/shangchao.jpg"/><p>商超</p></div>
      <div><img src="@/assets/img/guoshu.jpg"/><p>果蔬</p></div>
      <div><img src="@/assets/img/yiyao.jpg"/><p>医药</p></div>
      <div><img src="@/assets/img/dapai.jpg"/><p>大牌</p></div>
      <div><img src="@/assets/img/xianhua.jpg"/><p>鲜花</p></div>
      <div><img src="@/assets/img/mala.jpg"/><p>麻辣</p></div>
      <div><img src="@/assets/img/chaocai.jpg"/><p>炒菜</p></div>
      <div><img src="@/assets/img/pisa.jpg"/><p>披萨</p></div>
    </div>

    <div class="cell-item" v-for="item in mergantList" @click="goMergantDetail(item.uId)">
      <mt-cell-swipe class="cell-item-detail">
        <div slot="title" class="order-card">
          <div class="mergant">
            <div class="mergant-logo">
              <img src="@/assets/mine_log.png"/>
            </div>
            <div class="mergant-info">
              <div class="mergant-name">
                <div class="mergant-name-status">
                  <span class="name">{{item.mName}}</span>
                  <span class="type">{{item.type}}</span>
                </div>
                <span class="date">￥{{item.minPrice}}起送 | 配送费￥{{item.addPrice}}</span>
              </div>
              <div class="order-info">
                <span class="order-info-left">满35元赠送啤酒1份</span>
                <span class="order-info-left">满8减7，满36减20，满50减28</span class="order-info-left">
                <!-- <span class="order-info-left2">等2件商品</span> -->
                <!-- <span class="order-info-right">¥20.72</span> -->
              </div>
            </div>
          </div>
          <!-- <div class="card-button">
            <mt-button type="primary" size="small" plain>查看详情</mt-button>
          </div> -->
        </div>
      </mt-cell-swipe>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      value:'',
      mergantList:[],
      formInline:{},
    }
  },
  methods: {
    getPage(pNum) {
      this.$axios({
        method: 'post',
        url: '/hungry/mergant/o_getAllMergant',
        data:{pageNum:pNum,
        mName:this.formInline.mName,
        type:this.formInline.type},
      }).then((response) => {
        if(response.data.success === true){
          this.mergantList = response.data.data.list;
          this.currentPage = response.data.data.pageNum;
          this.pageSize = response.data.data.pageSize;
          this.itemTotal = response.data.data.total;
        }else{
          this.$message.error(response.data.errMsg);
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务异常！');
      });
    },
    goMergantDetail(mergantId){
      this.$router.push({path:'/client/mergant/'+mergantId});
    }
  },
  mounted() {
    this.getPage(1);
  }
}
</script>

<style scoped>
.nav-foodtype {
  height: 40vw;
}
.nav-foodtype div{
  box-sizing: border-box;
  float: left;
  width: 20vw;
  height: 20vw;
  text-align: center;
  line-height: 20vw;
  position: relative;
  background: white;
}
.nav-foodtype div img {
  width: 12vw;
  height: 12vw;
}
.nav-foodtype div p {
  text-align: center;
  margin: 0;
  position: absolute;
  left: 27px;
  top: 14px;
  font-size: 14px;
}

.cell-item {
  margin-top: 10px;
  border-bottom: 1px solid #eeeeee;
}
.cell-item .order-card{
  padding: 2.733333vw 0 0 0vw;
}
.cell-item .order-card .mergant {
  height: 22.5vw;
  border-bottom: 1px solid #eeeeee;
}
.cell-item .card-button {
  padding: 2vw;
  text-align: right;
}
.order-card .mergant-logo{
  height: 11vw;
  width: 11vw;
  padding-right: 4vw;
  float: left;
}
.order-card .mergant-logo img{
  display: block;
  height: 12vw;
  width: 12vw;
  border-radius: 50%;
  border: 1px solid #eee;
}
.order-card .mergant-info {

  display: block;
  width: 80vw;
  height: 10vw;
  float: left;
}
.order-card .order-info {
  margin-top: 2vw;
  margin-bottom: 4vw;
}
.order-card .order-info .order-info-left {
  max-width: 42.666667vw;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 3.5vw;
  color: #666;
  font-weight: 400;
}
.order-card .order-info .order-info-left2 {
  display: inline-block;
  text-overflow: ellipsis;
  font-size: 3.5vw;
  color: #666;
  font-weight: 400;
}
.order-card .order-info .order-info-right {
  float: right;
  font-size: 4;
  font-weight: 700;
}
.order-card .mergant-name {
  padding-bottom: 2.2vw;
  border-bottom: 1px solid #eeeeee;
}
.order-card .mergant-name-status {
  padding-bottom: 1vw;

}

.order-card .mergant-info .name {
  font-size: 5vw;
  font-weight: 600; 
}
.order-card .mergant-info .type {
  font-size: 2vw;
  /*font-weight: 600; */
  border: 1px solid black;
  border-radius: 12%
}
.order-card .mergant-info .status {
  float: right;
  font-size: 3.3vw;
  line-height: 6vw;
}
.order-card .mergant-info .date {
  font-size: .293333rem;
  color: #999;
}
</style>