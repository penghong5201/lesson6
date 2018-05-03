<%@ page import="com.biz.lesson.web.servlet.HttpServletHelper" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% request.setAttribute("clientIP", HttpServletHelper.getClientIP(request));%>
<lesson:page title="欢迎 使用" hideHeader="true" bodyClasses="login-layout light-login">
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container" style="margin-top: 5%">
                        <div class="center">
                            <img src="${empty config.properties['system_logo'] ? 'http://static.lesson.cn/lesson.png' : config.properties['system_logo']}" alt="lesson" style="max-width: 200px;">
                        </div>

                        <div class="space-6"></div>

                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <h4 class="header blue lighter bigger">
                                            <i class="ace-icon fa fa-coffee green"></i>
                                            Please Enter Your Information
                                        </h4>

                                        <div class="space-6"></div>

                                        <form action="login/check.do" method="post">
                                            <fieldset>
                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" autocomplete="off" name="j_username" class="form-control"
                                                                   placeholder="Username"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                                </label>

                                                <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" autocomplete="off" name="j_password"
                                                                   class="form-control" placeholder="Password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                                </label>

                                                <c:if test="${userBlocked == true}">
                                                    <input type="text" autocomplete="off" id="j_captcha" name="j_captcha" maxlength="4"/>
                                                    <img id="captcha_img" src="captcha.do"
                                                         onclick="this.src='captcha.do?d='+new Date().getTime()"/>
                                                </c:if>


                                                <div class="space"></div>

                                                <div class="clearfix">
                                                    <label class="inline">
                                                        <span class="lbl"><b class="blue">Your IP:</b> <strong style="border-bottom: 1px solid #ccc;">${clientIP}</strong> </span>
                                                    </label>
                                                    <button id="submit" type="submit"
                                                            class="width-35 pull-right btn btn-sm btn-primary"
                                                            <c:if test="${userBlocked == true}">disabled="disabled"
                                                    </c:if>>
                                                        <i class="ace-icon fa fa-key"></i>
                                                        <span class="bigger-110">
                                                            <spring:message code="button.login" />
                                                        </span>
                                                    </button>


                                                </div>

                                                <div class="space-4"></div>
                                            </fieldset>
                                        </form>
                                        <div class="social-or-login center">
                                            <span class="bigger-110">Compatible with browsers </span>
                                        </div>

                                        <div class="space-6"></div>

                                        <div class="social-login right">

                                            <i class="ace-icon fa fa-chrome fa-2x light-blue"></i>
                                            <i class="ace-icon fa fa-firefox fa-2x light-blue"></i>
                                            <i class="ace-icon fa fa-safari fa-2x light-blue"></i>
                                            <i class="ace-icon fa fa-edge fa-2x light-blue"></i>
                                            <div class="light-red pull-right">Do not use IE.</div>
                                        </div>
                                    </div><!-- /.widget-main -->
                                </div><!-- /.widget-body -->
                            </div><!-- /.login-box -->

                        </div><!-- /.position-relative -->

                    </div>
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.main-content -->
    </div>
    <!-- /.main-container -->
    <script type="text/javascript">

        $('#j_captcha').bind('input propertychange', function () {
            var inputCode = $(this).val().toString();
            if (inputCode.length == 4) {
                var data_url = "captcha/validate.do";
                $.post(data_url, {
                    "inputCode": inputCode
                }, function (result) {
                    if (result) {
                        $('#submit').removeAttr("disabled");
                    } else {
                        $('#captcha_img').attr('src', 'captcha.do?d=' + new Date().getTime());
                        $('#submit').attr("disabled", "disabled");
                    }
                });
            }
        });

    </script>

</lesson:page>