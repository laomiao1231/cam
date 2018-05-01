<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/static/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<header id="header">
    <div class="topbar">
        <div class="col-lg-12 text-center">
            公寓管理系统
        </div>
    </div>
</header>
<div class="container">
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="content">
            <div class="box grid-search">
                <span>查询公告</span>
                <div class="search">
                    <input type="text" name="id" placeholder="请输入关键字">
                    <button>查询</button>
                </div>
            </div>
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>学生姓名</td><td>性别</td><td>专业</td><td>班级</td><td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="pull-right" id="page_nav"></div>
                <div class="pull-right">
                    <div class="page-total-info" id="page_info"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var stuId; //分配宿舍时使用
    var pageNum;
    var pageSize = 5;
    $(function(){
        to_page(1, pageSize);
    });
    //ajax 请求函数
    function to_page(pageNumber, pageSize){
        $.ajax({
            url:"<%=request.getContextPath() %>/student/loadNoDorm",
            data:"pageNumber="+pageNumber+"&pageSize="+pageSize,
            type:"get",
            success:function(pageInfo){
                //信息表
                build_table_Information(pageInfo);
                //页码信息
                build_table_info(pageInfo);
                //页码导航条
                build_table_nav(pageInfo);
                build_select();
            }
        });
    }
    //创建表
    function build_table_Information(pageInfo){
        $("#Information_table tbody").empty();
        var list=pageInfo.list;
        $.each(list,function(index,student){
            stuId=student.studentId;
            var studentNameTd=$("<td></td>").append(student.studentName);
            var studentSexTd=$("<td></td>").append(student.studentSex==1?'男':'女');
            var studentMajorTd=$("<td></td>").append(student.studentMajor);
            var studentClassTd=$("<td></td>").append(student.studentClass);
            var operateTd=$("<td></td>");
            var dormList=$("<select id='dormId' class='form-control'></select>");
            dormList.appendTo(operateTd);

            $("<tr></tr>").append(studentNameTd)
                    .append(studentSexTd)
                    .append(studentMajorTd)
                    .append(studentClassTd)
                    .append(operateTd)
                    .appendTo("#Information_table tbody");
        });
    }
    //创建分页信息
    function build_table_info(pageInfo){
        $("#page_info").empty();
        $("#page_info").append("共"+pageInfo.pages+
                "页 "+pageInfo.total+"条记录");
        pageNum=pageInfo.pageNum;
    }
    //创建分业条
    function build_table_nav(pageInfo){
        $("#page_nav").empty();
        var ul=$("<ul></ul>").addClass("pagination");
        var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
        var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;上一页").attr("href","#"));
        if(pageInfo.hasPreviousPage == false){
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        }else{
            firstPageLi.click(function(){
                to_page(1, pageSize);
            });
            prePageLi.click(function(){
                to_page(pageInfo.pageNum-1, pageSize);
            });
        }

        var nextPageLi=$("<li></li>").append($("<a></a>").append("下一页&raquo;").attr("href","#"));
        var lastPageLi=$("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
        if(pageInfo.hasNextPage == false){
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        }else{//添加点击跳转页面事件
            nextPageLi.click(function(){
                to_page(pageInfo.pageNum+1, pageSize);
            });
            lastPageLi.click(function(){
                to_page(pageInfo.pages, pageSize);
            });
        }

        ul.append(firstPageLi).append(prePageLi);
        $.each(pageInfo.navigatepageNums,function(index,item){
            var numLi=$("<li></li>").append($("<a></a>").append(item));
            if(pageInfo.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item, pageSize);
            });
            ul.append(numLi);
        });

        ul.append(nextPageLi).append(lastPageLi);
        ul.appendTo("#page_nav");
    }

    //确定修改按钮，给修改按钮加事件
    function build_select(){
        $.ajax({
            url:"<%=request.getContextPath() %>/dormitory/loadUsableDorm",
            type:"get",
            success:function(result) {
                $("#dormId").empty();
                var optionSelected = $("<option></option>").append("分配宿舍").attr("value", "");
                optionSelected.appendTo("#dormId");
                $.each(result,function(index, dormitory) {
                    var optionEle = $("<option></option>").append(dormitory.dormitoryBuilding + "#" + dormitory.dormitoryCode).attr("value", dormitory.dormitoryId);
                    optionEle.appendTo("#dormId");
                });
            }
        })
    };

    $(document).on("change","#dormId",function(){
        $.ajax({
            url:"<%=request.getContextPath() %>/dormitory/allot",
            data: "stuId="+stuId+"&dormId="+$("#dormId").val(),
            type:"get",
            success:function(result) {
                if(result.status == 200) {
                    to_page(pageNum, pageSize);
                }
            }
        })
    });

    $("#update_btn").click(function(){
        var dormId = $("dormitoryId").val();
        $.ajax({
            url:"<%=request.getContextPath() %>/dormitory/allot",
            type:"POST",
            data:"stuId="+stuId+"&dormId="+dormId,
            success:function(){
                $("#ModalUpdate").modal("hide");
                to_page(pageNum, pageSize);
            }
        });
    });
</script>
</body>
</html>
