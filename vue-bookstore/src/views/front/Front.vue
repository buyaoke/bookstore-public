<template>
  <div>
   <div style="display: flex;height: 60px;line-height: 60px;border-bottom: 1px solid #cccccc">
     <div style="width: 200px;text-align: center">
       <img src="../../assets/logo.png" width="20px"  style="position: relative;top: 5px;margin-right: 6px">图书商城</div>
     <div style="flex: 1;padding: 0 45px">
       <el-menu

           class="el-menu-demo"
           mode="horizontal"

           background-color="#545c64"
           text-color="#fff"
           active-text-color="#ffd04b" router>
         <el-menu-item index="/front/home">首页</el-menu-item>
         <el-submenu index="2">
           <template slot="title">书籍类型</template>
           <el-menu-item @click="$router.push('/front/bookShow?booktype=\'历史\'')" style="text-align: center">历史</el-menu-item>
           <el-menu-item @click="$router.push('/front/bookShow?booktype=\'名著\'')" style="text-align: center">名著</el-menu-item>
           <el-menu-item index="/front/bookShow?booktype='轻小说'" style="text-align: center">轻小说</el-menu-item>
         </el-submenu>
         <el-menu-item style="margin-left: 190px;line-height:60px" >
           <el-input style="width:600px" suffix-icon="el-icon-search" placeholder="请输入书名"
                     v-model="bookName"></el-input>
           <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
           <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
         </el-menu-item>

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
      <router-view :key="$route.fullPath"></router-view>
    </div>

  </div>
</template>

<script>
export default {
  name: "Front",
  data(){
    return {
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):null,
      bookName:"",
    }
  },
  methods:{
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
    reset(){
      this.bookName = ''
    },
    load(){

    }
  }

}
</script>

<style >
.item{
  display: inline-block;
  width: 100px;
}
</style>