<template>
  <div>
    <div style="margin-bottom:30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>

        <el-breadcrumb-item>用户管理</el-breadcrumb-item>

      </el-breadcrumb>
    </div>
    <div style="padding:10px 0">
      <el-input style="width:200px" suffix-icon="el-icon-search" placeholder="请输入名称" @selection-change="handleSelectionChange"
                v-model="username"></el-input>
      <el-input style="width:200px" suffix-icon="el-icon-message" placeholder="请输入邮箱" v-model="email"
                class="ml-5"></el-input>
      <el-input style="width:200px" suffix-icon="el-icon-position" placeholder="请输入地址" v-model="address"
                class="ml-5"></el-input>
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

    <el-table :data="tableData" border stripe header-cell-class-name="headbg" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55" ></el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="email" label="邮箱">
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
                         title="确定删除吗？" @confirm="del(scope.row.id)">
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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
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
      username: "",
      email: "",
      address: "",
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
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res => {
        this.tableData = res.data.records;
        this.total = res.data.total;


      })
    },
    reset() {
      this.username = ""
      this.address = ""
      this.email = ""
      this.load()
    },
    save() {
      this.request.post("/user", this.form).then(res => {
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
    del(id) {
      this.request.delete("/user/" + id).then(res => {
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
      let ids = this.multipleSelection.map(v => v.id)

      this.request.post("/user/del/batch", ids
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