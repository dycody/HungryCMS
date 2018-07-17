<template>
<mt-tab-container v-model="active">
  <mt-tab-container-item id="login">
    <div class="Login">
      <div class="LoginHeader">
        <img src="@/assets/logo.jpg">
        <div class="word">Everything 30'</div>
      </div>
      <div class="login-title">用户登入</div>
      <el-form ref="loginForm" :model="loginForm" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="密码" auto-complete="off">
          </el-input>
        </el-form-item>
        <el-button class="button-login" type="primary" plain @click="submitForm('loginForm')">登入</el-button>
      </el-form>
      <div style="text-align: center;">
        <el-button class="button-register" @click="active='signup'">新用户注册</el-button>
      </div>
    </div>
  </mt-tab-container-item>
  <mt-tab-container-item id="signup">
    <mt-header fixed title="" left>
      <mt-button icon="back" slot="left" @click="active='login'"></mt-button>
    </mt-header>
    <div class="login-title2">用户注册</div>
    <div class="view">
      <add-user/>
    </div>
  </mt-tab-container-item>
</mt-tab-container>
</template>

<script>
import { Toast } from 'mint-ui';
import addUser from '@/components/admin/user/addUserDialog'
export default {
  components: {
    addUser,
  },
  data() {
    return {
      active:"login",
      loginForm:{},
      //rules前端验证
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      }
    };
  },
  methods: {
    resultDeal(userInfo) {
      sessionStorage.setItem('UserInfo', JSON.stringify(userInfo));
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // this.makeCode(4);
          this.$axios({
            method: 'post',
            url: '/hungry/login/o_user',
            data: this.loginForm,
          }).then((response) => {
            var data = response.data;
            if(data.success === true){
              Toast('登入成功');
              this.resultDeal(data.data);
              setTimeout(() =>{
                if(this.$route.query.redirect === undefined) {
                  this.$router.push({path:'/client/mine'});
                }else {
                  let p = {path: this.$route.query.redirect};
                  this.$router.push(p);
                }
              });
            }else {
              // this.$message.error(response.data.errMsg);
              this.$refs['loginForm'].resetFields();
              Toast({message: response.data.errMsg,
                position: 'bottom'});
            }
          }).catch((error) => {
            console.log(error);
            this.$message.warning('服务器异常！');
          });
        } else {
          // console.log('error submit!!');
          return false;
        }
      });
    },
  },

};
</script>

<style scoped>
.view {
  padding: 30px 10px;
  padding-right: 50px;
}
.Login {
  margin: 50px 38px;
}
.LoginHeader {
  text-align: center;
  margin-bottom: 4px;
}
.LoginHeader .word {
  position:relative;
  top: -16px;
  color: #3376ba;
}
.LoginHeader img {
  width: 160px;
  height: 140px;
}
.el-form {
  margin-top: 36px;
}
.button-login {
  margin-top: 30px;
  color: #3376ba;
  width: 100%;
}
.button-register {
  border: none;
  margin-top: 6px;
  width: 40%;
}
.login-title {
  text-align: center;
  font-weight: 700;
  font-size: 40px;
  letter-spacing: 8px;
  color: #3376ba;
}
.login-title2 {
  margin-top: 60px;
  text-align: center;
  font-weight: 700;
  font-size: 40px;
  letter-spacing: 8px;
  color: #3376ba;
}
</style>
