<template>
  <div>
    <mt-header class="header blue-background" fixed title="订单详情">
      <router-link to="/courier/order" slot="left">
        <mt-button icon="back"></mt-button>
      </router-link>
    </mt-header>
    <div class="order-info">
      <div class="order-item">
        <span class></span>
      </div>
      <mt-field label="取货地址" type="textarea" rows="4" :value="order.locationFrom" disabled></mt-field>
      <mt-field label="送货地址" type="textarea" rows="4" :value="order.locationTo" disabled></mt-field>
      <mt-field label="下单时间" disabled :value="order.createTime"></mt-field>
      <mt-field sty label="收货人" disabled :value="order.recName"></mt-field>
      <mt-field sty label="收货人手机" disabled :value="order.recTel"></mt-field>
      <mt-field sty label="店家名称" disabled :value="order.mergant.mName"></mt-field>
      <mt-field sty label="店家手机" disabled :value="order.mergant.mTel"></mt-field>
      <mt-button @click="confirmOrder" style="margin-top: 20px;" type="primary" size="large" v-if="order.reach==='0'">确认送达</mt-button>
      <mt-button @click="cancelOrder" style="margin-top: 5px" type="danger" size="large" v-if="order.reach==='0'">订单取消</mt-button>
    </div>
  </div>
</template>

<script>
import { MessageBox } from 'mint-ui';
export default {
  data() {
    return {
      courierInfo: {},
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
    confirmOrder() {
      MessageBox.confirm('是否确认,若没有送达,可能导致客户投诉!!').then(action => {
        this.confirmSend(this.order.id);
      });
    },
    cancelOrder() {
      MessageBox.confirm('是否确认,若无故取消,可能导致客户投诉!!').then(action => {
        this.confirmDelete(this.order.id);
      });
      
    },
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
    confirmDelete(oId) {
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
    }
  },
  mounted() {
    if(Boolean(sessionStorage.getItem("CourierInfo"))){
      this.getOrderById(this.$route.params.id);
      if(Boolean(sessionStorage.getItem("UserInfo"))){
        this.userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
      }
    }
  }
}
</script>

<style scoped>
.order-info {
  padding: 70px 10px;
}

</style>