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
<%@include file="../common/common_header.jsp"%>
<div class="container">
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="content">
            <div class="box grid-search">
                <span>查询宿舍</span>
                <div class="search">
                    <input type="text" name="id" placeholder="请输入宿舍编号">
                    <button>查询</button>
                </div>
            </div>
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>宿舍编号</td><td>所属公寓</td><td>满员人数</td><td>已住人数</td><td>宿管员</td><td>操作</td>
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
                            <label  class="col-sm-2 control-label">宿舍编号</label>
                            <div class="col-sm-10">
                                <input type="text" name="dormitoryCode" class="form-control" id="update_code">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">所属公寓</label>
                            <div class="col-sm-10">
                                <input type="text" name="dormitoryBuilding" class="form-control" id="update_building">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">满员人数</label>
                            <div class="col-sm-10">
                                <input type="text" name="dormitoryFull" class="form-control" id="update_full">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label  class="col-sm-2 control-label">管理员</label>
                            <div class="col-sm-10">
                                <input type="text" name="staffName" class="form-control" id="update_name">
                                <span class="help-block"></span>
                            </div>
                        </div>--%>
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
            url:"<%=request.getContextPath() %>/dormitory/loadAll",
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
    //创建员工表
    function build_table_Information(pageInfo){
        $("#Information_table tbody").empty();
        var list=pageInfo.list;
        $.each(list,function(index,dormitory){
            var dormitoryCodeTd=$("<td></td>").append(dormitory.dormitoryCode);
            var dormitoryBuildingTd=$("<td></td>").append(dormitory.dormitoryBuilding);
            var dormitoryFullTd=$("<td></td>").append(dormitory.dormitoryFull);
            var dormitoryPersonnelTd=$("<td></td>").append(dormitory.dormitoryPersonnel);
            var staffNameTd=$("<td></td>").append(dormitory.staff.staffName);
            var operateTd=$("<td></td>");
            var update=$("<div></div>").addClass("operate edit_btn")
                    .append("<i class='fa fa-edit'></i>");
            update.attr("edit-id", dormitory.dormitoryId);
            var remove=$("<div><div>").addClass("operate delete_btn")
                    .append("<i class='fa fa-trash'></i>");
            remove.attr("del-id", dormitory.dormitoryId);
            var detail=$("<div><div>").addClass("operate detail_btn")
                    .append("<i class='fa fa-th-list'></i>");
            detail.attr("detail-id", dormitory.dormitoryId);
            update.appendTo(operateTd);
            remove.appendTo(operateTd);
            detail.appendTo(operateTd);
            $("<tr></tr>").append(dormitoryCodeTd)
                    .append(dormitoryBuildingTd)
                    .append(dormitoryFullTd)
                    .append(dormitoryPersonnelTd)
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
        pageNum = pageInfo.pageNum;
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
                to_page(pageInfo.pageNum+1, pageSize);
            });
            lastPageLi.click(function(){
                to_page(pageInfo.pages, pageSize);
            });
        }

        ul.append(firstPageLi).append(prePageLi);
        $.each(pageInfo.navigatepageNums,function(index,item){
            var numLi=$("<li></li>").append($("<a></a>").append(item));
            if(pageInfo.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                to_page(item, pageSize);
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
            url:"<%=request.getContextPath() %>/dormitory/get/"+Id,
            type:"get",
            success:function(result){
                console.log(result);
                $("#update_code").val(result.dormitoryCode);
                $("#update_building").val(result.dormitoryBuilding);
                $("#update_full").val(result.dormitoryFull);
                $("#update_personnel").val(result.dormitoryPersonnel);
                $("#update_name").val(result.staff.staffName);
                $("#update_btn").attr("edit-id",result.lostItemsId);
            }
        })
    }
    $("#update_btn").click(function(){
        $.ajax({
            url:"<%=request.getContextPath() %>/dormitory/update/"+$(this).attr("edit-id"),
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
        if(confirm("确认删除该宿舍信息吗？")){
            $.ajax({
                url:"<%=request.getContextPath() %>/dormitory/remove/"+Id,
                type:"get",
                success:function(){
                    to_page(pageNum, pageSize)
                }
            })
        }
    });
    //详情
    $(document).on("click",".detail_btn",function(){
        var Id=$(this).attr("detail-id");
        window.location.href="<%=request.getContextPath() %>/dormitory/getPersonnelDetail/"+Id;
    });
</script>
</body>
</html>
