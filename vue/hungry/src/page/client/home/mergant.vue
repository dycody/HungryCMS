<template>
  <mt-tab-container v-model="active">
    <mt-tab-container-item id="mergant">

      <div class="view">
        <mt-header class="header blue-background" fixed title="店铺详情">
          <router-link to="/client/home" slot="left">
            <mt-button icon="back"/>
          </router-link>
        </mt-header>
        <img src="@/assets/img/margent-header.png" width="100%">
        <div class="cell-item">
          <div slot="title" class="order-info">
            <div class="order-logo">
              <img src="@/assets/mine_log.png"/>
            </div>
            <div class="order-status">
              <h1>{{mergantInf.mName}}<img src="@/assets/icon/icon-center.png"/></h1>
              <p>爱你么么哒~啾咪~</p>
            </div>
          </div>
        </div>
        <mt-navbar v-model="selected" style="margin: 5px 0">
          <mt-tab-item id="1">点餐</mt-tab-item>
          <mt-tab-item id="2">评价</mt-tab-item>
          <mt-tab-item id="3">商家</mt-tab-item>
        </mt-navbar>

        <mt-tab-container v-model="selected">

          <mt-tab-container-item id="1">
            <mt-cell v-for="(food, index) in foodList" :key="index">
              <div slot="title" class="food-item">
                <img src="@/assets/img/food-example.jpeg"/>
                <div class="food-item-info">
                  <span class="name">{{food.name}}</span>
                  <span class="value">￥{{food.cutPrice}} <span>￥{{food.yuanPrice}}</span></span>
                  <div class="number">
                    <img class="sub" src="@/assets/icon/jianhao.svg" @click="subFoodNum(food,index)"/>
                    <span>{{food.foodNum}}</span>
                    <img class="plus" src="@/assets/icon/jiahao-full.svg" @click="addFoodNum(food,index)"/>
                  </div>
                </div>
              </div>
            </mt-cell>
            <div class="shopping-bar" v-if="selected === '1'">
              <span>起送￥{{mergantInf.minPrice}} 配送费￥1</span>
              <span class="total-money">合计<span>￥{{totalMoney}}</span></span>
              <mt-button type="primary" :disabled="isSellable" @click="active='order'" size="small">
                立即购买
              </mt-button>
            </div>
          </mt-tab-container-item>
          <mt-tab-container-item id="2">
            <!-- <mt-cell v-for="n in 4" :title="'测试 ' + n" /> -->
          </mt-tab-container-item>
          <mt-tab-container-item id="3">
            <!-- <mt-cell v-for="n in 6" :title="'选项 ' + n" /> -->
          </mt-tab-container-item>
        </mt-tab-container>

      </div>
    </mt-tab-container-item>
    <mt-tab-container-item id="order">
      <div class="view">
        <mt-header class="header blue-background" fixed title="订单详情">
          <mt-button icon="back" slot="left" @click="active='mergant'"/>
        </mt-header>
        <div class="cell-item">
          <mt-cell v-for="(item, index) in foodList" v-if="item.foodNum !== 0" :key="index">
            <div class="order-list" slot="title">
              <span class="name">{{item.name}}</span>
              <span class="number">X{{item.foodNum}}</span>
              <span class="menoy">¥{{(item.foodNum*item.cutPrice).toFixed(2)}}</span>
            </div>
          </mt-cell>
          <mt-cell>
            <div class="order-list" slot="title">
              <span class="name">配送费</span>
              <span class="number"></span>
              <span class="menoy">¥{{mergantInf.addPrice}}</span>
            </div>
          </mt-cell>
          <mt-cell>
            <div class="order-list" slot="title">
              <span class="name">合计</span>
              <span class="menoy"><h3 style="margin: 0">¥{{totalMoney}}</h3></span>
            </div>
          </mt-cell>
        </div>
        <div class="cell-item">
          <mt-cell><div slot="title" class="dist-info-title">配送信息</div></mt-cell>
          <mt-cell>
            <el-form :rules="rules" style="margin-top: 20px" ref="orderform" :model="orderInf" label-width="72px" slot="title">
              <el-form-item label="收货人" prop="recName">
                <el-input v-model="orderInf.recName" placeholder="请输入称呼"></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="recTel">
                <el-input v-model="orderInf.recTel" placeholder="请输入手机"></el-input>
              </el-form-item>
              <el-form-item label="地址" prop="locationTo">
                <el-input v-model="orderInf.locationTo" placeholder="请输入收货地址"></el-input>
              </el-form-item>
            </el-form>

          </mt-cell>
          <!-- <mt-field label="收货人" placeholder="请输入称呼" v-model="name"></mt-field>
          <mt-field label="手机号" placeholder="请输入手机号" type="tel" v-model="tel"></mt-field>
          <mt-field label="地址" placeholder="地址" type="textarea" rows="4" v-modal="location"></mt-field> -->
        </div>
        <mt-button type="danger" size="large" style="position: fixed; bottom: 100px;right: 0px" @click="onSumbit('orderform')">
          确认下单
        </mt-button>

      </div>

    </mt-tab-container-item>
  </mt-tab-container>
