<template>
  <div>
    <div style="margin-bottom:30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>

        <el-breadcrumb-item>图书管理</el-breadcrumb-item>

      </el-breadcrumb>
    </div>
    <div style="padding:10px 0">
      <el-input style="width:200px" suffix-icon="el-icon-search" placeholder="请输入书名" v-model="bookName"></el-input>
      <el-input style="width:200px" suffix-icon="el-icon-message" placeholder="请输入作者" v-model="author" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">重置</el-button>

    </div>
    <div style="margin:10px 0">
      <el-button type="primary" @click="handleAddd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
                     title="确定删除吗？" @confirm="delBatch">
        <el-button type="danger" slot="reference" style="margin: 0 5px">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headbg" @selection-change="handleSelectionChange"
              :row-style="{height: '50px'}">


      <el-table-column prop="bookName" label="书名" width="80">
      </el-table-column>
      <el-table-column prop="bussionName" label="售卖商家" width="80">
      </el-table-column>
      <el-table-column prop="bookPrice" label="价格" width="140">
      </el-table-column>
      <el-table-column prop="bookDescrip" label="书籍描述" width="450" style="word-break: break-all;text-overflow: ellipsis;overflow: hidden;display: -webkit-box;-webkit-line-clamp: 5;-webkit-box-orient: vertical;">

      </el-table-column>
      <el-table-column  label="书籍图片" >
        <template slot-scope="scope" >
          <img :src="scope.row.bookImg" style="height: 50px;margin: 0 auto"/>
        </template>
      </el-table-column>
      <el-table-column prop="bookAuthor" label="作者">
      </el-table-column>
      <el-table-column prop="bookType" label="类型">
      </el-table-column>


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
                         title="确定删除吗？" @confirm="del(scope.row.bookId)">
            <el-button type="danger" slot="reference" class="ml-5">删除<i class="el-icon-delete"></i></el-button>
          </el-popconfirm>

        </template>

      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
                     :page-sizes="[2, 5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>

    <el-dialog title="图书信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">

        <el-form-item label="图片" style="text-align: center">
            <el-upload
                style="text-align: center;"
                class="avatar-uploader"
                action="http://localhost:9090/file/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess">
              <img v-if="form.bookImg" :src="form.bookImg" class="avatar" style="width:100%">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
        </el-form-item>

        <el-form-item label="书名">
          <el-input v-model="form.bookName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.bookPrice" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="作者">
          <el-input v-model="form.bookAuthor" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-input v-model="form.bookType" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="书籍描述">
          <el-input type="textarea" v-model="form.bookDescrip" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      total: 0,
      tableData: [],
      pageNum: 1,
      pageSize: 2,
      bookName:"",
      author:"",
      id:0,

      dialogFormVisible: false,
      form: {},
      multipleSelection: [],
      currentPage: 1,
      headbg:"headbg"
    }
  },
  created() {
    this.id = this.$route.query.id
    this.load()
  },
  methods:{
    load() {
      this.request.get("/book/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          bookName:this.bookName,
          author:this.author,
          id:this.id

        }
      }).then(res => {

        this.tableData = res.data.records;
        this.total = res.data.total;



      })
    },
    reset() {
      this.bookName = ""
      this.author = ""

      this.load()
    },
    save() {

      this.form.bussionId = JSON.parse(localStorage.getItem("user")).id

      this.request.post("/book", this.form).then(res => {
        if (res.code === "200") {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })

    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true;
      this.load();
    },
    del(bookId) {
      this.request.delete("/book/" + bookId).then(res => {
        if (res.code ==="200") {
          this.$message.success("删除成功");
          this.load();
          this.dialogFormVisible = false
        } else {
          this.$message.error("删除失败")
        }
      })
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load();
    },
    handleAddd() {
      this.dialogFormVisible = true;
      this.form = {};
    },
    handleSelectionChange(val) {
      this.multipleSelection = val

    },
    delBatch() {
      let bookIds = this.multipleSelection.map(v => v.bookId)

      this.request.post("/book/del/batch", bookIds
      ).then(res => {
        if (res.code==="200") {
          this.$message.success("批量删除成功");
          this.load();
          this.dialogFormVisible = false
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleAvatarSuccess(res,file){

      this.form.bookImg = res
      this.$forceUpdate()

    }
  }
}
</script>

<style>
.headbg{
  background-color: #ccc !important;
}
</style>