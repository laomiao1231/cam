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
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>管理员账户</td><td colspan="2">操作</td>
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
    var pageNum;
    var pageSize = 5;
    $(function(){
        to_page(1, pageSize);
    });
    //ajax 请求函数
    function to_page(pageNumber, pageSize){
        $.ajax({
            url:"<%=request.getContextPath() %>/admin/loadAll",
            data:"pageNumber="+pageNumber+"&pageSize="+pageSize,
            type:"get",
            success:function(pageInfo){
                //信息表
                build_table_Information(pageInfo);
                //页码信息
                build_table_info(pageInfo);
                //页码导航条
                build_table_nav(pageInfo);
            }
        });
    }
    //创建表
    function build_table_Information(pageInfo){
        $("#Information_table tbody").empty();
        var list=pageInfo.list;
        $.each(list,function(index,admin){
            var adminAccountTd=$("<td></td>").append(admin.adminAccount);
            var operateTd=$("<td></td>");
            var changeStatus;
            if(admin.adminStatus == 1){
                changeStatus=$("<div></div>").addClass("operate change_btn")
                        .append("<i class='fa fa-toggle-on'></i>");
            }else {
                changeStatus=$("<div></div>").addClass("operate change_btn")
                        .append("<i class='fa fa fa-toggle-off'></i>");
            }
            changeStatus.attr("change-id", admin.adminId);
            var remove=$("<div><div>").addClass("operate delete_btn")
                    .append("<i class='fa fa-trash'></i>");
            remove.attr("del-id", admin.adminId);
            changeStatus.appendTo(operateTd);
            remove.appendTo(operateTd);
            $("<tr></tr>").append(adminAccountTd)
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
                to_page(pageInfo.pageNum+1, 2);
            });
            lastPageLi.click(function(){
                to_page(pageInfo.pages, 2);
            });
        }

        ul.append(firstPageLi).append(prePageLi);
        $.each(pageInfo.navigatepageNums,function(index,item){
            var numLi=$("<li></li>").append($("<a></a>").append(item));
            if(pageInfo.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item, 2);
            });
            ul.append(numLi);
        });

        ul.append(nextPageLi).append(lastPageLi);
        ul.appendTo("#page_nav");
    }

    //删除
    $(document).on("click",".delete_btn",function(){
        var Id=$(this).attr("del-id");
        //弹出确认框
        if(confirm("确认删除该管理员信息吗？")){
            //确认删除，发送ajax请求
            $.ajax({
                url:"<%=request.getContextPath() %>/admin/remove/"+Id,
                type:"get",
                success:function(){
                    to_page(pageNum, pageSize)
                }
            })
        }
    });

    //更改状态
    $(document).on("click",".change_btn",function(){
        var Id=$(this).attr("change-id");
        //弹出确认框
        if(confirm("确认更改该账户状态吗？")){
            $.ajax({
                url:"<%=request.getContextPath() %>/admin/change/"+Id,
                type:"get",
                success:function(){
                    to_page(pageNum, pageSize)
                }
            })
        }
    });
</script>
</body>
</html>
