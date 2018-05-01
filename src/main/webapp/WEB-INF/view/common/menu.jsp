<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="userInfo" value="${sessionScope.user}"/>
<c:if test="${userInfo.power == 2}">
    <nav>
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/guide/toChangePassword">重置密码</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toAdminAdd">系统管理员信息添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toAdminManage">系统管理员信息管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toStudentAdd">学生信息添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toStudentManage">学生信息管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toStaffAdd">宿舍管理员信息添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toStaffManage">宿舍管理员信息管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toNewsManage">公告管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toNewsAdd">公告添加</a>
            </li>
        </ul>
    </nav>
</c:if>

<c:if test="${userInfo.power == 1}">
    <nav>
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/guide/toChangePassword">重置密码</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toVisitorManage">来访人员管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toVisitorAdd">来访人员添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toLostItemsAdd">失物招领信息添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toLostItemsManage">失物招领信息管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toDormitoryAdd">宿舍添加</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toDormitoryManage">宿舍管理</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toDormitoryAllot">宿舍分配</a>
            </li>
        </ul>
    </nav>
</c:if>

<c:if test="${userInfo.power == 0}">
    <nav>
        <ul>
            <li>
                <a href="<%=request.getContextPath()%>/guide/toChangePassword">重置密码</a>
            </li>
            <li>
                <a href="<%=request.getContextPath() %>/guide/toNewsList">查询公告</a>
            </li>
        </ul>
    </nav>
</c:if>
