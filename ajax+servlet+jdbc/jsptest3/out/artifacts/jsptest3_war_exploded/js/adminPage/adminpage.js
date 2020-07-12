var count = 0;
function editA() {
    var title=$(this).parents("tr").children("td").eq(1).text();
    var opentime=$(this).parents("tr").children("td").eq(2).text();
    var closetime=$(this).parents("tr").children("td").eq(3).text();
    var flag = confirm("是否要进入" + title + "的编辑？");
    if (flag) {
        location.href="paperServlet?action=turn&title="+title+"&opentime="+opentime+"&closetime="+closetime;
    }
}
function countA() {
    var title=$(this).parents("tr").children("td").eq(1).text();
    var opentime=$(this).parents("tr").children("td").eq(2).text();
    var closetime=$(this).parents("tr").children("td").eq(3).text();
    var flag = confirm("是否要进入" + title + "的统计？");
    if (flag) {
        location.href="paperServlet?action=turnCountPage&title="+title+"&opentime="+opentime+"&closetime="+closetime;
    }
}

    // 该函数用于在界面加载后显示已有试卷信息。
$(function () {
    $.ajax({
        url: "paperServlet",
        data:"action=showPaperinfo",
        type: "GET",
        success: function (data) {
            // console.log(data)
            var jsonobj = JSON.parse(data);
            count = jsonobj.length;
            for (var i = 0; i < jsonobj.length; i++) {
                var showtitle = jsonobj[i].title;
                var showopentime = jsonobj[i].openDate;
                var showclosetime = jsonobj[i].closeDate;
                $("<tr></tr>").append("<td>" + (i + 1) + "</td>")
                    .append("<td>" + showtitle + "</td>")
                    .append("<td>" + showopentime + "</td>")
                    .append("<td>" + showclosetime + "</td>")
                    .append("<td><a href='#' id='delete'>删除</a></td>")
                    .append("<td><a href='#' id='edit'>编辑</a></td>")
                    .append("<td><a href='#' id='count'>统计</a></td>")
                    .appendTo("#paperTable");
            }
        },
        dataType: "text"
    })
});
$(function () {
    $("#out").click(function () {
            location.href="userServlet?action=destroySession";
    })
})
//该函数用于删除问卷
$(function () {
    //删除问卷的方法
    function delA() {
        //获取要删除试卷的名字
        var name = $(this).parents("tr").find("td:eq(1)").text();
        //弹出一个确认框
        var flag = confirm("确认删除" + name + "吗？");
        if (flag) {
            //删除当前a所在的tr
            var title=$(this).parents("tr").children("td").eq(1).text();
            var opentime=$(this).parents("tr").children("td").eq(2).text();
            var closetime=$(this).parents("tr").children("td").eq(3).text();
            $.getJSON("paperServlet","action=deletePaper&title="+title+"&opentime="+opentime+"&closetime="+closetime,function (data) {
                // console.log(data);
                // alert(data);
                if (data.title>0){
                    $("#error").text("删除成功").css('color','#f00')
                }
                else
                    $("#error").text("删除失败").css('color','#f00')

            })
            count = count - 1;
            $(this).parents("tr").remove();

        }
        //取消默认行为
        return false;
    }

    //删除试卷
    //$("a").click(delA);
    $("#delete").live("click", delA);
    $("#edit").live("click", editA);
    $("#count").live("click", countA);
})
//该函数用于增加试卷
$(function () {
    $("#addButton").click(function (event) {
        //获取用户填写的内容
        var title1 = $("#title").val();
        var openTime = $("#openTime").val();
        var closeTime = $("#closeTime").val();
        var opendate = new Date(openTime)
        var closedate = new Date(closeTime)
        // alert(title1)
        $.getJSON("paperServlet","action=addPaper&title1="+title1+"&opentime="+openTime+"&closetime="+closeTime,function (data) {
            // console.log(data);
            // alert(data);
            if ((closedate.getTime() - opendate.getTime()) > 6000&&title1!=""&&!data.paper) {
                count = count + 1;
                $("<tr></tr>").append("<td>" + count + "</td>")
                    .append("<td>" + title1 + "</td>")
                    .append("<td>" + openTime + "</td>")
                    .append("<td>" + closeTime + "</td>")
                    .append("<td><a href='#' id='delete'>删除</a></td>")
                    .append("<td><a href='#' id='edit'>编辑</a></td>")
                    .append("<td><a href='#' id='count'>统计</a></td>")
                    .appendTo("#paperTable");
                $("#error").text("添加成功").css('color','#f00')
            }else if(title1==""){
                $("#error").text("请输入问卷名").css('color','#f00')
                event.preventDefault();
            }else if((closedate.getTime() - opendate.getTime()) < 6000) {
                $("#error").text("请将问卷的测试时间设置为一分钟以上").css('color','#f00')
                event.preventDefault();
            }else if (data.paper){
                $("#error").text("该问卷已存在").css('color','#f00')
                event.preventDefault();
            }
        })

    });
})

