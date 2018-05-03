<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login success</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath() %>/static/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../common/common_header.jsp"%>
<div class="container">
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="content">
            <div class="box grid-search">
                <h3>关于“${dormitoryDetail.dormitoryBuilding}#${dormitoryDetail.dormitoryCode}”宿舍的住宿人员详情</h3>
            </div>
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>学生姓名</td><td>性别</td><td>专业</td><td>班级</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dormitoryDetail.studentList}" var="stu">
                        <tr>
                            <td>${stu.studentName}</td>
                            <td>${stu.studentSex == 1?'男':'女'}</td>
                            <td>${stu.studentMajor}</td>
                            <td>${stu.studentClass}</td>
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
