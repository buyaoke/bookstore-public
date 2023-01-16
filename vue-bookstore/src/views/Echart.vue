<template>
  <div >
    <el-row :gutter="10" style="margin-bottom: 5%">
      <el-col :span="6" >
        <el-card>
          <div style="color: #409EFF">用户总数:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">100</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #E6A23C">销售总数:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">￥100000</div></el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #F56C6C">用户总数:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">100</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #909399">收益总数:</div>
          <div style="padding: 10px 0;text-align: center;font-weight: bold">￥10000</div>
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

    }
  },
  mounted() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
      title: {
        text: '季度会员统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度","第二季度","第三季度","第四季度"]
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
        text: '季度会员统计',
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


    this.request.get("/echarts/members").then(res => {

      option.series[0].data = res.data
      option.series[1].data = res.data
      pieoption.series[0].data = [
      {name:"第一季度",value: res.data[0]},
      {name:"第二季度",value: res.data[1]},
      {name:"第三季度",value: res.data[2]},
      {name:"第四季度",value: res.data[3]},

      ]
      myChart.setOption(option)
      pieChart.setOption(pieoption)

    })
  }
}
</script>

<style scoped>

</style>