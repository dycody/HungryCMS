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
    <el-form-item label="商家名" prop="mName">
      <el-input v-model="form.mName"></el-input>
    </el-form-item>
    <el-form-item label="老板姓名" prop="bossName">
      <el-input v-model="form.bossName"></el-input>
    </el-form-item>
    <el-form-item label="老板电话" prop="bossTel">
      <el-input v-model="form.bossTel"></el-input>
    </el-form-item>   
    <el-form-item label="商家类型" prop="bossName">
      <el-select v-model="form.type" placeholder="商家类型">
        <el-option
        v-for="item in selectType"
        :key="item"
        :label="item"
        :value="item">
      </el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="所属区域" prop="bossName">
    <el-select v-model="form.rId" placeholder="所属区域">
      <el-option
      v-for="item in selectRegion"
      :key="item.id"
      :label="item.name"
      :value="item.id">
    </el-option>
  </el-select>
</el-form-item>
<template>
<el-form-item label="营业时间">
  <el-time-select
    placeholder="开门时间"
    v-model="form.opentime"
    :picker-options="{
      start: '06:30',
      step: '00:15',
      end: '18:30'
    }">
  </el-time-select>
  <el-time-select
    placeholder="打烊时间"
    v-model="form.closetime"
    :picker-options="{
      start: '08:30',
      step: '00:15',
      end: '23:30',
      minTime: form.opentime
    }">
  </el-time-select>
</el-form-item>
</template>
<el-form-item label="地址" prop="location">
  <el-input v-model="form.location"></el-input>
</el-form-item>
<el-form-item label="商家电话" prop="mTel">
  <el-input v-model="form.mTel"></el-input>
</el-form-item>
<el-form-item align="center">
  <el-button type="primary" @click="onSubmit('form')"> 创建</el-button>
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
        selectType: "",
        selectRegion: [],
        form: {},
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
          minPrice: [
          {required: true, message: '请输入优惠价', trigger:'blur'},
          {pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '请输入正确的价格', trigger:['blur']},
          ],
          addPrice: [
          {required: true, message: '请输入优惠价', trigger:'blur'},
          {pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '请输入正确的价格', trigger:['blur']},
          ],
          checkPass: [
          {required: true, message: '请再次输入密码', trigger:'blur'},
          {validator: validatePass, trigger:['blur','change']},
          ],
          mName: [
          {required: true, message: '请输入商家名', trigger:'blur'},
          {min: 2, max: 20, message: '长度为2-20字符', trigger:['blur','change']},
          ],
          location: [
          {required: true, message: '请输入地址', trigger:'blur'},
          ],
          rName: [
          {required: true, message: '请选择区域名', trigger:'blur'},
          ],
          opentime: [
          {required: true, message: '请选择开门时间', trigger:'blur'},
          ],
          closetime: [
          {required: true, message: '请选择打烊时间', trigger:'blur'},
          ],
          bossTel: [
          {required: true, message: '请输入老板电话', trigger:'blur'},
          {pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/, message:'请输入正确的手机', trigger:'blur'}
          ],
          bossName: [
          {required: true, message: '请输入老板姓名', trigger:'blur'},
          ],
          type: [
          {required: true, message: '请选择商家类型', trigger:'blur'},
          ],
          mTel: [{required: true, message: '请输入商家电话', trigger:'blur'},
          {pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/, trigger:'blur', message: '手机格式错误'}],
        },
      }
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios({
              method: 'post',
              url: '/hungry/mergant/o_saveMergant',
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