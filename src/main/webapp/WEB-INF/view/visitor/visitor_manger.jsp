<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login success</title>
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
    <nav>
        <ul>
            <li>
                <a>重置密码</a>
            </li>
            <li>
                <a>查询公告</a>
            </li>
            <li>
                <a>设备报修</a>
            </li>
        </ul>
    </nav>
    <div class="block-right">
        <div class="content">
            <div class="box grid-search">
                <span>查询公告</span>
                <div class="search">
                    <input type="text" name="id" placeholder="请输入关键字">
                    <button>查询</button>
                </div>
            </div>
            <div class="box poc">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>来访人姓名</td><td>性别</td><td>所属单位</td><td>时间</td><td>宿管员姓名</td><td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="pull-right" id="page_nav">
                    <div class="page-total-info" id="page_info"></div>
                </div>
            </div>
        </div>
    </div>
</div>
    <script type="text/javascript">
        $(function(){
            to_page(1, 2);
            alert("success");
        });
        //ajax 请求函数
        function to_page(pageNumber, pageSize){
            $.ajax({
                url:"<%=request.getContextPath() %>/news/loadAll",
                data:"pageNumber="+pageNumber+"&pageSize="+pageSize,
                type:"get",
                success:function(pageInfo){
                    //信息表
                    alert("11111111");
                    build_table_Information(pageInfo);
                    //页码信息
                    alert("2222222222");
                    build_table_info(pageInfo);
                    //页码导航条
                    alert("33333333333")
                    build_table_nav(pageInfo);
                }
            });
        }
        //创建员工表
        function build_table_Information(pageInfo){
            $("#Information_table tbody").empty();
            var list=pageInfo.list;
            $.each(list,function(index,visitor){
                var visitorNameTd=$("<td></td>").append(visitor.visitorName);
                var visitorSexTd=$("<td></td>").append(visitor.visitorSex==1?'男':'女');
                var visitorCompanyTd=$("<td></td>").append(visitor.visitorCompany);
                var visitorDateTd=$("<td></td>").append(visitor.visitorDate);
                var staffNameTd=$("<td></td>").append(visitor.staffName);
                $("<tr></tr>").append(visitorNameTd)
                        .append(visitorSexTd)
                        .append(visitorCompanyTd)
                        .append(visitorDateTd)
                        .append(staffNameTd)
                        .appendTo("#Information_table tbody");
            });
        }
        //创建分页信息
        function build_table_info(pageInfo){
            $("#page_info").empty();
            $("#page_info").append("第"+pageInfo.pageNum+
                    "页;共"+pageInfo.pages+
                    "页;共"+pageInfo.total+"条记录");
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
                    to_page(1, 2);
                });
                prePageLi.click(function(){
                    to_page(pageInfo.pageNum-1, 2);
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
    </script>
</body>
</html>
