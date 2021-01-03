<template>
<div class="container">
 <div style="display:flex; width: 100%;margin: 10px;">
     <div class="userbox" style="width: 100%;" >
      <div style="text-align: center;">用户的行为信息的统计</div>
      <br/>
      <div id="mainbar" style="width: 100%;height:400px;"></div>
    </div>
  </div>
  <div style="display:flex; width: 100%;margin: 50px 0 ;">
     <div class="userbehave" style="width: 100%;" >
      <div style="text-align: center;">用户的行为信息统计</div>
      <br/>
      <div id="mainpie" style="width: 100%;height:400px;"></div>
    </div>
  </div>
</div>

</template>

<script>
import {statisticUser} from '../api/index'
import echarts from 'echarts'
export default {
   name: "test",
   data (){
      return{
          xarr: [],    //存放横坐标的数据
          sarr:[],     // 存放具体横坐标中的值
          pieData:[]  // 存放后端格式化后的数据用于饼图的数据
      }
   },
  methods: {
    //柱状图
    drawChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("mainbar"));

      // 初始化
      let option = {
            tooltip: {},
            xAxis: {
                data: [],
            },
            yAxis: {},
            series: [{
                type: 'bar',
                data: [],
            }]
        };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
     
      myChart.showLoading();

      // 动态渲染数据
      statisticUser().then(res =>{
            // console.log(res)
            myChart.hideLoading();
           for(let i = 0; i < res.length;i++){
                this.xarr[i] = res[i].act;
                this.sarr[i] = res[i].count;
           }
            // console.log(this.xarr);
            // console.log(this.sarr);
            myChart.setOption({
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                top: '15%',
                right: '3%',
                left: '5%',
                bottom: '12%'
            },
            xAxis: [{
                type: 'category',
                data: this.xarr,
                axisLine: {
                    lineStyle: {
                        color: 'rgba(70, 196, 255, .8)'
                    }
                },
                axisTick: {
                    show: false,
                },
                axisLabel: { 
                    color: 'rgba(111, 132, 189, 1)',
                    textStyle: {
                        fontSize: 15
                    },
                },
            }],
            yAxis: [{
                name: '单位：人数',
                nameTextStyle: {
                    color: 'rgba(111, 132, 189, 1)'
                },
                axisLabel: {
                    formatter: '{value}',
                    color: 'rgba(111, 132, 189, 1)',
                },
                axisTick: {
                    show: false,
                },

                axisLine: {
                    show: false,
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(70, 196, 255, .3)'
                    }
                }
            }],
            series: [{
                type: 'bar',
                data: this.sarr,
                 barGap:'30%',
                barWidth: 30,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0, 192, 240, 1)' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: 'rgba(101, 105, 255, 1)' // 100% 处的颜色
                        }], false),
                        barBorderRadius: [5, 5, 0, 0],
                        label: {
                            show: true,
                            show: true, //开启显示
                            position: 'top', //在上方显示
                            textStyle: { //数值样式
                                color: 'rgba(111, 132, 189, 1)',
                                fontSize: 16
                            }
                        }
                    }
                },
            }]
          }
            );

            // 设置echars的随着窗口的大小而变化
         window.addEventListener('resize', () => {
				if (myChart) {
					myChart.resize();
				}
			});
        })
    },

    // 饼图
    pieChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("mainpie"));
     
      let option = {
            tooltip: {},
            series: [{
                type: 'pie',
                data: [],
            }]
        };
      // 初始化ehcars
      myChart.setOption(option);
  
      // 进行数据的渲染
      statisticUser().then(res =>{
            for(let i = 0; i < res.length;i++){
                this.pieData.push({
                    'name': res[i].act,
                    'value': res[i].count
                })
                console.log(this.pieData[i]['value']);
            }
          // console.log(this.pieData);
           myChart.setOption(option1);
           window.addEventListener('resize', () => {
				if (myChart) {
					myChart.resize();
				}
			});
        });

       // 设置新的值
      let index = 0;
      let bgColor = '#fff';
      var colorList = ['#73DDFF', '#73ACFF', '#FDD56A', '#FDB36A', '#FD866A', '#9E87FF', '#58D5FF']
      function fun() {
        var timer = setInterval(function() {
         myChart.dispatchAction({
            type: 'hideTip',
            seriesIndex: 0,
            dataIndex: index
        });
        // 显示提示框
        myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        // 取消高亮指定的数据图形
        myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: index == 0? 5 : index -1
        });
         myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if (index > 5) {
            index = 0;
        }
      },3000)
     }

      fun()
      setTimeout(function() {fun()}, 5000);

      let option1 = {
      backgroundColor: bgColor,
      title: {
          text: '用户行为占比',
          x: 'center',
          y: 'center',
          textStyle: {
              fontSize: 20
          }
      },
      tooltip: {
          trigger: 'item'
      },
      series: [{
          type: 'pie',
          radius: ['45%', '60%'],
          center: ['50%', '50%'],
          clockwise: true,
          avoidLabelOverlap: true,
          hoverOffset: 15,
          itemStyle: {
              normal: {
                  color: function(params) {
                      return colorList[params.dataIndex]
                  }
              }
          },
          label: {
              show: true,
              position: 'outside',
              formatter: '{a|{b}：{d}%}\n{hr|}',
              rich: {
                  hr: {
                      backgroundColor: 't',
                      borderRadius: 3,
                      width: 3,
                      height: 3,
                      padding: [3, 3, 0, -12]
                  },
                  a: {
                      padding: [-30, 15, -20, 15]
                  }
              }
          },
          labelLine: {
              normal: {
                  length: 20,
                  length2: 30,
                  lineStyle: {
                      width: 1
                  }
              }
          },
          data: this.pieData,
       }]
      };
       
    },

},
  // 视图渲染完毕
  mounted() {
    this.drawChart();
    this.pieChart();
  },

}
</script>

<style scoped>
   .container{
     background-color: '#fff';
     display: 'flex';
    }
</style>