// 初始化echarts实例
var myChart = echarts.init(document.getElementById('container'));

var city = 5666;
// 全国省份列表
var dataMap = [
    {name: '北京'},
    {name: '天津'},
    {name: '上海'},
    {name: '重庆'},
    {name: '河北'},
    {name: '河南'},
    {name: '云南'},
    {name: '辽宁'},
    {name: '黑龙江'},
    {name: '湖南'},
    {name: '安徽'},
    {name: '山东'},
    {name: '新疆'},
    {name: '江苏'},
    {name: '浙江'},
    {name: '江西'},
    {name: '湖北'},
    {name: '广西'},
    {name: '甘肃'},
    {name: '山西'},
    {name: '内蒙古'},
    {name: '陕西'},
    {name: '吉林'},
    {name: '福建'},
    {name: '贵州'},
    {name: '广东'},
    {name: '青海'},
    {name: '西藏'},
    {name: '四川'},
    {name: '宁夏'},
    {name: '海南'},
    {name: '台湾'},
    {name: '香港'},
    {name: '澳门'}];
// 需要在页面上直接标记出来的城市
var specialMap = ['山东'];
// 对dataMap进行处理，使其可以直接在页面上展示
for (var i = 0; i < specialMap.length; i++) {
    for (var j = 0; j < dataMap.length; j++) {
        if (specialMap[i] === dataMap[j].name) {
            dataMap[j].selected = true;
            break;
        }
    }
}
// 绘制图表，准备数据
var option = {
    tooltip: {
        formatter: function (params) {
            return '<p style="font-size:18px;">' + params.name + '</p><p style="font-size:15px">这里是' + params.city + '</p>';
        },
        backgroundColor: "#ff7f50",//提示标签背景颜色
        textStyle: {color: "#fff"} //提示标签字体颜色
    },
    series: [
        {
            name: '中国',
            type: 'map',
            mapType: 'china',
            selectedMode: 'multiple',
            label: {
                normal: {
                    show: true // 显示省份标签
                    // textStyle:{color:"#c71585"} //省份标签字体颜色
                },
                emphasis: {
                    show: true // 对应的鼠标悬浮效果
                    // textStyle:{color:"#800080"}
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: .3, // 区域边框宽度
                    borderColor: '#009fe8' // 区域边框颜色
                    // areaColor:"#ffefd5", // 区域颜色
                },
                emphasis: {
                    borderWidth: .6,
                    borderColor: '#4b0082',
                    areaColor: "#ffdead"
                }
            },
            data: dataMap
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option, true);

//全局设置Echrts - 根据窗口的大小变动图表
window.onresize = function () {
    myChart.resize();
};