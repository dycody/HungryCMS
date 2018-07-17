<template>
	<div>
   <el-row :gutter="20">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/mergant' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/mergant/mergant'}">商家管理</el-breadcrumb-item>
    </el-breadcrumb>
  </el-row>
  <el-row class="mergantInf" :gutter="20">
    <el-col :span="10">
     <div>
      <div class="mergantInf-item"><span>账号名</span>：{{mergantInf.userName}}</div>
      <div class="mergantInf-item"><span>商家名</span>：{{mergantInf.mName}}</div>
      <div class="mergantInf-item"><span>地址</span>：{{mergantInf.location}}</div>
      <div class="mergantInf-item"><span>联系电话</span>：{{mergantInf.mTel}}</div>
      <div class="mergantInf-item"><span>老板名</span>：{{mergantInf.bossName}}</div>
      <div class="mergantInf-item"><span>老板电话</span>：{{mergantInf.bossTel}}</div>
      <div class="mergantInf-item"><span>所属区域</span>：{{mergantInf.region.name}}</div>
    </div>
  </el-col>
  <el-col :span="10">
   <div>
    <div class="mergantInf-item"><span>开门时间</span>：{{mergantInf.opentime|formatDate2}}</div>
    <div class="mergantInf-item"><span>打烊时间</span>：{{mergantInf.closetime|formatDate2}}</div>
    <div class="mergantInf-item"><span>商家类型</span>：{{mergantInf.type}}</div>
    <div class="mergantInf-item"><span>所属区域</span>：{{mergantInf.region.name}}</div>
    <div class="mergantInf-item"><span>起送价</span>：{{mergantInf.addPrice}}</div>
    <div class="mergantInf-item"><span>配送费</span>：{{mergantInf.minPrice}}</div>
    <div class="mergantInf-item"><span>注册时间</span>：{{mergantInf.createTime|formatDate}}</div>
  </div>
</el-col>
<el-col :span="4">
 <div>
 </div>
</el-col>
</el-row>
<el-row :gutter="20">
  <el-button class="button-addfood" icon="el-icon-plus" type="primary"  @click="addFood()"/>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="商品名" prop="name" align="center" width="100"/>
    <el-table-column label="原价" prop="yuanPrice" align="center" width="80"/>
    <el-table-column label="优惠价" prop="cutPrice" align="center" width="80"/>
    <el-table-column label="商品描述信息" prop="inf" align="center" width="180"/>
    <el-table-column label="图片" prop="picture" align="center" width="120"/>
    <el-table-column label="操作" align="center" width="140">
      <template slot-scope="scope">
        <el-button    @click="editFood(scope.row)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
        <el-button   @click="confirmDelete(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
      </template>
    </el-table-column>
  </el-table>
</el-row>
<el-dialog title="添加商品" :visible.sync="showAddDialog">
  <add-food-dialog :userId.sync="this.userId"/>
</el-dialog>
<el-dialog title="编辑用户" :visible.sync="showEditDialog">
  <edit-food-dialog :foodId.sync="this.foodId"/>
</el-dialog>
</div>
</template>

<script>
  import {formatDate} from '@/r/js/data.js';
  import addFoodDialog from '@/components/admin/food/addFoodDialog';
  import editFoodDialog from '@/components/admin/food/editFoodDialog';
  export default {
    components: {
      addFoodDialog,editFoodDialog,
    },
    filters: {
      formatDate2(time) {
        var date = new Date(time);
        return formatDate(date, 'hh:mm:ss');
      },
      formatDate(time) {
        var date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      }
    },
    methods: {
      editFood(row) {
        this.foodId = row.id;
        this.showEditDialog = true;
         //   this.getUserInf();
       },
       addFood() {
        this.showAddDialog = true;
         //   this.getUserInf();
       },
       getMergantInf(userId) {
        this.$axios({
          method: 'post',
          url: '/hungry/mergant/o_getMergantById',
          data: {id:userId},
        }).then((response) => {
          if(response.data.success === true){
            this.mergantInf = response.data.data;
            this.mergantInf.uId = this.mergantInf.account.id;
            this.mergantInf.userName = this.mergantInf.account.username;
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
      confirmDelete(row) {
        this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('/hungry/food/o_deleteFood',{id:row.id}).then((response) => {
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
      return {
        mergantInfo:{},
        mergantInf: {
          region: {},
        },
        showAddDialog: false,
        showEditDialog: false,
        itemTotal: 1,
        tableData: [],
        userId: '',
        foodId:Number,
        formInline: {
          name: '',
        },
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
      this.getMergantInf(this.userId);
      this.$axios({
        method: 'post',
        url: '/hungry/food/o_getAllFood',
        data:{
          uId:this.userId,
          name:this.formInline.name},
        }).then((response) => {
          if(response.data.success === true){
            this.tableData = response.data.data.list;
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
  .mergantInf {
    margin: 10px 0;
  }
  .mergantInf-item {
    margin: 30px;
  }
  .mergantInf-item span {
    font-weight: 700;
  }
  .button-addfood {
    margin: 0px 30px;
  }
</style>