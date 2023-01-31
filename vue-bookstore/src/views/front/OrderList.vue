<template>
  <div>
    <div style="font-size: 14px;margin-top: 10px">
      <template>
        <el-table :data="tableData" style="width: 100%">
          <el-table-column
              prop="orderId"
              label="订单号"
              width="100">
          </el-table-column>
          <el-table-column
              prop="bussionName"
              label="商家名称"
              width="100">
          </el-table-column>
          <el-table-column
              prop="bookName"
              label="书名"
              width="80">
          </el-table-column>
          <el-table-column
              prop="createTime"
              label="订单时间">
          </el-table-column>
          <el-table-column
              prop="orderPrice"
              label="订单价格">
          </el-table-column>
          <el-table-column
              prop="isPayed"
              label="是否支付">
          </el-table-column>
          <el-table-column

              label="操作"
              width="170">
            <template slot-scope="scope">
              <el-button @click="handlePay(scope.row)" type="text" size="small" v-if="scope.row.isPayed==='未支付'">支付宝支付</el-button>
              <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
                             title="确定删除吗？" @confirm="handleRemove(scope.row)">
                <el-button type="text" slot="reference" style="margin-left: 20px" v-if="scope.row.isPayed==='未支付'">取消订单</el-button>
              </el-popconfirm>

            </template>
          </el-table-column>
        </el-table>
      </template>

    </div>
    <div class="block">

      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 15, 20, 30]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "OrderList",
  data(){
    return {
      orders:[],
      commentForm:{},
      tableData: [],
      pageNum:1,
      pageSize:5,
      total:0,
      customerId:JSON.parse(localStorage.getItem("user")).id
    }
  },
  created() {
    this.onload()


  },
  methods:{
    onload(){
      this.request.get("/order/page",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          customerId:this.customerId,

        }
      }).then(res => {
        if (res.code === "200"){
          this.tableData = res.data.data
          this.total = res.data.total;
        }
      })
    },
    save(){},
    handlePay(order){

      window.open("http://localhost:9090/alipay/pay?subject="+order.bookName+"&traceNo="+order.orderId+"&totalAmount="+order.orderPrice)
      this.$message.success("请求支付宝接口成功")





    },
    handleRemove(order){
      this.request.delete("/order/"+order.orderId).then(res =>{
        if (res.code === "200"){
          this.$message.success("删除成功")
          this.onload()
        }
      })

    },
    handleSizeChange(val){
      this.pageSize = val
      this.onload()
},
    handleCurrentChange(val){
      this.pageNum = val
      this.onload()
    },

  }
}
</script>

<style scoped>

</style>