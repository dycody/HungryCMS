<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="订单状态">
        <mt-button icon="back" @click="logout" slot="left">登出</mt-button>
    </mt-header>
    <div class="cell-item" v-for="(item,index) in orderList" @click="goOrderDetail(item)">
      <mt-cell-swipe class="cell-item-detail">
        <div slot="title" class="order-card">
          <div class="mergant">
            <div class="mergant-logo">
              <img src="@/assets/mine_log.png"/>
            </div>
            <div class="mergant-info">
              <div class="mergant-name">
                <div class="mergant-name-status">
                  <span class="name">{{item.mergant.mName}}</span>
                  <span class="status" v-if="item.reach === '1'">订单已送达</span>
                  <span class="status" v-else-if="item.reach === '2'">订单已取消</span>
                  <span class="status" v-else>订单配送中</span>
                </div>
                <span class="date">{{item.createTime|formatDate}}</span>
              </div>
              <div class="order-info">
                <span class="order-info-left">{{item.locationTo}}</span>
                <span class="order-info-right">¥{{item.realPrice}}</span>
              </div>
            </div>
          </div>
          <div class="card-button">
            <mt-button type="primary" size="small" plain>查看详情</mt-button>
            <mt-button type="danger" size="small" plain v-if="item.reach === '0'" @click="confirmOrder">确认送达</mt-button>
          </div>
        </div>
      </mt-cell-swipe>
    </div>
  </div>
</template>

<script>
import {formatDate} from '@/r/js/data.js';
export default {
  data() {
    return {
      courierInfo: {},
      orderList: {
        mergant:{},
        courier:{},
      },
    }
  },
  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    }
  },
  methods: {
    logout() {
      sessionStorage.removeItem("CourierInfo");
      this.$router.push('/courier/login');
    },
    confirmOrder() {
      MessageBox.confirm('是否确认,若没有送达,可能导致客户投诉!!').then(action => {
        this.confirmSend(this.order.id);
      });
    },
    confirmSend(oId) {
      this.$axios.post('/hungry/order/o_confirmOrder',{id:oId}).then((response) => {
        if(response.data.success === true){
          this.$message.success('送货成功!');
          this.$router.go(0);
        }else{
          this.$message.error(response.data.errMsg);
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务异常！');
      });
    },
    goOrderDetail(order){
      this.$router.push('/courier/order/detail/'+order.id);
    },
    getOrderByCourierId(courierId) {
      this.$axios({
        method: 'post',
        url: '/hungry/order/o_getAllOrder',
        data:{
          pageNum:this.currentPage,
          courierId:courierId,
        },
        }).then((response) => {
          if(response.data.success === true){
            this.orderList = response.data.data.list;
            this.currentPage = response.data.data.pageNum;
            this.pageSize = response.data.data.pageSize;
            this.itemTotal = response.data.data.total;
            // console.log(this.orderList);
          }else{
            this.$message.error(response.data.errMsg);
          }
        }).catch((error) => {
          console.log(error);
          this.$message.warning('服务异常！');
        });
    }
  },
  mounted() {
    if(Boolean(sessionStorage.getItem("CourierInfo"))){
      this.courierInfo = JSON.parse(sessionStorage.getItem("CourierInfo"));
      this.getOrderByCourierId(this.courierInfo.uId);
    }
  }
}
</script>

<style scoped>
body {
  background-color: #f5f5f5;
}
.view {
  padding-top: 44px; 
  background-color: #f5f5f5;
  padding-bottom: 100px;
}
.header {
  font-weight: 700; 
  font-size: 1em;
  height: 44px;
  text-overflow: ellipsis;
}
.order-card .order-info {
  margin-top: 4vw;
  margin-bottom: 4vw;
}
.order-card .order-info .order-info-left {
  max-width: 42.666667vw;
  display: inline-block;
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
.order-card .mergant-info .status {
  float: right;
  font-size: 3.3vw;
  line-height: 6vw;
}
.order-card .mergant-info .date {
  font-size: .293333rem;
  color: #999;
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
  height: 8.6vw;
  width: 8.6vw;
  padding-right: 4vw;
  float: left;
}
.order-card .mergant-logo img{
  display: block;
  height: 9vw;
  width: 9vw;
  border-radius: 50%;
  border: 1px solid #eee;
}
.order-card .mergant-info {

  display: block;
  width: 80vw;
  height: 10vw;
  float: left;
}
  
</style>