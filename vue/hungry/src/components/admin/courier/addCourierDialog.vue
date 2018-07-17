<template>
  <el-form ref="form" :model="form" :rules="rules" status-icon label-width="80px">
    <el-form-item label="账户名" prop="username">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password" >
      <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
      <el-input type="password" v-model="form.checkPass" auto-complete="off"></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="name">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="所属区域" prop="rId">
      <el-select v-model="form.rId" placeholder="所属区域">
        <el-option
        v-for="item in selectRegion"
        :key="item.id"
        :label="item.name"
        :value="item.id">
      </el-option>
    </el-select>
  </el-form-item>
</el-form-item>
<el-form-item label="手机" prop="tel">
  <el-input v-model="form.tel"></el-input>
</el-form-item>
<el-form-item align="center">
  <el-button type="primary" @click="onSubmit('form')">创建</el-button>
  <el-button type="primary" @click="resetForm('form')" plain>重置</el-button>
</el-form-item>
</el-form>
</template>

<script>
  export default {
    props: {
      visible: Boolean,
    },
    data() {
      var validatePass= (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        form: {},
        selectRegion: [],
        rules: {
          username: [
          {required: true, message: '请输入账户名', trigger:'blur'},
          {min: 6, max: 20, message: '长度为6-20字符', trigger:['blur','change']},
          {pattern: /^[a-zA-Z0-9]{6,20}$/, message: '账户名只能为字符或数字', trigger:['blur']},
          ],
          password: [
          {required: true, message: '请输入密码', trigger:'blur'},
          {min: 6, message: '长度大于6位字符', trigger:['blur']}
          ],
          checkPass: [
          {required: true, message: '请再次输入密码', trigger:'blur'},
          {validator: validatePass, trigger:['blur','change']},
          ],
          name: [
          {required: true, message: '请输入昵称', trigger:'blur'},
          {min: 2, max: 20, message: '长度为2-20字符', trigger:['blur','change']},
          ],
          rName: [
          {required: true, message: '请选择所属区', trigger:'blur'},
          ],
          tel: [{pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/, trigger:'blur', message: '手机格式错误'}],
        },
      }
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              method: 'post',
              url: '/hungry/courier/o_saveCourier',
              data: this.form,
            }).then((response) => {
              var data = response.data;
              if(data.success === true){
                this.$message.success('提交成功！');
                this.$emit('update:visible', false);
                this.$router.go(0);
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
      // console.log('submit!');
    },
    mounted() {
      this.$axios.post('/hungry/region/o_getAllRegion',{}).then((response) => {
        console.log(response)
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