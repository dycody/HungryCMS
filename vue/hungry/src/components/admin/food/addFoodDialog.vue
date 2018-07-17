<template>
  <el-form ref="form" :model="form" :rules="rules" status-icon label-width="80px">
    <el-form-item label="商品名" prop="name">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="原价" prop="yuanPrice" >
      <el-input v-model="form.yuanPrice"></el-input>
    </el-form-item>
    <el-form-item label="现价" prop="cutPrice">
      <el-input v-model="form.cutPrice"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="inf">
      <el-input v-model="form.inf"></el-input>
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
    userId:String,
  },
  data() {
    return {
      form: {uId:""},
      rules: {
        name: [
          {required: true, message: '请输入商品名', trigger:'blur'},
        ],
        yuanPrice: [
          {required: true, message: '请输入原价', trigger:'blur'},
          {pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '请输入正确的价格', trigger:['blur']},
        ],
        cutPrice: [
          {required: true, message: '请输入优惠价', trigger:'blur'},
          {pattern: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/, message: '请输入正确的价格', trigger:['blur']},
        ],
      },
    }
  },
  methods: {
    onSubmit(formName) {
      this.form.uId=this.userId;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            url: '/hungry/food/o_saveFood',
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
}
</script>