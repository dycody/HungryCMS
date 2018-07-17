<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="订单详情">
      <router-link to="/client/order" slot="left">
        <mt-button icon="back"/>
      </router-link>
    </mt-header>

    <div class="cell-item">
      <mt-cell>
        <div slot="title" class="order-info">
          <div class="order-logo">
            <img src="@/assets/img/mergant-logo.jpeg"/>
          </div>
          <div class="order-status">
            <h1 v-if="order.reach==='1'">订单已送达<img src="@/assets/icon/icon-center.png"></h1>
            <h1 v-else-if="order.reach==='2'">订单已取消<img src="@/assets/icon/icon-center.png"></h1>
            <h1 v-else>订单配送中<img src="@/assets/icon/icon-center.png"></h1>
            <p>感谢你对hungry的信任，期待再次光临</p>
          </div>
          <div class="order-button">
            <mt-button size="small" type="primary" v-if="order.reach==='1'">再来一单</mt-button>
            <mt-button size="small" type="primary" disabled v-else-if="order.reach==='2'">订单已经取消</mt-button>
            <mt-button @click="confirmDelete(order.id)" size="small" type="primary" v-if="order.reach==='0'">取消订单</mt-button>
          </div>
          <div class="order-margent">
            <span class="logo"><img src="@/assets/img/mergant-logo.jpeg"/></span>
            <span class="name">{{order.mergant.mName}}</span>
            <span class="right"><img src="@/assets/icon/icon-center.png" width="10px"></span>
          </div>
        </div>
      </mt-cell>
    </div>
    <div class="cell-item">
      <mt-cell v-for="(item,index) in orderInfo" :key="index">
        <div class="order-list" slot="title">
          <span class="name">{{item.foodName}}</span>
          <span class="number">X{{item.num}}</span>
          <span class="menoy">¥{{(item.num*item.price).toFixed(2)}}</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">配送费</span>
          <span class="number"></span>
          <span class="menoy">¥{{order.addPrice}}</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">合计</span>
          <span class="number"></span>
          <span class="menoy"><h3 style="margin: 0">¥{{order.realPrice}}</h3></span>
        </div>
      </mt-cell>
    </div>
    <div class="cell-item">
      <mt-cell><div slot="title" class="dist-info-title">配送信息</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">下单时间：{{order.createTime}}</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">取货地址：{{order.locationFrom}}</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">送货地址：{{order.locationTo}}</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">配送员：{{order.courier.name}}</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">收货人：{{order.recName}}</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">收货电话：{{order.recTel}}</div></mt-cell>
    </div>

  </div>
</template>

<script>
import {formatDate} from '@/r/js/data.js';
export default {
  data() {
    return {
      userInfo: {},
      order: {},
      orderInfo: [],
    }
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    }
  },
  methods: {
    getOrderById(orderId) {
      this.$axios({
        method: 'post',
        url: '/hungry/order/o_getOrderById',
        data:{
          orderId:orderId,
        },
        }).then((response) => {
          if(response.data.success === true){
            this.order=response.data.data.order;
            this.orderInfo=response.data.data.orderInfList;
            console.log(this.order);
            console.log(this.orderInfo);
          }else{
            this.$message.error(response.data.errMsg);
          }
        }).catch((error) => {
          console.log(error);
          this.$message.warning('服务异常！');
        });
    },
    confirmDelete(oId) {
      this.$confirm('此操作将取消订单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/hungry/order/o_deleteOrder',{id:oId}).then((response) => {
          if(response.data.success === true){
            this.$message.success('删除成功!');
            this.$router.go(0);
          }else{
            this.$message.error(response.data.errMsg);
          }
        }).catch((error) => {
          console.log(error);
          this.$message.warning('服务异常！');
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });          
      });
    },
  },
  mounted() {
    this.getOrderById(this.$route.params.id);
    if(Boolean(sessionStorage.getItem("UserInfo"))){
      this.userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
    }
  }
}
</script>

<style scoped>
.dist-info-title {
  font-weight: 700;
  font-size: 5vw;
}
.order-list {
  padding: 7px;
  border-bottom: 1px solid #eee;
}
.order-list .name {
  width: 65vw;
  max-width: 65vw;
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #333;
}
.order-list .number {
  width: 10vw;
  display: inline-block;
  color: #151515;
  flex: 1;
  text-align: right;
}
.order-list .menoy {
  width: 10vw;
  display: inline-block;
  flex: 1;
  text-align: right;
}

.cell-item {
  margin-top: 10px;
}
.view {
  padding-left: 5px;
  padding-right: 5px; 
}
.order-info {
  text-align: center;
}
.order-info .order-logo img {
  height: 25vw;
  width: 25vw;
  border-radius: 50%;
  border: 1px solid #eee;
}
.order-info .order-status h1 {
  font-size: 6vw;
  margin-bottom: 0px;
}
/*.order-info .order-status h1 img{
  display: inline;
}*/
.order-info .order-status p {
  font-size: 2vw;
  margin-top: 10px;
  color: #999;
}
.order-info .order-button {
  border-bottom: 1px solid #eee;
  padding-bottom: 5px;
  margin-bottom: 5px; 
}
.order-info .order-margent {
  text-align: left;
  padding: 7px 10px;

}
.order-info .order-margent .logo img{
  height: 7vw;
  width: 7vw;
}
.order-info .order-margent .right{
  float: right;
}


</style>