<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<c:set var="studentInfo" value="${requestScope.studentDetail}"/>
<%@include file="../common/common_header.jsp"%>
<div class="container">
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="col-lg-12 text-center">
            <h2>我的个人中心</h2>
        </div>
        <div class="content">
                <table class="table table-bordered tb-gray" >
                    <thead>
                    <tr>
                        <td>姓名</td><td>专业</td><td>班级</td><td>所在宿舍</td><td>宿管员姓名</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${studentInfo.studentName}</td>
                        <td>${studentInfo.studentMajor}</td>
                        <td>${studentInfo.studentClass}</td>
                        <td>${studentInfo.dormitory.dormitoryBuilding}#${studentInfo.dormitory.dormitoryCode}</td>
                        <td>${studentInfo.staff.staffName}</td>
                    </tr>
                    </tbody>
                </table>
        </div>
    </div>
</body>
</html>
