<template>
  <div>
   <div style="display: flex;height: 60px;line-height: 60px;border-bottom: 1px solid #cccccc">
     <div style="width: 200px;text-align: center">
       <img src="../../assets/logo.png" width="50px"  style="position: relative;top: 5px;margin-right: 6px">换衣来到XX系统</div>
     <div style="flex: 1">
       <el-menu

           class="el-menu-demo"
           mode="horizontal"

           background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b">
         <el-menu-item index="1">处理中心</el-menu-item>
         <el-submenu index="2">
           <template slot="title">我的工作台</template>
           <el-menu-item index="2-1">选项1</el-menu-item>
           <el-menu-item index="2-2">选项2</el-menu-item>
           <el-menu-item index="2-3">选项3</el-menu-item>
           <el-submenu index="2-4">
             <template slot="title">选项4</template>
             <el-menu-item index="2-4-1">选项1</el-menu-item>
             <el-menu-item index="2-4-2">选项2</el-menu-item>
             <el-menu-item index="2-4-3">选项3</el-menu-item>
           </el-submenu>
         </el-submenu>
         <el-menu-item index="3" disabled>消息中心</el-menu-item>
         <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
       </el-menu>
     </div>
     <div style="width: 200px">
       <div v-if="!user.username">
         <el-button @click="$router.push('/login')">登录</el-button>
         <el-button @click="$router.push('/register')">注册</el-button>
       </div>
       <div v-else>
         <el-dropdown style="width:100px;cursor: pointer;">
           <div style="display: inline-block">
             <img :src="user.avatarUrl" alt="" style="width: 30px;border-radius: 50%; position: relative;top:10px;right: 5px">
             <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left:5px"></i>
           </div>

           <el-dropdown-menu slot="dropdown" style="width: 100px;text-align:center">
             <el-dropdown-item style="font-size: 14px; padding: 5px 0">
               <router-link to="/person" style="text-decoration: none">个人信息</router-link>
             </el-dropdown-item>
             <el-dropdown-item style="font-size: 14px; padding: 5px 0">
               <span  style="text-decoration: none" @click="logout">退出</span>
             </el-dropdown-item>

           </el-dropdown-menu>
         </el-dropdown>
       </div>


     </div>
   </div>
    <div style="width: 1200px;margin: 0 auto">
      <router-view></router-view>
    </div>

  </div>
</template>

<script>
export default {
  name: "Front",
  data(){
    return {
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):null,
    }
  },
  methods:{
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
  }

}
</script>

<style >
.item{
  display: inline-block;
  width: 100px;
}
</style>