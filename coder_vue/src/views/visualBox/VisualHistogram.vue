<template>
    <div >
        <div class="titles" style="margin-top: 2%;margin-bottom: 1%">
            <h1>Codeforces积分区间统计</h1>
            <h3>Codeforces积分区间统计（横轴积分，纵轴人数）</h3>
        </div>
        
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main1" :style="{width: '100%', height: '600px'}"></div>
    </div>
</template>
 
<script>
    import axios from 'axios'
    import * as echarts from 'echarts'
    export default {
        //name: 'CodeforcesRating',
        data () {
            return {
                msg: 'Welcome to Your Vue.js App'
            }
        },
        mounted(){
            this.drawLine1();
        },
        methods: {
            drawLine1(){
                //console.log(1212)
                axios.get('/stu/info/acmer/echarts/numbers')
                    .then(response => {
                    // 获取到后端返回的数据
                    const data = response.data.data;
                    console.log(data)
                    // 基于准备好的dom，初始化echarts实例
                    let myChart = this.$echarts.init(document.getElementById('main1'))
                    // 绘制图表
                    myChart.setOption({
                        backgroundColor: '#191E40',
                        title: {
                            text: 'CodeforcesRating',
                            subtext: 'Codeforces积分区间统计（横轴积分，纵轴人数）',
                            left: 'center'
                        },
                        //subtitle: { text: 'Codeforces积分区间统计（横轴积分，纵轴人数）' },
                        tooltip: {
                            trigger: 'axis',
                            backgroundColor:'#5bc0de',
                            textStyle:{
                                color:'#ffffff',
                                fontSize:18,
                                fontWeight:'normal',
                            },
                            axisPointer: {
                                type: 'shadow',
                                label: {
                                    fontSize:16,
                                    show: true,
                                    backgroundColor: '#7B7DDC'
                                }
                            }
                        },
                        xAxis: {
                            data: ["1300","1400","1500","1600","1700","1800","1900"],
                            axisLine: {
                                lineStyle: {
                                    color: '#ffffff'
                                }
                            },
                            axisTick:{
                                show:false,
                            },
                        },
                        yAxis: {
                            splitLine: {show: false},
                            axisLine: {
                                lineStyle: {
                                    color: '#ffffff',
                                }
                            },
                            
                            axisLabel:{
                                formatter:'{value} ',
                            }
                        },
                        series: [{
                            name: '人数',
                            type: 'bar',
                            
                            itemStyle: {
                                normal: {
                                    barBorderRadius: 5,
                                    //颜色渐变
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 1, color: '#33cabb'},
                                            {offset: 0, color: '#3EACE5'}
                                        ]
                                    )
                                }
                            },
                            data: data
                        }]
                    });
                })
                .catch(error => {
                console.error(error);
                });
            }
        }
    
    }
</script>
 
<style scoped>
 
</style>