<template>
  <el-menu :default-openeds="['1', '3']" style="min-height:100%;overflow-x: hidden;"
           background-color="rgb(48,65,86)" text-color="#fff" active-text-color="#ffd04b" :collapse-transition="false"

           :collapse="isCollapse"
           router
  >
    <div style="height:60px;line-height:60px;text-align:center">
      <img src="../assets/logo.png" alt=""
           style="width:20px; position: relative; top: 5px;margin-right: 5px;"></img>
      <b style="color:white" v-show="isShow">图书商城后台管理系统</b>
    </div>
    <el-menu-item index="/home">
      <i class="el-icon-house"></i><span slot="title">主页</span>
    </el-menu-item>
    <el-menu-item :index="'/chart?id='+this.user.id" v-if="user.type==='商家'">
      <i class="el-icon-house"></i><span slot="title">图书收益</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i><span slot="title">系统管理</span>
      </template>


        <el-menu-item index="/user" v-if="user.type ==='管理员' ">
          <i class="el-icon-s-custom"></i><span slot="title">用户管理</span>
          </el-menu-item>
        <el-menu-item :index="'/book?id='+this.user.id" v-if="user.type==='商家'">
          <i class="el-icon-s-custom"></i><span slot="title">书籍管理</span>
          </el-menu-item>
          <el-menu-item :index="'/order?id='+this.user.id" v-if="user.type==='商家'">
          <i class="el-icon-s-custom"></i><span slot="title">订单管理</span>
          </el-menu-item>

    </el-submenu>

  </el-menu>

</template>

<script>
export default {
  name: "Aside",
  props:{
    isShow:Boolean,
    isCollapse:Boolean

  },
  data(){
    return {
      user:JSON.parse(localStorage.getItem("user"))
    }
  }
}
</script>


<style scoped>

</style>