<template>
  <div class="warpper">
    <div style="margin: 200px auto;background-color: #fff;width: 350px; padding: 20px;height:400px;border-radius: 2px">
      <div style="margin: 20px 0;text-align: center;font-size: 24px"><b>注 册</b>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-form-item prop="username">
        <el-input size="medium" placeholder="请输入用户名" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.username" ></el-input>
          </el-form-item>
          <el-form-item prop="password">
        <el-input size="medium" placeholder="请输入密码"style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password" ></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
        <el-input placeholder="请确认密码" size="medium"  style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassword" ></el-input>
          </el-form-item>
        <div style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">注 册</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回登录</el-button>
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
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    }

  },
  methods:{
    login(userForm){
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          if (this.user.password !== this.user.confirmPassword){
            this.$message.error("密码不一致")
            return false
          }
          this.request.post("/user/register",this.user).then(res => {
            if (res.code === "200"){
              this.$message.success("注册成功")
            } else {
              this.$message.error(res.msg)
            }
          })
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