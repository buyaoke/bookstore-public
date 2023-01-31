<template>
  <div>
    <div style="margin: 10px 0">
      <div style="background-color: #409EFF;width: 100%;height: 50px;margin-bottom: 10px;line-height: 50px">{{booktype}}</div>
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in books" :key="item.bookId" style="margin-top: 5px;height: 530px">
          <el-card :body-style="{ padding: '0px' }" style="height: 100%">
            <img :src="item.bookImg" class="image" style="width:100%">
            <div style="padding: 14px;">
            <span style="word-break:break-all;
       text-overflow: ellipsis;
       overflow: hidden;
       display:-webkit-box;
       -webkit-line-clamp:1;
       -webkit-box-orient:vertical;">{{item.bookName}}</span>
              <div class="bottom clearfix">
                <time class="time">{{  }}</time>
                <div>
                  <el-button type="text" @click="buy">购买</el-button>
                  <el-button type="text" @click="$router.push('/front/bookdetail?bookId=' + item.bookId+'&bussinId='+item.bussionId)">详情</el-button>
                </div>

              </div>
            </div>
          </el-card>
        </el-col>

      </el-row>
    </div>
  </div>





</template>

<script>
import request from "@/utils/request";

export default {
  name: "BookShow",

  data(){
    return {
      booktype:'',
      books:[],
      bookName:""
    }
  },

  created() {
    this.booktype = this.$route.query.booktype
    this.bookName = this.$route.query.bookName
    if(this.booktype !==''){
      this.onload()
    }
   if (this.bookName !== ''){
     this.search()
   }

  },
  methods:{
    onload() {
      this.request.get("/book/type",{
        params:{
          type:this.booktype
        }
      }).then(res =>{
        this.books = res.data
      })
    },
    search(){
      this.request.get("/book/searchBook",{
        params:{
          bookName:this.bookName
        }
      }).then(res =>{
        this.books = res.data
      })
    }


  },




}

</script>

<style scoped>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>