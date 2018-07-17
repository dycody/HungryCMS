<template>
  <div class="view">
    <div class="login-title">骑手登录</div>
    <add-courier/>
  </div>

</template>

<script>
import addCourier from '@/components/admin/courier/addCourierDialog'
export default {
  components: {
    addCourier,
  },
}
</script>

<style scoped>
.view {
  padding: 50px 10px;
  padding-right: 50px;
}
.login-title {
  text-align: center;
  font-weight: 700;
  font-size: 40px;
  letter-spacing: 8px;
  color: #3376ba;
  margin-bottom: 30px;
}
</style>