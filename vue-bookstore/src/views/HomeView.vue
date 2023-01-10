<template>
  <div>
    <el-container>
      <el-aside width="sideWidth + 'px'"
        style="background-color: rgb(238, 241, 246); min-height: 100vh; box-shadow: 2px 0 6px rgb(0, 21, 41);">
        <el-menu :default-openeds="['1', '3']" style="min-height:100%;overflow-x: hidden;"
          background-color="rgb(48,65,86)" text-color="#fff" active-text-color="#ffd04b" :collapse-transition="false"
          :collapse="isCollapse">
          <div style="height:60px;line-height:60px;text-align:center">
            <img src="../assets/logo.png" alt=""
              style="width:20px; position: relative; top: 5px;margin-right: 5px;"></img>
            <b style="color:white" v-show="isShow">图书商城后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>
              <span slot="title">导航一</span></template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i><span slot="title">导航二</span></template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i><span slot="title">导航三</span></template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px; border-bottom:1px solid #ccc; line-height: 60px; display: flex;">
          <div style="flex:1; font-size:20px">
            <span :class="collapseBtnClass" style="curse: pointer" @click="collapse"></span>
          </div>
          <el-dropdown style="width:70px;cursor: pointer;">
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>

            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>
          <div style="margin-bottom:30px">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>

              <el-breadcrumb-item>用户管理</el-breadcrumb-item>

            </el-breadcrumb>
          </div>
          <div style="padding:10px 0">
            <el-input style="width:200px" suffix-icon="el-icon-search" placeholder="请输入名称"
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
            <el-button type="danger" @click="delBatch">批量删除<i class="el-icon-remove-outline"></i></el-button>
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

        </el-main>
      </el-container>
    </el-container>

  </div>

</template>

<script>




import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄',

    };
    return {
      pageNum: 1,
      pageSize: 2,
      tableData: [],
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      isShow: true,
      currentPage: 1,
      total: 0,
      username: "",
      email: "",
      address: "",
      dialogFormVisible: false,
      form: {},
      multipleSelection: [],
      ids: {}

    }
  },
  created() {
    this.load();

  },
  methods: {
    load() {
      request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records;
        this.total = res.total;


      })
    },
    reset() {
      this.username = ""
      this.address = ""
      this.email = ""
      this.load()
    },
    save() {
      request.post("/user", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
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
      request.delete("/user/" + id).then(res => {
        if (res) {
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
    handleSelectionChange(val) {
      this.multipleSelection = val

    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)

      request.post("/user/del/batch", ids
      ).then(res => {
        if (res) {
          this.$message.success("批量删除成功");
          this.load();
          this.dialogFormVisible = false
        } else {
          this.$message.error("批量删除失败")
        }
      })
    }
  },


}
</script>
<style>
.headbg {
  background-color: #ccc !important;
}
</style>
