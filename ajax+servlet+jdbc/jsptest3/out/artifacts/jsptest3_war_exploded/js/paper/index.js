//用来显示数据库中已有的题目（仅是题目，没有选项）
$(function () {
    $.ajax({
        url: "questionServlet",
        data: "action=showQuestioninfo",
        type: "GET",
        success: function (data) {
            var jsonarray = JSON.parse(data);

            // alert(data)
            function sortorder(a, b) {
                return a.questionOrder - b.questionOrder;
            }

            jsonarray.sort(sortorder);
            // for (var i = 0; i <jsonarray.length ; i++) {
            //     console.log(jsonarray[i])
            // }
            for (var i = 0; i < jsonarray.length; i++) {
                // alert(jsonarray[i].questionContent)
                // alert(jsonarray[i].questionType)
                var index = jsonarray[i].questionType.toString();
                var movie_box = '<div class="movie_box" style="border: 1px solid rgb(255, 255, 255);"></div>';
                var Grade = $(".yd_box").find(".movie_box").length + 1;
                // alert(Grade)
                // console.log("add"+typeof index);
                switch (index) {
                    case "0": //单选
                    case "1": //多选
                        var wjdc_list = '<ul class="wjdc_list"></ul>'; //问答 单选 多选
                        var danxuan = "";
                        // console.log(wjdc_list);
                        if (index == "0") {
                            danxuan = '【单选】';
                        } else if (index == "1") {
                            danxuan = '【多选】';
                        }
                        // console.log(danxuan);
                        wjdc_list = $(wjdc_list).append(' <li><div class="tm_btitlt"><i class="nmb">' + Grade + '</i>. <i class="btwenzi">' + jsonarray[i].questionContent + '</i><span class="tip_wz">' + danxuan + '</span></div></li>');
                        // console.log(wjdc_list);
                        // console.log(movie_box)
                        movie_box = $(movie_box).append(wjdc_list);
                        // console.log(movie_box)
                        movie_box = $(movie_box).append('<div class="dx_box" data-t="' + index + '"></div>');
                        movie_box = $(movie_box).append('<div class="questionId" question_data="' + jsonarray[i].questionId + '"></div>');
                        break;
                }
                $(movie_box).hover(function () {
                    var html_cz = "<div class='kzqy_czbut'><a href='javascript:void(0)' class='sy'>上移</a><a href='javascript:void(0)'  class='xy'>下移</a><a href='javascript:void(0)'  class='bianji'>编辑</a><a href='javascript:void(0)' class='del' >删除</a></div>"
                    $(this).css({
                        "border": "1px solid #0099ff"
                    });
                    $(this).children(".wjdc_list").after(html_cz);
                }, function () {
                    $(this).css({
                        "border": "1px solid #fff"
                    });
                    $(this).children(".kzqy_czbut").remove();
                    //$(this).children(".dx_box").hide();
                });
                $(".yd_box").append(movie_box);
            }

        },
        dataType: "text"
    })

});


