<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>学生账户</td><td>学生姓名</td><td>性别</td><td>年龄</td><td>专业</td><td>班级</td><td>操作</td>
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
    <%--修改--%>
    <div class="modal fade" id="ModalUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                </div>
                <div class="modal-body">
                    <!-- form 表单 -->
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">学生账户</label>
                            <div class="col-sm-10">
                                <p type="text" name="studentAccount" class="form-control" id="update_account"></p>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">学生姓名</label>
                            <div class="col-sm-10">
                                <input type="text" name="studentName" class="form-control" id="update_name">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="studentSex" id="update_gender1" value="1">男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="studentSex" id="update_gender2" value="0"> 女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">年龄</label>
                            <div class="col-sm-10">
                                <input type="text" name="studentAge" class="form-control" id="update_age">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">专业</label>
                            <div class="col-sm-10">
                                <input type="text" name="studentMajor" class="form-control" id="update_major">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">班级</label>
                            <div class="col-sm-10">
                                <input type="text" name="studentClass" class="form-control" id="update_class">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-gray-small" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-green-small" id="update_btn">修改</button>
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
            url:"<%=request.getContextPath() %>/student/loadAll",
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
        $.each(list,function(index,student){
            var studentAccountTd=$("<td></td>").append(student.studentAccount);
            var studentNameTd=$("<td></td>").append(student.studentName);
            var studentSexTd=$("<td></td>").append(student.studentSex==1?'男':'女');
            var studentAgeTd=$("<td></td>").append(student.studentAge);
            var studentMajorTd=$("<td></td>").append(student.studentMajor);
            var studentClassTd=$("<td></td>").append(student.studentClass);
            var operateTd=$("<td></td>");
            var update=$("<div></div>").addClass("operate edit_btn")
                    .append("<i class='fa fa-edit'></i>");
            update.attr("edit-id", student.studentId);
            var remove=$("<div><div>").addClass("operate delete_btn")
                    .append("<i class='fa fa-trash'></i>");
            remove.attr("del-id", student.studentId);
            update.appendTo(operateTd);
            remove.appendTo(operateTd);
            $("<tr></tr>").append(studentAccountTd)
                    .append(studentNameTd)
                    .append(studentSexTd)
                    .append(studentAgeTd)
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
    //确定修改按钮，给修改按钮加事件
    $(document).on("click",".edit_btn",function(){
        $('#ModalUpdate').modal({
            backdrop:"static"//点击背景不删除
        });
        //发送ajax请求
        getEditData($(this).attr("edit-id"));
    });

    function getEditData(Id){
        $.ajax({
            url:"<%=request.getContextPath() %>/student/get/"+Id,
            type:"get",
            success:function(result) {
                $("#update_account").text(result.studentAccount);
                $("#update_name").val(result.studentName);
                $("#ModalUpdate input[name='studentSex']").val([result.studentSex]);
                $("#update_age").val(result.studentAge);
                $("#update_major").val(result.studentMajor);
                $("#update_class").val(result.studentClass);
                $("#update_btn").attr("edit-id",result.studentId);
            }
        })
    }
    $("#update_btn").click(function(){
        $.ajax({
            url:"<%=request.getContextPath() %>/student/update/"+$(this).attr("edit-id"),
            type:"POST",
            data:$("#ModalUpdate form").serialize(),
            success:function(){
                $("#ModalUpdate").modal("hide");
                to_page(pageNum, pageSize);
            }
        });
    });
    //删除
    $(document).on("click",".delete_btn",function(){
        var Id=$(this).attr("del-id");
        //弹出确认框
        if(confirm("确认删除ci此学生信息吗？")){
            //确认删除，发送ajax请求
            $.ajax({
                url:"<%=request.getContextPath() %>/student/remove/"+Id,
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
