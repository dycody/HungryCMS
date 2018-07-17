<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mergant'}">商家管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div>
      <el-form :inline="true" :model="formInline" class="demo-form-inline selectForm">
        <el-form-item label="搜索：">
          <el-input v-model="formInline.mName" placeholder="商家名"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-select v-model="formInline.type" placeholder="商家类型">
            <el-option
            v-for="item in selectType"
            :key="item"
            :label="item"
            :value="item">
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
    <el-table-column label="商家名" prop="mName" align="center" width="80"/>
    <el-table-column label="地址" prop="location" align="center" width="80"/>
    <el-table-column label="联系电话" prop="mTel" align="center" width="80"/>
    <el-table-column label="老板姓名" prop="bossTel" align="center" width="80"/>
    <el-table-column label="老板电话" prop="bossName" align="center" width="80"/>
    <el-table-column label="商家类型" prop="type" align="center" width="80"/>
    <el-table-column label="所属区域" prop="region.name" align="center" width="80"/>
    <el-table-column label="操作" align="center" width="140">
      <template slot-scope="scope">
        <el-button  @click="forwardDetail(scope.row)" type="success" icon="el-icon-goods" size="mini" plain circle></el-button>
        <el-button  @click="editUser(scope.row)" type="primary" icon="el-icon-edit" size="mini" plain circle></el-button>
        <el-button  @click="confirmDelete(scope.row)" type="danger" icon="el-icon-delete" size="mini" plain circle></el-button>
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
<el-dialog title="添加商家" :visible.sync="showAddDialog">
  <add-mergant-dialog/>
</el-dialog>
<el-dialog title="编辑商家" :visible.sync="showEditDialog">
  <edit-mergant-dialog :userId.sync="this.userId"/>
</el-dialog>
</div>
</template>

<script>
  import {formatDate} from '@/r/js/data.js';
  import addMergantDialog from '@/components/admin/mergant/addMergantDialog';
  import editMergantDialog from '@/components/admin/mergant/editMergantDialog';
  export default {
    components: {
      addMergantDialog,editMergantDialog,
    },
    methods: {
      forwardDetail(row) {
        console.log(row.uId);
        this.$router.push({path:`/admin/mergant/detail/${row.uId}`});
      },
      editUser(row) {
        this.userId = row.uId;
        this.showEditDialog = true;
   //   this.getUserInf();
    },
    handleCurrentChange(val) {
      this.getPage(val);
    },
    confirmDelete(row) {
      this.$confirm('此操作将删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/hungry/mergant/o_deleteMergant',{id:row.uId}).then((response) => {
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
    getPage(pNum) {
      this.$axios({
        method: 'post',
        url: '/hungry/mergant/o_getAllMergant',
        data:{pageNum:pNum,
          mName:this.formInline.mName,
          type:this.formInline.type},
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
    data() {
      return {
        currentPage: 1,
        pageSize: 1,
        itemTotal: 1,
        tableData: [],
        showAddDialog: false,
        showEditDialog: false,
        userId: Number,
        formInline: {},
        selectType: "",
      }
    },
mounted() {
  this.getPage(1);
  this.$axios.post('/hungry/mergant/o_getMergantType',{}).then((response) => {
    if(response.data.success === true){
      this.selectType = response.data.data;
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