</template>

<script>
  import { Toast } from 'mint-ui';
  export default {
    data() {
      return {
        active: "mergant",
        selected: "1",
        mergantInf: {
          region: {},
        },
        userInfo:{
          account:{
            username:"用户名",
          }
        },
        itemTotal: 1,
        foodList: [],
        userId: this.$route.params.id,
        formInline: {
          name: '',
        },
        isSellable: true,
        orderInf: {
          recName: "",
          recTel: "",
          locationFrom: "",
          locationTo: "",
          foodList: [],
        },
        rules: {
          recName: [{required: true, message: '请输入称呼', trigger:'blur'}],
          recTel: [{required: true, message: '请输入手机', trigger:'blur'},{pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/, trigger:'blur', message: '请输入手机格式错误'}],
          locationTo: [{required: true, message: '请输入地址', trigger:'blur'}],
        }
      }
    },
    computed: {
      totalMoney: function() {
        let money = 0;
        for (var i = this.foodList.length - 1; i >= 0; i--) {
          money = money + this.foodList[i].foodNum * parseFloat(this.foodList[i].cutPrice);
        }
        money += parseFloat(this.mergantInf.addPrice);
        return money.toFixed(2);
      },

    },
    watch: {
      totalMoney() {
        if(this.totalMoney-parseFloat(this.mergantInf.addPrice) >= parseFloat(this.mergantInf.minPrice)
         && this.totalMoney > parseFloat(this.mergantInf.addPrice)){
          this.isSellable = false;
      }else {
        this.isSellable = true;
      }
    }
  },
  methods: {
    onSumbit(formName) {
      var foodlist = new Array();
      for (var i = this.foodList.length - 1; i >= 0; i--) {
        if(this.foodList[i].foodNum !== 0){
          foodlist.push({
            fname:this.foodList[i].name,
            fnum:this.foodList[i].foodNum,
            fvalue: this.foodList[i].cutPrice,
          });
        }
      }
      this.orderInf.locationFrom = this.mergantInf.location;
      this.orderInf.mergantId = this.mergantInf.uId;
      this.orderInf.userId = this.userInfo.uId;
      this.orderInf.realPrice = this.totalMoney;
      this.orderInf.addPrice = this.mergantInf.addPrice;
      this.orderInf.foodList = foodlist;
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios({
            method: 'post',
            url: '/hungry/order/o_add',
            data: this.orderInf,
          }).then((response) => {
            var data = response.data;
            if(data.success === true){
              Toast('提交订单成功');
              this.$router.push({path:'/client/order'});
            }else {
              Toast({message: response.data.errMsg,
                position: 'bottom'});
            }
          }).catch((error) => {
            console.log(error);
            Toast('服务器异常！');
          });
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },
    subFoodNum(food, index) {
      if(food.foodNum){
        food.foodNum=food.foodNum-1;
        this.$set(this.foodList, index, food);
      }
    },
    addFoodNum(food,index) {
      food.foodNum=food.foodNum+1;
      this.$set(this.foodList, index, food);
    },
    getMergantInf(userId) {
      this.$axios({
        method: 'post',
        url: '/hungry/mergant/o_getMergantById',
        data: {id:userId},
      }).then((response) => {
        if(response.data.success === true){
          this.mergantInf = response.data.data;
          // console.log(this.mergantInf);
          delete this.mergantInf.account;
          if(this.$refs['mergantInf'] != null){
            this.$refs['mergantInf'].resetFields();
          }
        }else {
          this.$message.error(response.data.errMsg);
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务器异常！');
      });
    },
    getAllFood(userId){
      this.$axios({
        method: 'post',
        url: '/hungry/food/o_getAllFood',
        data:{
          uId:userId,
          name:this.formInline.name
        },
      }).then((response) => {
        if(response.data.success === true){
          this.foodList = response.data.data.list;
          this.itemTotal = response.data.data.total;
          for (var i = this.foodList.length - 1; i >= 0; i--) {
            this.foodList[i].foodNum=0;
          }
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务异常！');
      });
    }
  },
  mounted() {
    this.getMergantInf(this.$route.params.id);
    this.getAllFood(this.$route.params.id);
    if(Boolean(sessionStorage.getItem("UserInfo"))){
      this.userInfo = JSON.parse(sessionStorage.getItem("UserInfo"));
    }
  }

}

</script>

<style scoped>
.shopping-bar {
  position: fixed;
  bottom: 55px;
  /*right: 10px;*/
  background-color: white;
  width: 120%;
  padding-left: 15px;
}
.shopping-bar .total-money {
  font-size:20px;
  font-weight: 700; 
  color: #4b4b52;
  padding-left: 20px;
}
.shopping-bar .total-money span{
  color: #e6a104;
}

.food-item {
  margin: 5px;
}
.food-item img {
  height: 20vw;
  width: 20vw;
  display: inline;
  float: left;
}
.food-item .food-item-info {
  float: left;
  position: relative;
  width: 70vw;
  height: 20vw;
}
.food-item .food-item-info .name {
  position: absolute;
  margin-top: 14px;
  left: 30px;
  font-weight: 600;
  font-size: 6vw;
  top: 0px;
}
.food-item .food-item-info .value {
  position: absolute;
  left: 35px;
  bottom: 15px;
  font-size: 20px;
  font-weight: 700;
  color: #e6a104;
}
.food-item .food-item-info .value span{
  font-size: 14px;
  /*font-weight: 700;*/
  text-decoration: line-through;
  color: black;
}
.food-item .food-item-info .number {
  position: absolute;
  right: 0px;
  bottom: 15px;
  width: 20vw;
  text-align: center;
  line-height: 22px;
}
.food-item .food-item-info .number img {
  width: 6vw;
  height: 6vw;
}
.number .plus {
  float: right;
}
.number span{
  border-top: 10px;
}
.view {
  padding-left: 5px;
  padding-right: 5px; 
}
.order-info {
  text-align: center;
  background: #FFFFFF;
  position: relative;
  margin: 0;
  padding: 10px 5px; 
}
.order-info .order-logo img {
  height: 20vw;
  width: 20vw;
  position: absolute;
  left: 38vw;
  bottom: 20vw;
  border: 3px solid black; 
}
.order-info .order-status h1 {
  font-size: 6vw;
  margin-bottom: 0px;
}
.order-info .order-status h1 img{
  display: inline;
  line-height: 20px;
}
.order-info .order-status p {
  font-size: 2vw;
  margin-top: 10px;
  color: #999;
}



 /**
  *order css
  */
.cell-item {
  margin-top: 5px;
}
.dist-info-title {
  font-weight: 700;
  font-size: 5vw;
}
.order-list {
  padding: 7px;
  /*line-height: 30px;*/
  /*border-bottom: 1px solid #eee;*/
}
.order-list .name {
  width: 56vw;
  max-width: 56vw;
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
  width: 20vw;
  display: inline-block;
  flex: 1;
  text-align: right;
}

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