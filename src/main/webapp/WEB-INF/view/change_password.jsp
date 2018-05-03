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
                var submitData = $("#password").val();
                $.ajax({
                    url: '<%=request.getContextPath() %>/guide/changePassword',
                    data: "password="+submitData,
                    type: "get",
                    success: function (result) {
                        //请求成功时
                        if(result.status == "00") {
                            alert("修改成功");
                            window.location.href="<%=request.getContextPath() %>/guide/toChangePassword";
                        }else {
                            alert("修改失败");
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
<%@include file="./common/common_header.jsp"%>
<div class="container">
    <%@include file="common/menu.jsp"%>
    <div class="block-right">
        <div class="col-lg-12 text-center">
            <h2>密码重置</h2>
        </div>
        <form class="form-horizontal" id="defaultForm">
            <div class="form-group">
                <label class="col-sm-2 control-label">新密码</label>
                <div class="col-sm-10">
                    <input type="password" name="password1" id="password" class="form-control" placeholder="请输入新密码">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-10">
                    <input type="password" name="password2" class="form-control" placeholder="请输入确认密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="reset" class="btn btn-gray-small">取消</button>
                    <button type="submit" class="btn btn-green-small" id="submitButton">确认</button>
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
                        password1: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                digits: {
                                    message: '必须是数字'
                                },
                                stringLength: {
                                    min: 6,
                                    message:'必须为6位'
                                }
                            }
                        },
                        password2: {
                            validators: {
                                notEmpty: {
                                    message: '确认密码不能为空'
                                },
                                identical: {
                                    field: 'password1',
                                    message: '两次输入的密码不相符'
                                }
                            }
                        }
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