$(function () {
    $("#admin").click(function () {
        location.href = "paperServlet?action=returnPage";
    })
})
$(window).load(function () {
    var box = $(".movie_box")
    $.each(box, function () {
        var boxItem = $(this)
        var questionId = boxItem.find(".questionId").attr("question_data")
        var questionType = boxItem.find(".dx_box").attr("data-t")
        // console.log(questionId)
        $.getJSON("selectServlet", "action=showSelectinfo&questionId=" + questionId, function (data) {
            // console.log(data)
            $.each(data, function () {
                var dataItem = (this)
                var selectId = dataItem.selectId
                var selectContent = dataItem.selectContent
                var questionId = dataItem.questionId
                console.log(selectContent)
                console.log(questionId)
                console.log(selectId)
                if (questionType == "0")
                    var inputType = 'radio';
                if (questionType == "1") {
                    inputType = 'checkbox';
                }
                var jcxxxx = $(this).parent(".bjqxwc_box").parent(".dx_box"); //编辑题目区
                var li = '<li><label><input name="a" type="' + inputType + '" value=""><span>' + selectContent + '</span></label><div class="selectId" select_data="' + selectId + '"></div></li>';
                boxItem.children(".wjdc_list").append(li);
            })
            // var jsonlist=JSON.parse(data)
            // console.log(jsonlist)
        })
    })
})
$(document).ready(function (e) {
    $('#addquerstions').change(function () {
        // debugger
        var index = $(this).val(); //选择添加问题的类型
        // console.log("index"+typeof index)
        if (index == "-1") {
            return;
        }
        var movie_box = '<div class="movie_box" style="border: 1px solid rgb(255, 255, 255);"></div>';
        var Grade = $(".yd_box").find(".movie_box").length + 1;
        switch (index) {
            case "0": //单选
            case "1": //多选
            case "2": //问答
                var wjdc_list = '<ul class="wjdc_list"></ul>'; //问答 单选 多选
                var danxuan = "";
                if (index == "0") {
                    danxuan = '【单选】';
                } else if (index == "1") {
                    danxuan = '【多选】';
                } else if (index == "2") {
                    danxuan = '【问答】';
                }
                wjdc_list = $(wjdc_list).append(' <li><div class="tm_btitlt"><i class="nmb">' + Grade + '</i>. <i class="btwenzi">请编辑问题？</i><span class="tip_wz">' + danxuan + '</span></div></li>');
                // if (index == "2") {
                //     wjdc_list = $(wjdc_list).append('<li>  <label> <textarea name="" cols="" rows="" class="input_wenbk btwen_text btwen_text_dx" ></textarea></label> </li>');
                // }
                movie_box = $(movie_box).append(wjdc_list);
                movie_box = $(movie_box).append('<div class="dx_box" data-t="' + index + '"></div>');
                break;

        }
        $(movie_box).hover(function () {
            var html_cz = "<div class='kzqy_czbut'><a href='javascript:void(0)' class='sy'>上移</a><a href='javascript:void(0)'  class='xy'>下移</a><a href='javascript:void(0)'  class='bianji'>编辑</a><a href='javascript:void(0)' class='del' >删除</a></div>"
            $(this).css({
                "border": "1px solid #0099ff"
            });
            $(this).children(".wjdc_list").after(html_cz);
        }, function () {
            $(this).css({
                "border": "1px solid #fff"
            });
            $(this).children(".kzqy_czbut").remove();
            //$(this).children(".dx_box").hide();
        });
        $(".yd_box").append(movie_box);

    });

    //鼠标移上去显示按钮
    $(".movie_box").hover(function () {
        var html_cz = "<div class='kzqy_czbut'><a href='javascript:void(0)' class='sy'>上移</a><a href='javascript:void(0)'  class='xy'>下移</a><a href='javascript:void(0)'  class='bianji'>编辑</a><a href='javascript:void(0)' class='del' >删除</a></div>"
        $(this).css({
            "border": "1px solid #0099ff"
        });
        $(this).children(".wjdc_list").after(html_cz);
    }, function () {
        $(this).css({
            "border": "1px solid #fff"
        });
        $(this).children(".kzqy_czbut").remove();
        //$(this).children(".dx_box").hide();
    });

    //下移
    $(".xy").live("click", function () {
        //文字的长度
        var leng = $(".yd_box").children(".movie_box").length;
        var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index();
        var czxxl = $(this).parent(".kzqy_czbut").parent(".movie_box");
        var order = 0;
        var childorder = 0;
        var questionId = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".questionId").attr("question_data");
        var prevquestionId = $(this).parent(".kzqy_czbut").parent(".movie_box").next().find(".questionId").attr("question_data");
        if (dqgs < leng - 1) {
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            var xyghtml = czxx.next().html();
            var syghtml = czxx.html();
            czxx.next().html(syghtml);
            czxx.html(xyghtml);
            //序号
            czxx.children(".wjdc_list").find(".nmb").text(dqgs + 1);
            czxx.next().children(".wjdc_list").find(".nmb").text(dqgs + 2);
            order = parseInt(czxxl.children(".wjdc_list").find(".nmb").text()) + 1;
            childorder = parseInt(czxx.next().children(".wjdc_list").find(".nmb").text()) - 1;
        } else {
            alert("到底了");
        }
        $.getJSON("questionServlet", "action=movie&type=up&order=" + order + "&questionId=" + questionId + "&childorder=" + childorder + "&prevquestionId=" + prevquestionId)

    });
    //上移
    $(".sy").live("click", function () {
        //文字的长度
        var leng = $(".yd_box").children(".movie_box").length;
        var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index();
        var czxxl = $(this).parent(".kzqy_czbut").parent(".movie_box");
        var order = 0;
        var childorder = 0;
        var questionId = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".questionId").attr("question_data");
        var prevquestionId = $(this).parent(".kzqy_czbut").parent(".movie_box").prev().find(".questionId").attr("question_data");
        if (dqgs > 0) {
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            var xyghtml = czxx.prev().html();
            var syghtml = czxx.html();
            czxx.prev().html(syghtml);
            czxx.html(xyghtml);
            //序号
            czxx.children(".wjdc_list").find(".nmb").text(dqgs + 1);
            czxx.prev().children(".wjdc_list").find(".nmb").text(dqgs);
            order = parseInt(czxxl.children(".wjdc_list").find(".nmb").text()) - 1;
            childorder = parseInt(czxx.prev().children(".wjdc_list").find(".nmb").text()) + 1;
        } else {
            alert("到头了");
        }
        $.getJSON("questionServlet", "action=movie&type=up&order=" + order + "&questionId=" + questionId + "&childorder=" + childorder + "&prevquestionId=" + prevquestionId)
    });
    //删除
    $(".del").live("click", function () {
        var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
        var zgtitle_gs = czxx.parent(".yd_box").find(".movie_box").length;
        var text = czxx.find(".btwenzi").text();
        var dx_rq = czxx.find(".dx_box");
        var title = dx_rq.attr("data-t");
        var questionId = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".questionId").attr("question_data");
        // console.log(questionId)
        var xh_num = 1;
        if (questionId == undefined) {
            czxx.parent(".yd_box").find(".movie_box").each(function () {
                $(".yd_box").children(".movie_box").eq(xh_num).find(".nmb").text(xh_num);
                xh_num++;
                //alert(xh_num);
            });
            czxx.remove();
        } else {
            var flag = confirm("是否要删除[" + text + "]？");
            if (flag) {
                $.getJSON("questionServlet", "action=deleteQuestion&questionId=" + questionId, function (data) {
                    // alert(data)
                    czxx.parent(".yd_box").find(".movie_box").each(function () {
                        $(".yd_box").children(".movie_box").eq(xh_num).find(".nmb").text(xh_num);
                        xh_num++;
                        //alert(xh_num);
                    });

                    czxx.remove();
                });
            }

        }
        //重新编号

    });
    //编辑
    $(".bianji").live("click", function () {
        //编辑的时候禁止其他操作
        $(this).siblings().hide();
        //$(this).parent(".kzqy_czbut").parent(".movie_box").unbind("hover");
        var dxtm = $(".dxuan").html();
        var duoxtm = $(".duoxuan").html();
        //接受编辑内容的容器
        var dx_rq = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".dx_box");
        var title = dx_rq.attr("data-t");
        //alert(title);
        //题目选项的个数
        var timlrxm = $(this).parent(".kzqy_czbut").parent(".movie_box").children(".wjdc_list").children("li").length;

        //单选题目
        if (title == 0) {
            dx_rq.show().html(dxtm);
            //模具题目选项的个数
            var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();
            //添加选项题目
            // console.log("单选：" + dxtxx_html)
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            var select = czxx.children(".wjdc_list").find(".selectId")
            for (var i_tmxx = 0; i_tmxx < timlrxm - 1; i_tmxx++) {
                // $.each(select, function () {
                selectid = $(select[i_tmxx]).attr("select_data")
                console.log(selectid)
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "<div class='selectId' select_data='" + selectid + "'></div></div>");
                // })
            }

            //赋值文本框
            //题目标题
            var texte_bt_val = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();
            dx_rq.find(".btwen_text").val(texte_bt_val);
            //遍历题目项目的文字
            var bjjs = 0;
            $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function () {
                //可选框框
                var ktksfcz = $(this).find("input").hasClass("wenb_input");
                if (ktksfcz) {
                    var jsxz_kk = $(this).index();
                    dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk - 1).find("label").remove();
                }
                //题目选项
                var texte_val = $(this).find("span").text();
                dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs).find(".input_wenbk").val(texte_val);
                bjjs++

            });
        }
        //多选题目
        if (title == 1) {
            dx_rq.show().html(duoxtm);
            //模具题目选项的个数
            var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();
            //添加选项题目
            for (var i_tmxx = 0; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "<div class='selectId' select_data='" + selectid + "'></div><span id='error'></span></div>");
                //alert(i_tmxx);
            }
            //赋值文本框
            //题目标题
            var texte_bt_val = $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();
            dx_rq.find(".btwen_text").val(texte_bt_val);
            //遍历题目项目的文字
            var bjjs = 0;
            $(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function () {
                //可选框框
                var ktksfcz = $(this).find("input").hasClass("wenb_input");
                if (ktksfcz) {
                    var jsxz_kk = $(this).index();
                    dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk - 1).find("label").remove();
                }
                //题目选项
                var texte_val = $(this).find("span").text();
                dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs).find(".input_wenbk").val(texte_val);
                bjjs++

            });
        }
    });

    //增加选项
    $(".zjxx").live("click", function () {
        var zjxx_html = $(this).prev(".title_itram").children(".kzjxx_iteam").html();
        $(this).prev(".title_itram").append("<div class='kzjxx_iteam'>" + zjxx_html + "</div>");
    });

    //删除一行
    $(".del_xm").live("click", function () {
        //获取编辑题目的个数
        var select = $(this).next(".selectId")
        var selectId = select.attr("select_data");
        console.log("selectId:" + selectId)
        if (selectId == undefined) {
            var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
            if (zuxxs_num > 1) {
                $(this).parent(".kzjxx_iteam").remove();
            } else {
                alert("手下留情");
            }
        } else {
            $.getJSON("selectServlet", "action=deleteSelect&selectId=" + selectId)
            var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
            if (zuxxs_num > 1) {
                $(this).parent(".kzjxx_iteam").remove();
            } else {
                alert("手下留情");
            }
        }
    });
    //取消编辑
    $(".dx_box .qxbj_but").live("click", function () {
        $(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();
        $(".movie_box").css({
            "border": "1px solid #fff"
        });
        $(".kzqy_czbut").remove();
        //
    });
    // body...
    //完成编辑（编辑）
    $(".swcbj_but").live("click", function () {
        //
        var jcxxxx = $(this).parent(".bjqxwc_box").parent(".dx_box"); //编辑题目区
        var querstionType = jcxxxx.attr("data-t"); //获取题目类型
        var text = $(this).parent(".bjqxwc_box").parent(".dx_box").children(".input_wenbk").val();
        var order = $(this).parent(".bjqxwc_box").parent(".dx_box").parent(".movie_box").find(".nmb").text();
        var questionId = $(this).parent(".bjqxwc_box").parent(".dx_box").parent(".movie_box").find(".questionId").attr("question_data");
        // alert(order)
        // alert($(this).parent(".bjqxwc_box").parent(".dx_box").parent("movie_box").find(".nmb"))
        // alert(questionId)
        if (questionId == undefined) {
            questionId = -1
        }
        $.getJSON("questionServlet", "action=editQuestion&questionOrder=" + order + "&questionContent=" + text + "&questionType=" + querstionType + "&questionId=" + questionId, function (data) {
            // console.log(data)
            var jsonMap = JSON.parse(data);
            // console.log(jsonMap)
            if (questionId == jsonMap.questionId)
                return
            else {
                $(this).parent(".bjqxwc_box").parent(".dx_box").parent(".movie_box").append('<div class="questionId" question_data="' + jsonMap.questionId + '"></div>');
            }
        })
        switch (querstionType) {
            case "0": //单选
            case "1": //多选
                //编辑题目选项的个数
                var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length; //编辑选项的 选项个数
                var xmtit_length = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").length - 1; //题目选择的个数

                //赋值文本框
                //题目标题
                var texte_bt_val_bj = jcxxxx.find(".btwen_text").val(); //获取问题题目
                jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(texte_bt_val_bj); //将修改过的问题题目展示

                //删除选项
                for (var toljs = xmtit_length; toljs > 0; toljs--) {
                    jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(toljs).remove();
                }
                //遍历题目项目的文字
                var bjjs_bj = 0;
                jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
                    //题目选项
                    var texte_val_bj = $(this).find(".input_wenbk").val(); //获取填写信息
                    var inputType = 'radio';
                    var selectId = $(this).find(".selectId").attr("select_data");
                    if (selectId == undefined) {
                        selectId = -1
                    }
                    console.log(questionId)
                    $.getJSON("selectServlet", "action=editSelect&selectContent=" + texte_val_bj + "&selectId=" + selectId + "&questionId=" + questionId, function (data) {
                        if (querstionType == "1") {
                            inputType = 'checkbox';
                        }
                        var li = '<li><label><input name="a" type="' + inputType + '" value=""><span>' + texte_val_bj + '</span></label></li>';
                        jcxxxx.parent(".movie_box").children(".wjdc_list").append(li);
                        bjjs_bj++
                    })
                    //jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(bjjs_bj + 1).find("span").text(texte_val_bj);
                });
                break;
        }
        //清除
        $(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();
    });
});
		
		