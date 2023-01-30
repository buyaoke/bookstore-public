<template>
  <div>
    <div style="margin-bottom:30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>

        <el-breadcrumb-item>订单管理</el-breadcrumb-item>

      </el-breadcrumb>
    </div>
    <div style="padding:10px 0">




    </div>
    <div style="margin:10px 0">



    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="headbg"
              :row-style="{height: '50px'}">

      <el-table-column type="selection" width="55" ></el-table-column>
            <el-table-column prop="orderId" label="Id" width="80">
            </el-table-column>

      <el-table-column prop="bussionName" label="商家名称" width="80">
      </el-table-column>
      <el-table-column prop="customerName" label="买家名称" width="80">
      </el-table-column>
      <el-table-column prop="bookName" label="书名" width="200">
      </el-table-column>
      <el-table-column prop="orderPrice" label="价格" width="140">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"   max-height="100px" >
      </el-table-column>



      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
                         title="确定删除吗？" @confirm="del(scope.row.orderId)">
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
        <el-form-item label="商家名称">
          <el-input v-model="form.bussionName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="顾客名称">
          <el-input v-model="form.customerName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="书名">
          <el-input v-model="form.bookName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.orderPrice" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="订单时间">
          <el-input  v-model="form.createTime" autocomplete="off" :disabled="true"></el-input>
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

      dialogFormVisible: false,
      form: {},
      multipleSelection: [],
      currentPage: 1,
      headbg:"headbg"
    }
  },
  created() {
    this.load()
  },
  methods:{
    load() {

      this.request.get("/order/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize

        }
      }).then(res => {
        console.log(res.data.data)
        this.tableData = res.data.data;
        this.total = res.data.tolal;


      })
    },
    reset() {
      this.bookName = ""
      this.author = ""

      this.load()
    },
    save() {
      this.request.get("/order/save",{
        params:{
          orderPrice:this.form.orderPrice,
          orderId:this.form.orderId
        }
      }).then(res => {
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
      this.request.delete("/order/" + bookId).then(res => {
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

      this.request.post("/order/del/batch", bookIds
      ).then(res => {
        if (res.code==="200") {
          this.$message.success("批量删除成功");
          this.load();
          this.dialogFormVisible = false
        } else {
          this.$message.error("批量删除失败")
        }
      })
    }
  }
}
</script>

<style>
.headbg{
  background-color: #ccc !important;
}
</style>