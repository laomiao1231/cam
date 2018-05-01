<%--
  Created by IntelliJ IDEA.
  User: mxw
  Date: 2018/5/1
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
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
<input type="hidden" id="key" value="${key}">
<header id="header">
    <div class="topbar">
        <div class="col-lg-12 text-center">
            公寓管理系统
            <span style="float: right"><a href="<%=request.getContextPath() %>/guide/logout" style="font-size: 14px;color: white">退出</a></span>
        </div>
    </div>
</header>
<div class="container">
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="content">
            <div class="box grid-search">
                <h3>来访者关于“*${key}*”搜索结果</h3>
            </div>
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>来访人姓名</td><td>性别</td><td>所属单位</td><td>时间</td><td>宿管员姓名</td><td>操作</td>
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
                            <label  class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" name="visitorName" class="form-control" id="update_name">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="visitorSex" id="update_gender1" value="1" checked="checked">男
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="visitorSex" id="update_gender2" value="0"> 女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属单位</label>
                            <div class="col-sm-10">
                                <input type="text" name="visitorCompany" class="form-control" id="update_company">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">来访时间</label>
                            <div class="col-sm-10">
                                <input type="text" name="visitorDate" class="form-control" id="update_date">
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
    var keyWord = $("#key").val();
    $(function(){
        to_page(1, pageSize);
        alert("success");
    });
    //ajax 请求函数
    function to_page(pageNumber, pageSize){
        $.ajax({
            url:"<%=request.getContextPath() %>/visitor/getByKey",
            data:"pageNumber="+pageNumber+"&pageSize="+pageSize+"&keyWord="+keyWord,
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
        $.each(list,function(index,visitor){
            var visitorNameTd=$("<td></td>").append(visitor.visitorName);
            var visitorSexTd=$("<td></td>").append(visitor.visitorSex==1?'男':'女');
            var visitorCompanyTd=$("<td></td>").append(visitor.visitorCompany);
            var visitorDateTd=$("<td></td>").append(visitor.visitorDate);
            var staffNameTd=$("<td></td>").append(visitor.staffName);
            var operateTd=$("<td></td>");
            var update=$("<div></div>").addClass("operate edit_btn")
                    .append("<i class='fa fa-edit'></i>");
            update.attr("edit-id", visitor.visitorId);
            var remove=$("<div><div>").addClass("operate delete_btn")
                    .append("<i class='fa fa-trash'></i>");
            remove.attr("del-id", visitor.visitorId);
            update.appendTo(operateTd);
            remove.appendTo(operateTd);
            $("<tr></tr>").append(visitorNameTd)
                    .append(visitorSexTd)
                    .append(visitorCompanyTd)
                    .append(visitorDateTd)
                    .append(staffNameTd)
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
            url:"<%=request.getContextPath() %>/visitor/get/"+Id,
            type:"get",
            success:function(result){
                console.log(result);
                $("#update_name").val(result.visitorName);
                $("#ModalUpdate input[name='visitorSex']").val([result.visitorSex]);
                $("#update_company").val(result.visitorCompany);
                $("#update_date").val(result.visitorDate);
                $("#update_btn").attr("edit-id",result.visitorId);
            }
        })
    }
    $("#update_btn").click(function(){
        $.ajax({
            url:"<%=request.getContextPath() %>/visitor/update/"+$(this).attr("edit-id"),
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
        if(confirm("确认删除来访者信息吗？")){
            //确认删除，发送ajax请求
            $.ajax({
                url:"<%=request.getContextPath() %>/visitor/remove/"+Id,
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
