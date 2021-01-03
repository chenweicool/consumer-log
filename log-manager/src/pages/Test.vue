<template>
  <div id="test">
    <div id="main" style="width: 100%;height:400px;"></div>
  </div>
</template>

<script>
import {statisticUser} from '../api/index'
export default {
   name: "test",
   data (){
      return{
          xarr: [],    //存放横坐标的数据
          sarr:[]     // 存放具体横坐标中的值
      }
   },
  methods: {
    drawChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("main"));
      //指定图表的配置项和数据
      let option = {
        title: {
          text: "ECharts 入门示例"
        },
        tooltip: {},
        legend: {
          data: ["用户"]
        },
        xAxis: {
          data: [],
           axisLabel:{
                interval: 0
            }
        },
        yAxis: {},
        series: [
          {
            name: "用户",
            type: "bar",
            barWidth : 50,//柱图宽度
            data: [],
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      myChart.showLoading();
      // 动态渲染数据
      statisticUser().then(res =>{
           console.log(res)
            myChart.hideLoading();
           for(let i = 0; i < res.length;i++){
                this.xarr[i] = res[i].act;
                this.sarr[i] = res[i].count;
           }
            console.log(this.xarr);
            console.log(this.sarr);
            myChart.setOption({
              legend: {
                data: ["用户"]
              },
              title: {
                text: "用户的点击量的信息"
              },
              tooltip: {},
              xAxis: {
                data: this.xarr,
                 axisLabel:{
    	       	     interval: 0
                }
              },
              series: [{
                // 根据名字对应到相应的系列
                name: '用户',
                type: "bar",
                barWidth : 50,//柱图宽度
                data: this.sarr,
              }]
            });
        })
    },

  },
  // 视图渲染完毕
  mounted() {
    this.drawChart();
  },

}
</script>