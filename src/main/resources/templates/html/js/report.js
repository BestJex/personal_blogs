// 日期插件调用方法
$(function () {
    $('#datetimepicker1').datetimepicker({
        format: 'YYYY-MM',
        locale: moment.locale('zh-cn')
    });
});
$(function () {
    $('#datetimepicker2').datetimepicker({
        format: 'YYYY-MM',
        locale: moment.locale('zh-cn')
    });
});

$(function () {

    /**
     *  生成报报表当前时间
     */
    var day2 = new Date();
    day2.setTime(day2.getTime());
    var nowDate = day2.getFullYear() + "-" + (day2.getMonth() + 1) + "-" + day2.getDate();
    $('#date').html(nowDate);  // 赋值

    /**
     * 提交查询
     */
    $('#sub').on('click', function () {
        var start = $('#start').val();  // 开始日期
        var end = $('#end').val();  // 结束日期

        // 判断不能让开始日期大于结束日期
        if (start === "" || end === "") {
            return alert("请完整填写查询日期.");
        }
        if (start.split('-')[0] > end.split('-')[0] ||
            (start.split('-')[0] === end.split('-')[0] &&
                start.split('-')[1] > end.split('-')[1])) {
            return alert("开始日期不能大于结束日期,请重新选择.");
        }

        // 验证通过,执行请求查询
        var url = "/testPoi/getReport";
        var date = {
            "start": start,
            "end": end
        };
        $.get(url, date, function (data) {
            var table = "";
            var tr_tr = "";
            for (var i = 0; i < data.length; i++) {
                var tr = "";
                for (var j = 0; j < data[i].length; j++) {
                    tr += "<td>" + data[i][j] + "</td>";
                }
                tr_tr = "<tr>" + tr + "</tr>";
                table += tr_tr;
            }
            if (data.length === 0) {
                table = "<tr>" + "<td colspan=\"12\" style=\"background-color: #E6E6FA;\">暂无数据.</td>" + "</tr>"
            }
            $('#tr').html(table);  // 赋值
        });
    });

    /**
     *  日期重置
     */
    $('#reset').on('click', function () {
        $('#start').val("");
        $('#end').val("");
    });
});

