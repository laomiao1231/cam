<%--
  Created by IntelliJ IDEA.
  User: mxw
  Date: 2018/4/26
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
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
                    url: '<%=request.getContextPath() %>/student/save',
                    data: submitData,
                    type: "POST",
                    success: function (result) {
                        //请求成功时
                        if(result.status == "200") {
                            alert("添加成功")
                            window.location.href="<%=request.getContextPath() %>/guide/toStudentAdd";
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
            <span style="float: right"><a href="<%=request.getContextPath() %>/guide/logout" style="font-size: 14px;color: white">退出</a></span>
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
        <div class="col-lg-12 text-center">
            <h2>学生信息添加</h2>
        </div>
        <form class="form-horizontal" role="form" id="defaultForm">
            <div class="form-group">
                <label class="col-sm-2 control-label">学生姓名</label>
                <div class="col-sm-10">
                    <input type="text" name="studentName" class="form-control" placeholder="请输入名字">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">性别</label>
                <div class="col-sm-10">
                    <label class="radio-inline col-sm-2">
                        <input type="radio" name="studentSex" value="1">男
                    </label>
                    <label class="radio-inline col-sm-2">
                        <input type="radio" name="studentSex" value="0">女
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">年龄</label>
                <div class="col-sm-10">
                    <input type="text" name="studentAge" class="form-control"
                           placeholder="请输入年龄">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">专业</label>
                <div class="col-sm-10">
                    <input type="text" name="studentMajor" class="form-control"
                           placeholder="请输入专业">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">班级</label>
                <div class="col-sm-10">
                    <input type="text" name="studentClass" class="form-control"
                           placeholder="请输入班级">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">账户状态</label>
                <div class="col-sm-10">
                    <select id="city" name="studentStatus" class="form-control">
                        <option value="1">可用</option>
                        <option value="0">禁用</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="reset" class="btn btn-gray-small">重置</button>
                    <button type="submit" class="btn btn-green-small" id="submitButton">添加</button>
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
                        studentName: {
                            validators: {
                                notEmpty: {
                                    message: '学生姓名不能为空'
                                }
                            }
                        },
                        studentSex: {
                            validators: {
                                notEmpty: {
                                    message: '性别不能为空'
                                }
                            }
                        },
                        studentAge: {
                            validators: {
                                notEmpty: {
                                    message: '年龄不能为空'
                                },
                                digits: {
                                    message: '值必须是数字'
                                },
                            }
                        },
                        studentMajor: {
                            validators: {
                                notEmpty: {
                                    message: '专业不能为空'
                                }
                            }
                        },
                        studentClass: {
                            validators: {
                                notEmpty: {
                                    message: '班级不能为空'
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
