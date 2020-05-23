$(function () {
    // 日期重置
    $('#reset').on('click', function () {
        $('#start').val("");
    });

    // 提交查询
    $('#sub').on('click', function () {
        var start = $('#start').val();  // 查询年份
        if (start === "") {
            return alert("请完整填写查询年份.");
        }
        subs(start);  // 查询函数
    });
});

window.onload = function () {
    var year = doHandleYear();
    subs(year);  // 查询函数
};

// 日期插件调用方法
$(function () {
    $('#datetimepicker1').datetimepicker({
        format: 'YYYY',
        locale: moment.locale('zh-cn')
    });
});

// 获取当前年
function doHandleYear() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    return year;
}

// 查询请求
var subs = function (start) {
    // 验证通过,执行请求查询
    var url = "http://127.0.0.1:80/selectWagesAll";
    var date = {
        "wagesDate": start
    };
    $.get(url, date, function (data) {

        if (data.total.zyc === 0 && data.total.lq === 0) {
            alert("当前年份无数据展示.");
        }

        $('#year').html(data.year);
        $('#total2').html(data.total.zyc);
        $('#total1').html(data.total.lq);

        option.series[0].data = data.zyc;
        option.series[1].data = data.lq;
        myChart.setOption(option, true);
    });
};