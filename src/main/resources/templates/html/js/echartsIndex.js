// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data: ['张宜成', '郎倩']
    },
    toolbox: {
        show: true,
        orient: 'vertical',
        x: 'right',
        y: 'center',
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '张宜成',
            type: 'bar',
            data: [],
            markPoint: {  // 设置最大值和最小值显示
                data: [
                    {type: 'max', name: '最大值', symbolSize: '50'},
                    {type: 'min', name: '最小值', symbolSize: '50'}
                ]
            },
            markLine: {  //显示平均水平线
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        },
        {
            name: '郎倩',
            type: 'bar',
            data: [],
            markPoint: {  //设置最大值和最小值显示
                data: [
                    {type: 'max', name: '最大值', symbolSize: '50'},
                    {type: 'min', name: '最小值', symbolSize: '50'}
                ]
            },
            markLine: {  //显示平均水平线
                data: [
                    {type: 'average', name: '平均值'}
                ]
            }
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option, true);

//全局设置Echrts - 根据窗口的大小变动图表
window.onresize = function () {
    myChart.resize();
};