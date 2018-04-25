<%--
  Created by IntelliJ IDEA.
  User: mxw
  Date: 2018/4/23
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
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
                    <a href="<%=request.getContextPath() %>/guide/toNewsList">查询公告</a>
                </li>
                <li>
                    <a>设备报修</a>
                </li>
            </ul>
        </nav>
        <div class="block-right">
            <h1 class="text-center">欢迎来到</h1>
            <h2 class="text-center">公寓管理系统</h2>
        </div>
    </div>
</body>
</html>