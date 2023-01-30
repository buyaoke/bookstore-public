<template>
<div>
  <div style="margin: 10px 0">

    <el-carousel height="450px">
      <el-carousel-item v-for="item in images" :key="item.src" style="margin-bottom: 10px">
        <img :src="item" alt="" style="height: 100%;">
      </el-carousel-item>
    </el-carousel>
  </div>
  <div style="margin: 10px 0">
    <div style="background-color: #409EFF;width: 100%;height: 50px;margin-bottom: 10px;line-height: 50px">轻小说</div>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in books" :key="item.bookId" style="margin-bottom: 10px">
        <div style="border: 1px solid #ccc;padding-bottom: 10px" >
          <img :src="item.bookImg" alt="" style="width: 100%;height:100%">
          <div style="padding: 10px 0">
            <span style="color: #666">{{item.bookName}}</span>
            <span style="color: #cccccc">{{item.bussionName}}aaaaa</span>
          </div>
          <div><el-button type="primary" @click="buy">购买</el-button>
            <el-button type="primary" @click="$router.push('/front/bookdetail?bookId=' + item.bookId+'&bussinId='+item.bussionId)">详情</el-button></div>
        </div>


      </el-col>

    </el-row>
  </div>

</div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      images:[
          require('../../assets/index1.jpg'),
          require("../../assets/index2.jpg")
      ],
      books:[]
    }
  },
  created(){
    this.request.get("/book").then(res => {
      console.log(res.data)
      this.books = res.data

    })
  },
  methods:{
    buy(){
      $
    }
  }
}
</script>

<style scoped>

</style>