<%--
  Created by IntelliJ IDEA.
  User: mxw
  Date: 2018/4/17
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/form-validation/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/form-validation/dist/css/formValidation.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/form-validation/css/default.css">
    <script src="<%=request.getContextPath() %>/static/jquery/jquery-3.3.1.min.js"></script>
    <style type="text/css">
        .login-bg {
            background-image: url("<%=request.getContextPath() %>/static/form-validation/vendor/image/login-bg.jpg");
            background-repeat: no-repeat;
        }
        .input-center {
            position: relative;
            margin: 0 auto;
            height: 50%;
        }
        .login-top {
            margin-top: 80px;
        }
        .from-layout {
            width:600px;
            margin:0 auto;
            padding: 80px 0;
            background-color: #F0FFFF;
            opacity: .8
        }
    </style>
    <script type="text/javascript">
        $(function() {
            $("#loginButton").click(function () {
                var data = $('#defaultForm').serialize();
                //序列化获得表单数据，结果为：user_id=12&user_name=John&user_age=20
                var submitData = decodeURIComponent(data, true);
                //submitData是解码后的表单数据，结果同上
                $.ajax({
                    url: '<%=request.getContextPath() %>/guide/login',
                    data: submitData,
                    type: "POST",
                    success: function (result) {
                        //请求成功时
                        if(result.status == "00") {
                            window.location.href="<%=request.getContextPath() %>/guide/loggedIn"
                        }else {
                            alert(result.errorMessage);
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
<body class="login-bg">
<div class="containers">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 login-top">
            <form id="defaultForm" class="form-horizontal from-layout">
                <div class="form-group">
                    <div class="input-group col-md-5 input-center">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="account" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group col-md-5 input-center">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" class="form-control" name="passWord" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group col-md-5 input-center">
                        <span class="input-group-addon"><i class="fa fa-id-card"></i></span>
                        <input type="radio" name="Identity" value="student">学生
                        <input type="radio" name="Identity" value="staff">宿管员
                        <input type="radio" name="Identity" value="admin">管理员
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-9 col-lg-offset-5">
                        <button id="loginButton" type="button" class="btn btn-primary">登录</button>
                    </div>
                </div>
            </form>
        </div>
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
                        account: {
                            validators: {
                                notEmpty: {
                                    message: '用户名不能为空'
                                }
                            }
                        },
                        password: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
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
