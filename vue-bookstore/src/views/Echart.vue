<template>
  <div >
    <el-row :gutter="10" style="margin-bottom: 5%">
      <el-col :span="8" >
        <el-card>
          <div style="color: #409EFF">客户总数:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">{{customercount}}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div style="color: #E6A23C">单笔最大金额:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">{{max}}</div></el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div style="color: #F56C6C">最大月销售金额:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">{{maxMonth}}</div>
        </el-card>
      </el-col>



    </el-row>
    <el-row>
      <el-col :span="12"> <div id="main" style="width: 500px;height: 400px"></div></el-col>
      <el-col :span="12"> <div id="pie" style="width: 500px;height: 400px"></div></el-col>
    </el-row>




  </div>



</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Echart",
  data(){
    return {
      customercount:0,
      max:0,
      maxMonth:0,
      id:0
    }
  },
  created() {
   this.id = this.$route.query.id
  },
  mounted() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
      title: {
        text: '月收益统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      xAxis: {
        type: 'category',
        data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月",]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar',
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }


      ]
    };



    var pieoption = {
      title: {
        text: '月收益统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {

          type: 'pie',
          radius: '50%',
          data: [

          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);


    this.request.get("/echarts/revenue",{
      params:{
        bussionId:this.id
      }
    }).then(res => {
      this.customercount = res.data.customercount
      this.max = res.data.max
      this.maxMonth = res.data.maxMonth

      option.series[0].data = res.data.data
      option.series[1].data = res.data.data



      pieoption.series[0].data = [
      {name:"一月",value: res.data.data[0]},
      {name:"二月",value: res.data.data[1]},
      {name:"三月",value: res.data.data[2]},
      {name:"四月",value: res.data.data[3]},
      {name:"五月",value: res.data.data[4]},
      {name:"六月",value: res.data.data[5]},
      {name:"七月",value: res.data.data[6]},
      {name:"八月",value: res.data.data[7]},
      {name:"九月",value: res.data.data[8]},
      {name:"十月",value: res.data.data[9]},
      {name:"十一月",value: res.data.data[10]},
      {name:"十二月",value: res.data.data[11]},


      ]
      myChart.setOption(option)
      pieChart.setOption(pieoption)

    })
  }
}
</script>

<style scoped>

</style>