<template>
  <el-form class="editUserDialog" ref="userInf" :model="userInf" :rules="rules" status-icon label-width="80px">
    <el-form-item label="账户名" prop="username">
      <el-input v-model="userInf.username" class="input-disable" disabled></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password" >
      <el-input type="password" v-model="userInf.password" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="userInf.checkPass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="name">
      <el-input v-model="userInf.name"></el-input>
    </el-form-item>
    <el-form-item label="所属区域" prop="rId">
      <el-select v-model="userInf.rId" placeholder="所属区域">
        <el-option
        v-for="item in selectRegion"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
  </el-form-item>
    <el-form-item label="手机" prop="tel">
      <el-input v-model="userInf.tel"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('userInf')">创建</el-button>
      <!-- <el-button type="primary" @click="resetForm('userInf')" plain>重置</el-button> -->
      <el-button >取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    userId:Number,
  },
  data() {
    var validatePass= (rule, value, callback) => {
      if (value !== this.userInf.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userInf:{},
      selectRegion: [],
      rules: {
        username: [
          {required: true, message: '请输入账户名', trigger:'blur'},
          {min: 6, max: 20, message: '长度为6-20字符', trigger:['blur','change']},
          {pattern: /^[a-zA-Z0-9]{6,20}$/, message: '账户名只能为字符或数字', trigger:['blur']},
        ],
        password: [
          {min: 6, message: '长度大于6位字符', trigger:['blur']}
        ],
        checkPass: [
          {validator: validatePass, trigger:['blur','change']},
        ],
        name: [
          {required: true, message: '请输入昵称', trigger:'blur'},
          {min: 2, max: 20, message: '长度为2-20字符', trigger:['blur','change']},
        ],
        location: [
          {required: true, message: '请输入地址', trigger:'blur'},
        ],
        tel: [{pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/, message: '手机格式错误', trigger:'blur'}],

      },
    }
  },
  methods: {
    onSubmit(formName) {
      delete this.userInf.region;
      this.$refs[formName].validate((valid) => {
        console.log(this.userInf);
        if (valid) {
          this.$axios({
            method: 'post',
            url: '/hungry/courier/o_saveCourier',
            data: this.userInf,
          }).then((response) => {
            var data = response.data;
            if(data.success === true){
              this.$message.success('提交成功！');
              this.$emit('update:visible', false);
              window.location.reload();
            }else {
              this.$message.error(response.data.errMsg);
            }
          }).catch((error) => {
            console.log(error);
            this.$message.warning('服务器异常！');
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    clearValidate(formName) {
      this.$refs[formName].clearValidate();
    },
    getUserInf(userId) {
      this.$axios({
        method: 'post',
        url: '/hungry/courier/o_getCourierById',
        data: {id:userId},
      }).then((response) => {
        if(response.data.success === true){
          this.userInf = response.data.data;
          this.userInf.username = this.userInf.account.username;
          delete this.userInf.account;
          this.$refs['userInf'].resetFields();
        }else {
          this.$message.error(response.data.errMsg);
        }
      }).catch((error) => {
        console.log(error);
        this.$message.warning('服务器异常！');
      });
    },
  },
  created() {
    this.getUserInf(this.userId);

  },
  watch: {
    userId:function(val){
      this.getUserInf(val);
     // console.log(this.userInf);
    }
  },
    mounted() {
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
    }
}
</script>

<style>
.el-input.is-disabled .el-input__inner{
  color: #606266;
  background-color: #fff;
  border: 0;
}

</style>