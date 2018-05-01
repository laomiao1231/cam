<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .article-body {
            width: 80%;
            margin: 0 auto;
        }
        .article-content {
            font-size: 14px;
            margin-bottom: 25px;
            text-align: center;
            word-wrap: break-word;
            word-break: normal;
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
<c:set var="newsInfo" value="${requestScope.news}"/>
<div class="container">
    <div class="content">
        <div class="col-lg-12 text-center">
            <h2>${newsInfo.newsTitle}</h2>
            <h5><fmt:formatDate value="${newsInfo.newsTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></h5>
        </div>
    </div>
    <div class="article-content">
        <p class="article-body">${newsInfo.newsContent}</p>
    </div>
</div>
</body>
</html>
