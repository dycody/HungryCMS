<template>
	<div>
    <el-row class="courierInf" :gutter="20">
      <div class="courierInf-item"><span>您好，外卖员</span></div>
    </el-row>

    <el-row :gutter="20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="商家名称" prop="mergant.mName" align="center" width="100"/>
        <el-table-column label="商家电话" prop="mergant.mTel" align="center" width="80"/>
        <el-table-column label="商家地址" prop="mergant.location" align="center" width="80"/>
        <el-table-column label="收货人姓名" prop="recTel" align="center" width="180"/>
        <el-table-column label="收货人联系方式" prop="recName" align="center" width="120"/>
        <el-table-column label="收货地址" prop="location" align="center" width="120"/>
        <el-table-column label="配送状态" align="center" width="120">
          <template slot-scope="scope" >
            <div v-if="scope.row.reach === '0'">未送达</div>
            <div v-else>已送达</div>
          </template>
        </el-table-column>
        <el-table-column label="确认送达" align="center" width="140">
          <template slot-scope="scope">
            <el-button @click="confirmReach(scope.row)" type="success" icon="el-icon-check" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
  </div>
</template>

<script>
  export default {
    methods:{
      confirmReach(row) {
        this.$confirm('此操作将确认送达, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('/hungry/order/o_cConfirmOrder',{id:row.id}).then((response) => {
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
    data() {
      return{
        userId: 31,
        itemTotal: 1,
        tableData: [],

      }
    },
    mounted() {
      this.$axios({
        method: 'post',
        url: '/hungry/order/o_cGetAllOrder',
        data:{
          uId:this.userId},
        }).then((response) => {
          console.log(response);
          if(response.data.success === true){
            this.tableData = response.data.data;
            this.itemTotal = response.data.data.total;
          }else{
            this.$message.error(response.data.errMsg);
          }
        }).catch((error) => {
          console.log(error);
          this.$message.warning('服务异常！');
        });
      }
    }
  </script>


  <style scoped>
  .courierInf-item {
    margin: 30px;
  }
  .courierInf-item span {
    font-weight: 700;
  }
</style>