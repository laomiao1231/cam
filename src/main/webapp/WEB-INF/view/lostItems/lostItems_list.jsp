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
                <span>查询失物招领信息</span>
                <div class="search">
                    <input type="text" name="key" id="key" placeholder="请输入信息关键字">
                    <button id="submitButton">查询</button>
                </div>
            </div>
            <div class="box cam">
                <table class="table table-bordered tb-gray" id="Information_table">
                    <thead>
                    <tr>
                        <td>失物招领信息描述</td><td>发布时间</td><td>物品状态</td>
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
            url:"<%=request.getContextPath() %>/lostItems/loadAll",
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
        $.each(list,function(index,lostItems){
            var lostItemsDescribeTd=$("<td></td>").append(lostItems.lostItemsDescribe);
            var lostItemsDateTd=$("<td></td>").append(lostItems.lostItemsDate);
            var lostItemsStatusTd=$("<td></td>").append(lostItems.lostItemsStatus==1?'已认领':'未认领');
            $("<tr></tr>").append(lostItemsDescribeTd)
                    .append(lostItemsDateTd)
                    .append(lostItemsStatusTd)
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
            url:"<%=request.getContextPath() %>/lostItems/get/"+Id,
            type:"get",
            success:function(result){
                console.log(result);
                $("#update_describe").val(result.lostItemsDescribe);
                $("#update_date").val(result.lostItemsDate);
                $("#ModalUpdate select").val([result.lostItemsStatus]);
                $("#update_btn").attr("edit-id",result.lostItemsId);
            }
        })
    }
    $("#update_btn").click(function(){
        $.ajax({
            url:"<%=request.getContextPath() %>/lostItems/update/"+$(this).attr("edit-id"),
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
        if(confirm("确认删除此失物招领信息吗？")){
            $.ajax({
                url:"<%=request.getContextPath() %>/lostItems/remove/"+Id,
                type:"get",
                success:function(){
                    to_page(pageNum, pageSize)
                }
            })
        }
    });

    $("#submitButton").click(function() {
        var key = $("#key").val();
        window.location.href="<%=request.getContextPath() %>/guide/toLostItemsKeyList?key="+key;
    });
</script>
</body>
</html>