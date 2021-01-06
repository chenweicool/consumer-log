<template>
  <div id="box" class="container">
    <div class = "header">
      <p>综和信息分析</p>
    </div>
  <div style="display:flex; width: 100%;margin: 10px;">
     <div class="userbox" style="flex:1;width: 100%;" >
      <div style="text-align: center;">用户的下载来源的统计</div>
      <br/>
      <div id="allUser" style="width: 100%;height:400px;"></div>
    </div>

    <div class="online" style="flex:1;width: 100%;">
      <div style="text-align: center; ">用户在线时长人数统计</div> <br/>
      <div id="online" style="width: 100%;height:400px;"></div>
    </div>
  </div>
    
  <div style="display:flex; width: 100%;margin: 10px ">
      <div class="mobile" style="flex:1;width: 100%;' '">
      <div style="text-align: center;">用户手机来源</div>

      <div id="mobile" style="width: 100%;height:400px;"></div>
    </div>

    <div class="longtime" style="flex:1;width: 100%;">
      <div style="text-align: center;">用户登陆时间</div>
      <div id="longtime" style="width: 100%;height:400px;"></div>
    </div>
   </div>
    
    
  </div>
</template>

<script>
import {statisticMobile,statisticOrigin,statisticHour} from '../api/index'
import echarts from 'echarts'
export default {
   name: "infoPage",
   data (){
      return{
          onlineXarr: [],    //存放用户在线时长的纵坐标
          onlineSarr:[] ,    // 用户在线时长的每个坐标值
          
          loginXarr:[],   // 用户登陆的时间的纵坐标
          loginSarr:[],   // 登陆时间点的具体坐标

          pieData:[],   // 用户的手机类型
          originData:[] , // 用户的数据来源的图形
          originNumber: '',    // 下载商品的用户的总的数量
      }
   },
  methods: {
   
   // 用户下载来源的信息统计饼图，需要优化的点是数字啊汉化
    drawChart() {

      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("allUser"));
      
      // 最简单的饼图的初始值
      let option = {
           tooltip: {},
            series: [{
            type: 'pie',
            data: [],
            }]
       };

        // 第一次的初始化
      myChart.setOption(option);

       // 配置个性化的使用
      let bgColor = '#fff';
      let title = '总量';
      let color = ['#0E7CE2', '#FF8352', '#E271DE', '#F8456B', '#00FFFF', '#4AEAB0'];
       
       // 测试数据
      let echartData = [{
              name: "A类",
              value: "3720"
          },
          {
              name: "B类",
              value: "2920"
          },
          {
              name: "C类",
              value: "2200"
          },
          {
              name: "D类",
              value: "1420"
          }
      ];
      
      // 计算数据的总数
      let formatNumber = function(num) {
          let reg = /(?=(\B)(\d{3})+$)/g;
          return num.toString().replace(reg, ',');
      }

      // 模拟数据求和
        //   let total = echartData.reduce((a, b) => {
        //       return a + b.value * 1
        //   }, 0);
        // 真实数据的求和 并不行，需要优化
        let total = this.originData.reduce((a, b) => {
            return a + b.value * 1
        }, 0);

        let option1 = {
            backgroundColor: bgColor,
            color: color,
            title: [{
                // text: '{name|' + title + '}\n{val|' + formatNumber(total) + '}',
                text: '用户的下载来源',
                top: 'center',
                left: 'center',
                textStyle: {
                    rich: {
                        name: {
                            fontSize: 14,
                            fontWeight: 'normal',
                            color: '#666666',
                            padding: [10, 0]
                        },
                        val: {
                            fontSize: 32,
                            fontWeight: 'bold',
                            color: '#333333',
                        }
                    }
                }
            },{
                text: '单位：人数',
                top: 0,
                left: 20,
                textStyle: {
                    fontSize: 14,
                    color:'#666666',
                    fontWeight: 400
                }
            }],
            series: [{
                type: 'pie',
                radius: ['45%', '60%'],
                center: ['50%', '50%'],
               // data: echartData,    
                data:this.originData,
                hoverAnimation: false,
                itemStyle: {
                    normal: {
                        borderColor: bgColor,
                        borderWidth: 2
                    }
                },
                labelLine: {
                    normal: {
                        length: 20,
                        length2: 120,
                        lineStyle: {
                            color: '#e6e6e6'
                        }
                    }
                },
                label: {
                    normal: {
                        formatter: params => {
                            return (
                                '{icon|●}{name|' + params.name + '}{value|' +
                                formatNumber(params.value) + '}'
                            );
                        },
                        padding: [0 , -100, 25, -100],
                        rich: {
                            icon: {
                                fontSize: 16
                            },
                            name: {
                                fontSize: 14,
                                padding: [0, 10, 0, 4],
                                color: '#666666'
                            },
                            value: {
                                fontSize: 18,
                                fontWeight: 'bold',
                                color: '#333333'
                            }
                        }
                    }
                },
            }]
        };

        // 实现数据的绑定
        statisticOrigin().then(res =>{
            //数据库的数据进行绑定
                let num = 0;
            for(let i = 0 ; i < res.length; i++){
                    this.originData[i] = res[i];
                    num += res[i].value;                 
            }
                
                console.log(num);
                this.originNumber = num;
                myChart.setOption(option1);
                window.addEventListener('resize', () => {
                    if (myChart) {
                        myChart.resize();
                    }
                });
        })
     // myChart.setOption(option1);
        //   window.addEventListener('resize', () => {
        // 			if (myChart) {
        // 				myChart.resize();
        // 			}
        // 		});
    },

    // 用户在线时长柱状图
    lineChart() {
      // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById("online"));
        
        // 测试数据
        let bgColor = '#fff';
        let data = {
            xData: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11','12'],
            yData: [100, 200, 300, 300, 500, 600, 700, 800, 900, 900, 1000,1300]
        }
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

         // 第一次最简单的初始化ehars图
        myChart.setOption(option1);

        // 个性化图表的样式
        let option = {
                backgroundColor: bgColor,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: [
                    {
                        top: 30,
                        bottom: 70,
                        left: 30
                    },
                    {
                        height: 30,
                        bottom: 0
                    }
                ],
                xAxis: [{
                    name: '时长（min）',// 给X轴加单位
                    nameLocation: 'middle',
                    type: 'category',
                    //data: data.xData,
                    data: this.onlineXarr,
                    gridIndex: 0,
                    axisLabel: {
                        color: '#333',
                        interval: 0
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#e7e7e7'
                        }
                    },
                    axisTick: {
                        lineStyle: {
                            color: '#e7e7e7'
                        }
                    },
                    zlevel: 2
                }, {
                    type: 'category',
                    gridIndex: 1,
                    axisLine: {
                        show: false
                    },
                    zlevel: 1
                }],
                yAxis: [{
                    name:'在线人数',
                    type: 'value',
                    gridIndex: 0,
                    minInterval: 1,  // 显示整数
                    axisLabel: {
                        color: '#333'
                    },
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#ccc'
                        }
                    },
                    axisTick: {
                        lineStyle: {
                            color: '#ccc'
                        }
                    }
                },{
                    type: 'value',
                    gridIndex: 1,
                    axisLabel: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    }
                }],
                series: [{
                   // data: data.yData,
                   data: this.onlineSarr,
                    type: 'bar',
                    barWidth: 35,
                    label: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: '#555'
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: (params) => {
                                let colors = ['#4150d8', '#28bf7e', '#ed7c2f', '#f2a93b', '#f9cf36', '#4a5bdc', '#4cd698', '#f4914e', '#fcb75b', '#ffe180', '#b6c2ff', '#96edc1']
                                return colors[params.dataIndex]
                            }
                        }
                    },
                    xAxisIndex: 0,
                    yAxisIndex: 0
                },
                ]
            };
           
           // 动态渲染数据
            statisticHour().then(res =>{
                for(let i = 0; i<res.length; i++){
                     this.onlineXarr[i] = res[i].name;
                     this.onlineSarr[i] = res[i].value;
                }
                myChart.setOption(option);
                window.addEventListener('resize', () => {
                    if (myChart) {
                        myChart.resize();
                    }
                }); 
            })
           
    },
  
   // 用户使用手机情况
    mobileChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("mobile"));
           
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
      statisticMobile().then(res =>{
            for(let i = 0; i < res.length;i++){
                 this.pieData[i] = res[i];
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
          text: '用户使用手机占比',
          x: 'center',
          y: 'center',
          textStyle: {
              fontSize: 17
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

   // 用户登陆时间点的人数
   loginTimeChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("longtime"));
   
     // 简单的初始化echars
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

     let option = {
				backgroundColor: '#fff',
				tooltip: {
					trigger: 'axis',
					backgroundColor: 'rgba(0,0,0,0.5)',
					axisPointer: {
						lineStyle: {
							color: {
								type: 'linear',
								x: 0,
								y: 0,
								x2: 0,
								y2: 1,
								colorStops: [{
									offset: 0,
									color: 'red'
								}, {
									offset: 0.5,
									color: '#48b3FF',
								}, {
									offset: 1,
									color: '#d9efff'
								}],
								global: false
							}
						},
					},
				},
				grid: {
					top: '10%',
					left: '5%',
					right: '3%',
					bottom: '15%',
					// containLabel: true
				},
				xAxis: [{
                    //name:'登陆时间',
                    nameLocation:'middle',
					type: 'category',
					color: '#59588D',
					axisLine: {
						show: true
					},
					axisLabel: {
						color: '#282828'
					},
					splitLine: {
						// show: true
					},

					axisTick: {
						show: false
					},
					// boundaryGap: true,
					data: ['0-2', '2-4', '4-6', '6-8', '8-10', '10-12', '12-14', '14-16', '16-18', '18-20', '20-22', '22-24'],

				}],

				yAxis: [{
                    name:'登陆人数',
                    type: 'value',
                    minInterval: 1,
					min: 0,
					splitNumber: 4,
					splitLine: {
						show: true,

					},
					axisLine: {
						show: true,
					},
					axisLabel: {
						show: true,
						// margin: 20,
						textStyle: {
							color: '#737373',

						},
					},
					axisTick: {
						show: false,
					},
					splitLine: {
						lineStyle: {
							color: 'rgba(131,101,101,0.2)',
							type: 'dashed',
						}
					}
				}],
				series: [{
					name: '',
					type: 'line',
					// smooth: true, //是否平滑
					showAllSymbol: true,
					symbol: 'circle',
					symbolSize: 10,
					lineStyle: {
						normal: {
							color: "#48B3FF"
						},
					},
					label: {
						show: false,
						position: 'top',
						textStyle: {
							color: '#48B3FF',
						}
					},

					itemStyle: {
						color: "#FFF",
						borderColor: "#48B3FF",
						borderWidth: 2,
						
					},
					tooltip: {
						show: true
					},
					areaStyle: {
						normal: {
							color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
									offset: 0,
									color: 'rgba(195,230,255,1)'
								},
								{
									offset: 1,
									color: 'rgba(195,230,255,0.1)'
								}
							], false),
							shadowColor: 'rgba(195,230,255,0.1)',
							shadowBlur: 20
						}
					},
					data: [55, 35, 62, 55, 97, 64, 44, 66, 78, 82, 33, 77],
				}]
			};

     // 加载动态数据 接口待完成

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
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
    this.lineChart();
    this.mobileChart();
    this.loginTimeChart();
  },
}
</script>

<style scoped>
.container{
  background-color: '#fff'
}
.header{
  text-align: center; /*让div内部文字居中*/
	border-radius: 20px;
	height: 50px;
	margin: 20px;
	top: 10px;
	left: 0;
	right: 0;
	bottom: 0;
}
</style>