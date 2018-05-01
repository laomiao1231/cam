<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>login success</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
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
            <div class="text-center">
                公寓管理系统
                <span style="float: right"><a href="<%=request.getContextPath() %>/guide/logout" style="font-size: 14px;color: white">退出</a></span>
            </div>
        </div>
    </header>
    <div class="container">
        <%@include file="common/menu.jsp"%>
        <div class="block-right">
            <h1 class="text-center">欢迎来到</h1>
            <h2 class="text-center">公寓管理系统</h2>
        </div>
    </div>
</body>
</html>
