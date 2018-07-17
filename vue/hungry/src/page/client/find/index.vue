<template>
  <div class="view">
    <mt-header class="header blue-background" fixed title="发现">
      <router-link to="/find" slot="left">
        <mt-button icon="back"/>
      </router-link>
    </mt-header>
    <img src="@/assets/img/find.png"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selected:"",
    }
  }
}
</script>

<style scoped>
.view {
  padding-bottom: 130px;
}
</style>