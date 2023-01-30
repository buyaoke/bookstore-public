<template>
  <div>

  <div id="product" style="display: flex;margin: 10px 30px;">

        <el-image :src="book.bookImg" style="height: 100vh"/>


    <div id="text" style="height: 100%;margin-left: 20px">

      <el-card class="box-card" style="height: 100vh" >

          <el-form>
          <el-form-item label="书名：" >
           {{book.bookName}}
          </el-form-item>


          <el-form-item label="作者：">
         {{book.bookAuthor}}
          </el-form-item>


          <el-form-item label="简介：" style="word-break: break-all;text-overflow: ellipsis;overflow: hidden;display: -webkit-box;-webkit-line-clamp: 6;-webkit-box-orient: vertical;">

            {{book.bookDescrip}}
          </el-form-item>


          <el-form-item label="价格：">
           ￥{{book.bookPrice}}
          </el-form-item>


          <el-form-item label="购买数量：">

          <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="购买数量"></el-input-number>

          </el-form-item>


        <el-form-item style="margin-top: 60px">
          <el-button type="primary" @click="buy">立即购买</el-button>
          <el-button>取消</el-button>
        </el-form-item>

        </el-form>






      </el-card>

    </div>
  </div>









    <div style="margin: 30px 0">
      <div style="margin: 10px 0">
        <div style="border-bottom: 1px solid orangered;padding: 10px;font-size: 20px"></div>
        <div style="padding: 10px 0">
          <el-input type="textarea" v-model="commentForm.content"></el-input>
        </div>
        <div class="pd-10" style="text-align: right">
          <el-button type="primary" size="small" @click="save">评论</el-button>
        </div>
      </div>
    </div>
    <div style="font-size: 14px">
      <div v-for="item in comments" :key="item.id" style="border-bottom: 1px solid #ccc;padding: 10px 0;display: flex">
        <div style="width: 100px;text-align: center;padding: 5px">
          <el-image :src="item.avatarUrl" style="width: 50px;height: 50px;border-radius: 50%"></el-image>
        </div>
        <div style="flex: 1;font-size: 14px;padding: 5px 0;line-height: 25px">
          <b>{{ item.nickname }}：</b>
          <span>{{ item.content }}：</span>
          <div style="display: flex;line-height: 20px;margin-top: 5px">
            <div style="width: 200px">
              <i class="el-icon-time"></i>
              <span>{{ item.time }}</span>
            </div>
            <div style="text-align: right;flex: 1">
              <el-button style="margin-left: 5px" type="text" @click="handle(item.id)">回复</el-button>
              <el-button type="text" style="color: #ff5959" @click="del(item.id)">删除</el-button>
            </div>

          </div>
          <div v-if="item.children.length" v-for="sub in item.children" :key="sub.id" style="padding-left: 50px;background-color: #cccccc">

            <div style="flex: 1;font-size: 14px;padding: 5px 0;line-height: 25px">
              <div>
                <b style="color:#3a8ee6">@{{ sub.pnickname }}</b>

              </div>
              <div>
                <b>{{ sub.nickname }}：</b>
                <span>{{ sub.content }}：</span>
              </div>

              <div style="display: flex;line-height: 20px;margin-top: 5px">
                <div style="width: 200px">
                  <i class="el-icon-time"></i>
                  <span>{{ sub.time }}</span>
                </div>
                <div style="text-align: right;flex: 1">
                  <el-button style="margin-left: 5px" type="text" @click="handle(sub.id)">回复</el-button>
                  <el-button type="text" style="color: #ff5959" @click="del(sub.id)">删除</el-button>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>

      <el-dialog title="回复" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="80px" size="small">

          <el-form-item label="回复内容" style="text-align: center">
            <el-input v-model="commentForm.contentReply" type="textarea"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>

</template>

<script>
export default {
  name: "BookDetail",
  data() {
    return {
      comments: [],
      commentForm: {},
      bookId: this.$route.query.bookId,
      bussionId:this.$route.query.bussinId,
      dialogFormVisible: false,
      book:{},
      user:{},
      num: 1

    }
    }
  ,
  created() {
    this.user = JSON.parse(localStorage.getItem("user"))
    console.log("aaaaa",this.user.id)
    this.load()
    this.loadComment()
  },
  methods: {
    load() {
      this.request.get("/book/" + this.bookId).then(res => {

        this.book = res.data
      })


    },
    handleChange(value) {
      console.log(value);
    },
    loadComment() {

      this.request.get("/comment/tree/" + this.bookId).then(res => {
        console.log(res.data)
        this.comments = res.data
      })
    },
    save() {
      this.commentForm.bookId = this.bookId
      if (this.commentForm.contentReply) {
        this.commentForm.content = this.commentForm.contentReply
      }
      this.request.post("/comment", this.commentForm).then(res => {
        if (res.code === "200") {
          this.commentForm = {} //初始化评论
          this.$message.success("评论成功")
          this.dialogFormVisible = false
          this.loadComment()
        } else {
          this.$message.error("评论失败")
        }
      })

    },
    del(bookId) {

      this.request.delete("/comment/" + this.bookId).then(res => {
        if (res.code === "200") {
          this.$message.success("删除成功");
          this.loadComment();
          this.dialogFormVisible = false
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handle(pid) {
      this.commentForm = {pid: pid}
      this.dialogFormVisible = true
    },


    buy(){
      let order = {}
     order.orderPrice=this.book.bookPrice * this.num,
      order.bussionId=this.bussionId,
      order.customerId=this.user.id,
     order.bookId=this.bookId,
      order.bookCount=this.num
      this.request.post("/order",order
      ).then(res => {
          if(res.code === "200"){
            this.$message.success("已生成图书订单，请及时支付")
          }


      })
    }





  }
}
</script>



<style scoped>



.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
  width: 400px;
}

.box-card {
  width: 480px;
}


</style>