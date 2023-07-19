<template>
    <div >
        <div class="titles" style="margin-top: 2%;margin-bottom: 1%">
            <h2 style="color:#0C4D4B ">student年级分布统计</h2>
        </div>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main2" :style="{width: '100%', height: '550px'}"></div>
    </div>
</template>
 
<script>
import axios from 'axios'
import * as echarts from 'echarts'
export default {
    name: 'CodeforcesRating',
    data () {
        return {
            msg: 'Welcome to Your Vue.js App'
        }
    },
    mounted(){
        this.drawLine2();
    },
    methods: {
        drawLine2(){
            console.log("gradetest")
            axios.get('/stu/info/acmer/student/grades')
                .then(response => {
                    var arr = [];
                    var value1 = response.data.data[0];
                    console.log(value1)
                    var value2 = response.data.data[1];
                    var value3 = response.data.data[2];
                    arr.push({value:value1, name:'2019级'});
                    arr.push({value:value2, name:'2020级'});
                    arr.push({value:value3, name:'2021级'});

                     // 基于准备好的dom，初始化echarts实例
                    let myChart = this.$echarts.init(document.getElementById('main2'))
           
                    // 绘制图表
                    myChart.setOption({
                        backgroundColor: '#FFFFFF',
                        title: {
                            text: '年级分布统计',
                            subtext: 'BUCTcoder各年级分布',
                            left: 'center',
                            top: 40,
                            textStyle: {
                            color: '#797979'
                            }
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c} ({d}%)'
                        },
                        legend: {
                            bottom: 10,
                            left: 'center',
                            data: ['2019级', '2020级', '2021级']
                        },
                        series : [
                        {
                            name: '年级分布',
                            type: 'pie',
                            radius: '50%',
                            center: ['50%', '50%'],
                            selectedMode: 'single',
                            data: arr,
                            label: {
                                normal: {
                                    //position: 'inner',
                                    //formatter:'{d}%',
                                    //textStyle: {
                                        color: '#797979'//'rgba(255, 255, 255, 0.3)'
                                    //}
                                }
                            },
                            labelLine: {
                                lineStyle: {
                                    color: '#797979'//'rgba(255, 255, 255, 0.3)'
                                },
                                smooth: 0.2,
                                length: 10,
                                length2: 20
                            },
                            emphasis: {
                                itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                            // itemStyle: {
                            //     normal: {
                            //         shadowBlur: 200,
                            //         shadowColor: 'rgba(0, 0, 0, 0.5)'
                            //     }
                            // }
                        }]
                    });
                })
        }
    }
 
}
</script>
 
<style scoped>
 
</style>