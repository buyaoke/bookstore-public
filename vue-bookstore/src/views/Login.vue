<template>
  <div class="warpper">
    <div style="margin: 200px auto;background-color: #fff;width: 350px; padding: 20px;border-radius: 2px">
      <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>登 录</b>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-form-item prop="username">
        <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username" ></el-input>
          </el-form-item>
          <el-form-item prop="password">
        <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password" ></el-input>
          </el-form-item>
        <div style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
          <el-button type="primary" size="small" autocomplete="off">注册</el-button>
        </div>
        </el-form>
      </div>
    </div>
  </div>


</template>

<script>
export default {
  name: "Login",
  data(){
    return {
      user:{},
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    }

  },
  methods:{
    login(userForm){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          this.request.post("/user/login",this.user).then(res => {
            if (res){
              this.$message.success("登录成功")
              this.$router.push("/")
            } else {
              this.$message.error("账户或密码错误")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });


    },


  }
}
</script>

<style>
.warpper{
  height: 100vh;
  background-image: linear-gradient(to bottom right,#FC4668,#3F5EF8);
  overflow: hidden;
}

</style>