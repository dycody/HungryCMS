<template>
  <el-form class="editFoodDialog" ref="foodInf" :model="foodInf" :rules="rules" status-icon label-width="80px">
    <el-form-item label="菜品名" prop="name">
      <el-input v-model="foodInf.name" class="input-disable" disabled></el-input>
    </el-form-item>
    <el-form-item label="原价" prop="yuanPrice">
      <el-input v-model="foodInf.yuanPrice"></el-input>
    </el-form-item>
    <el-form-item label="现价" prop="cutPrice">
      <el-input v-model="foodInf.cutPrice"></el-input>
    </el-form-item>
    <el-form-item label="详情" prop="inf">
      <el-input v-model="foodInf.inf"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('foodInf')">创建</el-button>
      <!-- <el-button type="primary" @click="resetForm('foodInf')" plain>重置</el-button> -->
      <el-button >取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    foodId:Number,
  },
  data() {
    return {
      foodInf:{},
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            url: '/hungry/food/o_saveFood',
            data: this.foodInf,
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
    getfoodInf(foodId) {
      this.$axios({
        method: 'post',
        url: '/hungry/food/o_getFoodById',
        data: {id:foodId},
      }).then((response) => {
        if(response.data.success === true){
          this.foodInf = response.data.data;
          delete this.foodInf.account;
          this.$refs['foodInf'].resetFields();
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
    this.getfoodInf(this.foodId);

  },
  watch: {
    foodId:function(val){
      this.getfoodInf(val);
     // console.log(this.foodInf);
    }
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