
<template>
<div class="container">
 <div style="display:flex; width: 100%;margin: 10px;">
     <div class="userbox" style="width: 100%;" >
      <div style="text-align: center;"></div>
      <br/>
      <div id="mainbar" style="width: 100%;height:400px;"></div>
    </div>
  </div>
  <div style="display:flex; width: 100%;margin: 50px 0 ;">
     <div class="userbehave" style="width: 100%;" >
      <div style="text-align: center;">用户的留存率统计</div>
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
          Orderxarr: [],    //存放横坐标的数据
          orderBrower:[],     // 用户的浏览量
          orderData:[],      // 用户的下单数据
          pieData:[]   // 存放后端格式化后的数据
      }
   },
  methods: {
    //柱状图，表示用户下单和浏览的量
    drawChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("mainbar"));

      // 初始化
      let option1 = {
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
      myChart.setOption(option1);

      // 测试数据
      var xData = ["2020-01","2020-02","2020-03", "2020-04", "2020-05", "2020-06", "2020-07", "2020-08", "2020-09", "2020-10", "2020-11"];
      var yData1 = [120, 150, 120, 460, 220, 240, 150, 50, 440, 180, 240];
      var yData2 = [130, 170, 100, 380, 170, 280, 220, 120, 380, 109, 140];
      
      // 新的表格格式
      let option = {
          title: {
              top: '0',
              left: 'center',
              text: '用户下单浏览信息统计',
              textStyle: {
                  align: 'center',
                  color: '#043491',
                  fontSize: 22,
              }

          },
          toolbox: {
              feature: {
                  saveAsImage: {},
                  dataView:{}
              }
          },
          backgroundColor: '#FFFFFF',
          tooltip: {
              trigger: 'axis',
              axisPointer: {
                  type: 'cross'
              }
          },
          legend: {
              x: 'center',
              y: '40px',
              textStyle: {
                  color: '#031C4C',
                  fontSize: 13,
              },
              icon: 'circle',
              data: ['浏览量', '下单量']
          },
          grid: {
              right: '5%',
              bottom: '5%',
              left: '2%',
              top: '80px',
              containLabel: true
          },
          xAxis: [{
              type: 'category',
              data: xData,
              name: '时间',
              nameTextStyle: {
                  color: '#031C4C'
              },
              axisLine: {
                  lineStyle: {
                      color: '#3B72BF'
                  }
              },
              axisTick: {
                  show: false,
              },
              axisLabel: {
                  show: true,
                  textStyle: {
                      color: "#013232",
                      fontSize: 12,
                  },
                  //interval:0,
                  //rotate:30
              },
          }],
          yAxis: [{
              type: 'value',
              name: '人数',
              nameTextStyle: {
                  color: '#031C4C'
              },
              position: 'left',
              axisLine: {
                  lineStyle: {
                      color: '#3B72BF'
                  }
              },
              splitLine: {
                  lineStyle: {
                      color: "#C6DAF6",
                  }

              },
              axisLabel: {
                  color: '#013232',
                  fontSize: 12,
              }
          }, ],
          series: [{
                  name: '浏览量',
                  type: 'line',
                  yAxisIndex: 0,
                  symbolSize: 12,
                  itemStyle: {
                      normal: {
                          color: '#FC30EE',
                      }
                  },
                  data: yData1
              },
              {
                  name: '下单量',
                  type: 'line',
                  yAxisIndex: 0,
                  symbolSize: 12,
                  itemStyle: {
                      normal: {
                          color: '#0EF100',
                      }
                  },
                  data: yData2
              }

          ]
      };

      // 动态数据的渲染，后端接口待实现,
      myChart.setOption(option);
      // 设置echars的随着窗口的大小而变化
      window.addEventListener('resize', () => {
            if (myChart) {
              myChart.resize();
            }
          });
    },

    // 横向图,计算转化率
    concersionChart() {

      // 初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("mainpie"));
      let option1 = {
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

      myChart.setOption(option1);
       // 模拟数据
        let dataArr = [
         { name: "1月", value: 88 },
            { name: "2月", value: 87 },
            { name: "3月", value:86.5 },
            { name: "4月", value: 91 },
            { name: "5月", value: 84 },
            { name: "6月", value: 57 },
            { name: "7月", value: 35 },
            { name: "8月", value: 25 },
            { name: "9月", value: 20 },
            { name: "10月", value: 20 },
            { name: "11月", value: 15 },
            { name: "12月", value: 13 },
            // { name: "13月", value: 10 },
        ]
    let dataAxis = dataArr.map(obj => obj.name);
    let TData = dataArr.map(item => Number(item.value));
    let max = Math.max(...TData);
    let maxArr = new Array(TData.length).fill(max);

    let option = {
        title: {
            text: '',
            textStyle: {
                align: 'center',
                color: '#043491',
                fontSize: 20,
            },
            top: '0%',
            left: 'center'
        },
        grid: {
              left: '5%',
              right: '0%',
              top: '10%',
              bottom: 25,
              containLabel: true
            },
        xAxis: {
              show: false,
              xAxisIndex: 1,
              splitNumber: 3,
              type: "value",
              nameGap: 30,
              axisTick: {
                show: true
              },
              axisLabel: {
                show: true,
                textStyle: {
                  color: "#fff",
                  fontSize: 16
                }
              },
              min: function(value) {
                return value.min > 2 ? value.min - 2 : value.min;
              },
              splitLine: {
                show: false
              },
              axisLine: {
                lineStyle: {
                  color: "rgba(58,78,105,.8)"
                }
              }
         },
         yAxis: {
              type: "category",
              data: dataAxis,
              axisTick: {
                show: false
              },
              inverse: true,
              z: 10,
            axisLabel: {
                show: true,
                color: "#fff",
                fontSize: dataAxis.length > 10 ? 18 : 22,
                interval: 0,
                formatter: function(value) {
                  var idx = dataAxis.indexOf(value);
                  return ( "{sort" +
                    (idx < 5 ? 0 : "Default") +
                    "|" +
                    (idx + 1) +
                    "}"
                  );
                },
                rich: {
                  label: {
                    color: "#fff",
                    fontSize: dataAxis.length > 10 ? 18 : 22,
                    padding: [0, 10, 50, 0],
                    fontWeigth: "normal"
                  },
                  sort0: {
                    color: "#3877de",
                    fontSize: 12,
                    width: 20,
                      height: 20,
                    borderRadius:5,
                    //textShadowColor: "rgba(0,0,0,.4)",
                    //textShadowBlur: 2,
                    //textShadowOffsetX: -1,
                    //textShadowOffsetY: 1,
                    //shadowColor: "rgba(255,102,31,.26)",
                    verticalAlign: "middle",
                    //shadowBlur: 8,
                    //shadowOffsetY: 4,
                    fontStyle: "italic",
                    align: "center",
                    backgroundColor: "#e6f2ff"
                  },
                  sort1: {
                    color: "#fff",
                    fontSize: 16,
                    width: 20,
                    height: 20,
                    textShadowColor: "rgba(0,0,0,.4)",
                    fontStyle: "italic",
                    textShadowBlur: 2,
                    textShadowOffsetX: -1,
                    textShadowOffsetY: 1,
                    shadowColor: "rgba(255,171,41,.26)",
                    verticalAlign: "middle",
                    shadowBlur: 8,
                    shadowOffsetY: 4,
                    align: "center",
                    backgroundColor: "#ff900e"
                  },
                  sort2: {
                    color: "#fff",
                    fontSize: 16,
                    width: 20,
                    height: 20,
                    fontStyle: "italic",
                    textShadowColor: "rgba(0,0,0,.4)",
                    textShadowBlur: 2,
                    textShadowOffsetX: -1,
                    textShadowOffsetY: 1,
                    shadowColor: "rgba(11,175,237,.26)",
                    verticalAlign: "middle",
                    shadowBlur: 8,
                    shadowOffsetY: 4,
                    align: "center",
                    backgroundColor: "#0599de"
                  },
                  sortDefault: {
                    color: "#8e929e",
                    fontSize: 12,
                    width: 20,
                  // padding: [0, 3, 0, 0],
                    height: 20,
                    borderRadius:5,
                    fontStyle: "italic",
                    //textShadowColor: "rgba(0,0,0,.4)",
                    //textShadowBlur: 2,
                    //textShadowOffsetX: -1,
                    //textShadowOffsetY: 1,
                    //shadowColor: "rgba(15,191,129,.26)",
                    verticalAlign: "middle",
                    //shadowBlur: 8,
                    //shadowOffsetY: 4,
                    align: "center",
                    backgroundColor: "#f4f4f6"
                  }
                }
              },
              axisLine: {
                show: false,
                lineStyle: {
                  color: "#e4e4e4"
                }
              }
            },
        color: ["#ff562e", "#ff900e", "#0599de", "#0bbe63"],    
        series: [
              {
                type: "bar",
                barGap: "-100%",
                barWidth: "30%",
                z: 0,
                label: {
                  normal: {
                    show: true,
                    position: ["70%", '-200%'],
                    formatter: function(params) {
                      var idy = params.dataIndex > 3 ? 3 : params.dataIndex;
                      return (
                        "{cc" + "|" + "用户转化率:}{color" + idy + "|" + TData[params.dataIndex] +
                        "}{u" + idy + "|" + "%}"
                      );
                    },
                        rich: {
                            cc: {
                        color: "#b4bbc5",
                        fontSize: 12,
                        verticalAlign: "bottom"
                      },
                      color0: {
                        color: "#ff562e",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      color1: {
                        color: "#ff900e",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      color2: {
                        color: "#0599de",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      color3: {
                        color: "#0bbe63",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      u0: {
                        fontSize: 14,
                        color: "#ff562e",
                        verticalAlign: "bottom"
                      },
                      u1: {
                        color: "#ff900e",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      u2: {
                        color: "#0599de",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      },
                      u3: {
                        color: "#0bbe63",
                        fontSize: 14,
                        verticalAlign: "bottom"
                      }
                    }
                  }
                },
                tooltip: {
                  show: false
                },
                itemStyle: {
                  normal: {
                    barBorderRadius: 5,
                    color: "#e1e7eb"
                  }
                },
                data: maxArr
              },
              {
                type: "bar",
                barGap: "-100%",
                barWidth: "30%",
              label: {
                  normal: {
                    show: true,
                    position: [0, '-200%'],
                      formatter: function (params) {
                          return (
                              "{cn|" + dataAxis[params.dataIndex]+"}"
                      );
                    },
                        rich: {
                            cn: {
                        color: "#273b5e",
                        fontSize: 12,
                        verticalAlign: "bottom"
                      },
                    }
                  }
                },
                z: 8,
                itemStyle: {
                  barBorderRadius: 5,
                  shadowColor: "rgba(0, 0, 0, 0.1)",
                  shadowBlur: 8,
                  shadowOffsetY: 8,
                  color: params => {
                    // build a color map as your need.
                    //定义一个颜色集合
                    var colorList = ["#ff7a0e", "#ffcb48", "#12c9fe", "#13c1a3"];
                    var colorList2 = ["#ff562e", "#ff900e", "#0599de", "#0bbe63"];
                    //对每个bar显示一种颜色
                    var idx = params.dataIndex < 3 ? params.dataIndex : 3;
                    var colorS = colorList[idx];
                    var colorS2 = colorList2[idx];
                    return new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                      { offset: 0, color: colorS },
                      { offset: 1, color: colorS2 }
                    ]);
                  },
                  emphasis: {
                    barBorderRadius: 5,
                    color: new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                      { offset: 1, color: "#00ff90" },
                      { offset: 0, color: "#0084f3" }
                    ])
                  }
                },
                data: TData
              }
            ]
       };
      // 完成数据绑定的初始化
      myChart.setOption(option);
      // 设置echars的随着窗口的大小而变化
      window.addEventListener('resize', () => {
            if (myChart) {
              myChart.resize();
            }
          });
  },

},
  // 视图渲染完毕
  mounted() {
    this.drawChart();
    this.concersionChart();
  },

}
</script>

<style scoped>
   .container{
     background-color: '#fff';
     display: 'flex';
    }
</style>