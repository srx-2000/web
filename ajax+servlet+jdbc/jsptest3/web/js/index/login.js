$(function () {
    $("#submit").click(function (event) {
        var user=0;
        var pass=0;
        // $("#username").change(function () {
        //     var pattern = /[A-Za-z0-9_\-\u4e00-\u9fa5]+/
        var username = $("#username").val();
        if (username.length==0){
            $("#username_error").text("用户名不能为空").css('color','#f00')
        }else {
            $("#username_error").hide()
            user=1;
        }
        // })
        // $("#password").change(function () {
        var pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/
        var password = $("#password").val();
        if (!pattern.test(password)){
            $("#password_error").text("请填写正确的密码").css('color','#f00')
        }else {
            $("#password_error").hide()
            pass=1
        }
        // })
        if(pass==0&&user==0){
            event.preventDefault();
            $("#submit_error").text("请填写正确的用户名和密码").css('color','#f00')
        }
    })

})