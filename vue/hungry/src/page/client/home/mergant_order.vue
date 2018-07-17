<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="订单详情">
      <router-link to="/client/mergant" slot="left">
        <mt-button icon="back"/>
      </router-link>
    </mt-header>
    <div class="cell-item">
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">鸭肫</span>
          <span class="number">X1</span>
          <span class="menoy">¥4</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">鸭肫</span>
          <span class="number">X1</span>
          <span class="menoy">¥4</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">鸭肫1111111111111111111111111111111111</span>
          <span class="number">X1</span>
          <span class="menoy">¥4</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">配送费</span>
          <span class="number"></span>
          <span class="menoy">¥4.5</span>
        </div>
      </mt-cell>
      <mt-cell>
        <div class="order-list" slot="title">
          <span class="name">合计</span>
          <span class="number"></span>
          <span class="menoy"><h3 style="margin: 0">¥45</h3></span>
        </div>
      </mt-cell>
    </div>
    <div class="cell-item">
      <mt-cell><div slot="title" class="dist-info-title">配送信息</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">下单时间:</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">送货时间</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">送货地址</div></mt-cell>
      <mt-cell><div slot="title" class="dist-info-item">配送员</div></mt-cell>
    </div>

  </div>
</template>

<script></script>

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