<template>
  <div>
    <el-row :gutter="20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="配送员昵称" prop="courier.name" align="center" width="180"/>
        <el-table-column label="配送员电话" prop="courier.tel" align="center" width="180"/>
        <el-table-column label="收货人姓名" prop="recTel" align="center" width="180"/>
        <el-table-column label="收货人联系方式" prop="recName" align="center" width="120"/>
        <el-table-column label="发出地址" prop="locationFrom" align="center" width="120"/>
        <el-table-column label="收货地址" prop="locationTo" align="center" width="120"/>
        <el-table-column label="配送状态" align="center" width="120">
          <template slot-scope="scope" >
            <div v-if="scope.row.reach === '0'">未送达</div>
            <div v-else-if="scope.row.reach === '1'">已送达</div>
            <div v-else>已取消</div>
          </template>
        </el-table-column>
        <el-table-column label="取消订单" align="center" width="140">
          <template slot-scope="scope">
            <el-button @click="confirmDelete(scope.row)" type="danger" icon="el-icon-close" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
  <el-pagination
  @current-change="handleCurrentChange"
  :current-page.sync="currentPage"
  :page-size="pageSize"
  layout="total, prev, pager, next, jumper"
  :total="itemTotal">
</el-pagination>
    </el-row>
  </div>
</template>

<script>
  export default {
    methods:{
      handleCurrentChange(val) {
      this.getPage(val);
    },
      confirmDelete(row) {
        this.$confirm('此操作将取消订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('/hungry/order/o_deleteOrder',{id:row.id}).then((response) => {
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
        mergantInfo:{},
        currentPage: 1,
        pageSize: 1,
        userId: 31,
        itemTotal: 1,
        tableData: [],

      }
    },
    created() {
      if(Boolean(localStorage.getItem("MergantInfo"))) {
        this.mergantInfo = JSON.parse(localStorage.getItem("MergantInfo"));
        console.log(this.mergantInfo);
        this.userId = this.mergantInfo.uId;

      }
    },
    mounted() {
      this.$axios({
        method: 'post',
        url: '/hungry/order/o_getAllOrder',
        data:{pageNum:this.currentPage
          ,mergantId:this.userId},
        }).then((response) => {
          if(response.data.success === true){
            this.tableData = response.data.data.list;
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