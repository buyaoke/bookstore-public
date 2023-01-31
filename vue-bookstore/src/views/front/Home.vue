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
    <div style="background-color: #409EFF;width: 100%;height: 50px;margin-bottom: 10px;line-height: 50px">轻 小 说 推 荐</div>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in qingbooks" :key="item.bookId" style="margin-top: 5px;height: 530px">
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
  <div style="margin: 10px 0">
    <div style="background-color: #409EFF;width: 100%;height: 50px;margin-bottom: 10px;line-height: 50px">历 史 类 图 书 推 荐</div>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in historybooks" :key="item.bookId" style="margin-top: 5px;height: 530px">
        <el-card :body-style="{ padding: '0px' }" style="height: 100%">
          <img :src="item.bookImg" class="image" style="width: 100%;">
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
  <div style="margin: 10px 0">
    <div style="background-color: #409EFF;width: 100%;height: 50px;margin-bottom: 10px;line-height: 50px">名 著 推 荐</div>
    <el-row :gutter="10">
      <el-col :span="6" v-for="item in classics" :key="item.bookId" style="margin-top: 5px;height: 530px">
        <el-card :body-style="{ padding: '0px' }" style="height: 100%">
          <img :src="item.bookImg" class="image" style="width: 100%">
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
export default {
  name: "Home",
  data() {
    return {
      images:[
          require('../../assets/index1.jpg'),
          require("../../assets/index2.jpg")
      ],
      books:[],
      qingbooks:[],
      historybooks:[],
      classics:[],
    }
  },
  created(){
    this.request.get("/book").then(res => {
      this.books = res.data
      this.filter()
    })
  },
  methods:{
    buy(){

    },
    filter(){
      let books = this.books
      this.qingbooks = books.filter(book => book.bookType === "轻小说")
        if(this.qingbooks.length > 4){
          this.books = this.qingbooks.sub(0,4)
        }
      this.historybooks = books.filter(book => book.bookType === "历史")
        if(this.historybooks.length > 4){
          this.books = this.historybooks.sub(0,4)
        }
      this.classics = books.filter(book => book.bookType === "名著")
        if(this.classics.length > 4){
          this.books = this.classics.sub(0,4)
        }
    }
  }
}
</script>

<style scoped>

</style>