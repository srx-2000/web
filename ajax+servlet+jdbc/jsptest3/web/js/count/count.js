$(function () {
    $.getJSON("paperServlet","action=turnCount",function (data) {
        var count=JSON.parse(data)
        $.each(count,function () {
            console.log(data)
            $("#counts").append("题目编号："+this.Question_Id+"."+this.questionContent+"</br>")
            $("#counts").append("选项编号："+this.Selecgt_Id+"."+this.selectContent+"</br>")
            $("#counts").append("选择人数："+this.num+"</br>")
            $("#counts").append("<hr>")

        })
    })
})
$(function () {
    $("#admin").click(function () {
        location.href = "paperServlet?action=returnPage";
    })
})