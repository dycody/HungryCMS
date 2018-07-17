<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/courier'}">外卖员管理</el-breadcrumb-item>
    </el-breadcrumb>
  
    <div>
      <el-form :inline="true" :model="formInline" class="demo-form-inline selectForm">
        <el-form-item label="搜索：">
          <el-input v-model="formInline.account" placeholder="账号名"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="formInline.name" placeholder="昵称"></el-input>
        </el-form-item>
      <el-select v-model="formInline.rId" placeholder="所属区域">
        <el-option
        v-for="item in selectRegion"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
  </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getPage(1)">查询</el-button>
        </el-form-item>
      </el-form>
      <el-button class="addBtn" type="primary" icon="el-icon-plus" @click="showAddDialog=true"></el-button>
    </div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="账号名" prop="account.username" align="center" width="150"/>
      <el-table-column label="昵称" prop="name" align="center" width="180"/>
      <el-table-column label="区域" prop="region.name" align="center" width="80"/>
      <el-table-column label="手机" prop="tel" align="center" width="120"/>
      <el-table-column label="注册时间" align="center" width="110">
        <template slot-scope="scope">{{scope.row.createTime|formatDate}}</template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="140">
        <template slot-scope="scope">
          <el-button  @click="editUser(scope.row)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
          <el-button  @click="confirmDelete(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
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
    <el-dialog title="添加外卖员" :visible.sync="showAddDialog">
      <add-courier-dialog/>
    </el-dialog>
    <el-dialog title="编辑外卖员" :visible.sync="showEditDialog">
      <edit-courier-dialog :userId.sync="this.userId"/>
    </el-dialog>
  </div>
</template>

<script>
import {formatDate} from '@/r/js/data.js';
import addCourierDialog from '@/components/admin/courier/addCourierDialog';
import editCourierDialog from '@/components/admin/courier/editCourierDialog';
export default {
  components: {
    addCourierDialog,editCourierDialog,
  },
  methods: {
  	editUser(row) {
      this.userId = row.uId;
      this.showEditDialog = true;
    },
  	handleCurrentChange(val) {
      this.getPage(val);
    },
    getPage(pNum) {
      this.$axios({
        method: 'post',
        url: '/hungry/courier/o_getAllCourier',
        data:{pageNum:pNum,
              account:this.formInline.account,
              name:this.formInline.name,
              rId:this.formInline.rId},
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
    },
    confirmDelete(row) {
      this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/hungry/courier/o_deleteCourier',{id:row.uId}).then((response) => {
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
    onSubmit() {
      console.log('submit!');
    }
  },

  filters: {
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    }
  },
  data() {
    return {
      selectRegion: [],
	    currentPage: 1,
      pageSize: 1,
      itemTotal: 1,
      tableData: [],
      showAddDialog: false,
      showEditDialog: false,
      userId: Number,
      formInline: {
        account: '',
        name: '',
      },
    }
   },
   mounted() {
      this.getPage(1);
      this.$axios.post('/hungry/region/o_getAllRegion',{}).then((response) => {
        if(response.data.success === true){
          this.selectRegion = response.data.data.list;
        }else{
          this.$message.error(response.data.errMsg);
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务器异常！');
      });
  },
}
</script>

<style scoped>
.addBtn {
  float: right;
}
.selectForm {
  float: left;
  margin-left: 20px;
}

.el-form-item {
  margin: 0;
}
</style>