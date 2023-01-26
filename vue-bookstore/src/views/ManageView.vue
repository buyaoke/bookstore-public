<template>
  <div>
    <el-container>
      <el-aside width="sideWidth + 'px'"
        style="background-color: rgb(238, 241, 246); min-height: 100vh; box-shadow: 2px 0 6px rgb(0, 21, 41);">
        <Aside :isCollapse="isCollapse" :isShow="isShow"></Aside>
      </el-aside>

      <el-container>
        <el-header style="border-bottom:1px solid #ccc;">
            <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"></Header>

        </el-header>

        <el-main>
          <router-view @refershUser="getUser"/>

        </el-main>
      </el-container>
    </el-container>

  </div>

</template>

<script>


import Aside from "@/components/Aside";
import Header from "@/components/Header";



export default {
  name: 'ManageView',

  components: {Header, Aside},
  created() {
    //从后台获取数据
    this.getUser()
  },

  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      isShow: true,
      ids: {},
      user:{},
    }
  },

  methods: {

    collapse() {
      //点击隐藏
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.isShow = false
      } else {
        //点击展开
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
        this.isShow = true
      }
    },
    getUser(){
      let username = JSON.parse(localStorage.getItem("user")).username
      return this.request.get("/user/username/"+username).then(res => {
        this.user = res.data
      })
    }

  },


}
</script>

