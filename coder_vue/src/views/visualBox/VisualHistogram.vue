<template>
    <div>
        <h1>mistakescollection</h1>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main1" style="width: 600px;height:400px;"></div>
        <div id="main2" style="width: 600px;height:400px;"></div>
    </div>
</template>
 
<script>
 
export default {
    name: 'MistakesCollection',
    data () {
        return {
            msg: 'Welcome to Your Vue.js App'
        }
    },
    mounted(){
        this.drawLine1();
        this.drawLine2();
    },
    methods: {
        drawLine1(){
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(document.getElementById('main1'))
            // 绘制图表
            myChart.setOption({
                //backgroundColor: '#2c343c',
                title: { text: 'drawLine1' },
                tooltip: {},
                xAxis: {
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            });
        },
        drawLine2(){
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(document.getElementById('main2'))
            // 绘制图表
            myChart.setOption({
                backgroundColor: '#2c343c',
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        data:[
                            {value:235, name:'视频广告'},
                            {value:274, name:'联盟广告'},
                            {value:310, name:'邮件营销'},
                            {value:335, name:'直接访问'},
                            {value:400, name:'搜索引擎'}
                        ],
                        roseType: 'angle',
                        label: {
                            normal: {
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                lineStyle: {
                                    color: 'rgba(255, 255, 255, 0.3)'
                                }
                            }
                        },
                        itemStyle: {
                            normal: {
                                shadowBlur: 200,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            });
        }
    }
 
}
</script>
 
<style scoped>
 
</style>