<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/form-validation/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/form-validation/dist/css/formValidation.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/form-validation/css/default.css">
    <script src="<%=request.getContextPath() %>/static/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/style.css"/>
    <link href="<%=request.getContextPath() %>/static/bootstrap/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(function() {
            $("#submitButton").click(function () {
                var data = $('#defaultForm').serialize();
                //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20
                var submitData = decodeURIComponent(data, true);
                //submitData是解码后的表单数据，结果同上
                $.ajax({
                    url: '<%=request.getContextPath() %>/dormitory/save',
                    data: submitData,
                    type: "POST",
                    success: function (result) {
                        //请求成功时
                        if(result.status == "200") {
                            alert("添加成功")
                            window.location.href="<%=request.getContextPath() %>/guide/toDormitoryAdd";
                        }else {
                            alert("添加失败");
                        }
                    },
                    error: function () {
                        //请求失败时
                        alert("未知错误");
                    }
                })
            })
        })
    </script>
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
    <%@include file="../common/menu.jsp"%>
    <div class="block-right">
        <div class="col-lg-12 text-center">
            <h2>宿舍信息添加</h2>
        </div>
        <form class="form-horizontal" id="defaultForm">
            <div class="form-group">
                <label class="col-sm-2 control-label">宿舍编号</label>
                <div class="col-sm-10">
                    <input type="text" name="dormitoryCode" class="form-control" placeholder="请输入宿舍编号">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所属公寓</label>
                <div class="col-sm-10">
                    <input type="text" name="dormitoryBuilding" class="form-control" placeholder="请输入所属公寓">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">宿舍满员数</label>
                <div class="col-sm-10">
                    <input type="text" name="dormitoryFull" class="form-control" placeholder="请输入宿舍满员数">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">宿舍管理员</label>
                <div class="col-sm-10">
                    <input type="text" name="dormitoryStaffId" class="form-control" placeholder="请输入账户">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="reset" class="btn btn-gray-small">取消</button>
                    <button type="submit" class="btn btn-green-small" id="submitButton">发布</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/static/form-validation/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/form-validation/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/form-validation/dist/js/formValidation.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/form-validation/dist/js/framework/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/form-validation/dist/js/language/zh_CN.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#defaultForm')
                .formValidation({
                    message: 'This value is not valid',
                    icon: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        dormitoryCode: {
                            validators: {
                                notEmpty: {
                                    message: '宿舍编号不能为空'
                                }
                            }
                        },
                        dormitoryBuilding: {
                            validators: {
                                notEmpty: {
                                    message: '所属公寓不能为空'
                                }
                            }
                        },
                        dormitoryFull: {
                            validators: {
                                notEmpty: {
                                    message: '宿舍满员数不能为空'
                                },
                                digits: {
                                    message: '值必须是数字'
                                }
                            }
                        },
                        dormitoryStaffId: {
                            validators: {
                                notEmpty: {
                                    message: '宿舍管理员不能为空'
                                }
                            }
                        },
                    }
                })
                .on('success.form.fv', function(e) {
                    e.preventDefault();
                    var $form = $(e.target);
                    var bv = $form.data('formValidation');
                    $.post($form.attr('action'), $form.serialize(), function(result) {
                        console.log(result);
                    }, 'json');
                });
    });
</script>
</body>
</html>
