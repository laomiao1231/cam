<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>login success</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .text-center {
            text-align: center;
            font-family: 楷体;
        }
    </style>
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
                <span>合作伙伴查询</span>
                <div class="search">
                    <input type="text" name="id" placeholder="请输入ID">
                    <button>查询</button>
                </div>
            </div>
            <div class="box poc">
                <table class="table table-bordered tb-gray">
                    <thead>
                    <tr>
                        <td>编号</td><td>标题</td><td>发布时间</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${newsList }" var="news">
                        <tr>
                            <td>${news.newsId }</td>
                            <td>${news.newsTitle }</td>
                            <td><fmt:formatDate value="${news.newsTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
