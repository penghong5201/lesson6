<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<lesson:page title="">
    <jsp:attribute name="script">
        <script type="application/javascript">
            $(function () {
              $("body").on("click", "#submit-add", function () {
                var passwordNew = $("#passwordNew").val();
                var passwordRepeat = $("#passwordRepeat").val();
                if(passwordNew == passwordRepeat){
                  var reg = new RegExp(/${passwordRegexp}/);
                  if (reg.test(passwordNew)) {
                    $("#password-edit-form").submit();
                  } else {
                    layer.msg("<spring:message code="common.password.regexp"/>");
                  }
                } else {
                  layer.msg("<spring:message code="common.password.not.match"/>");
                }
              });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <!-- breadcrumb -->
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do"><spring:message code="common.homepage"/></a>
                </li>
                <li class="active"><spring:message code="common.password"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue"><spring:message code="common.password"/>

                            </h3>
                            <form id="password-edit-form" action="password/save.do" method="post"
                                  class="form-horizontal" role="form">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           ><spring:message code="user.userId"/></label>

                                    <label class="col-sm-1 control-label "> <c:out value="${userId}"/> </label>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"><spring:message code="common.password.old"/></label>
                                    <div class="col-sm-3">
                                        <input type="password" autocomplete="off" id="passwordOld" name="passwordOld" class="required form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                            code="common.password.new"/> </label>
                                    <div class="col-sm-3">
                                        <input type="password" autocomplete="off" id="passwordNew" name="passwordNew" class="required form-control" />
                                        <p class="help-block"><spring:message code="common.password.regexp"/></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                            code="common.password.repeat"/> </label>
                                    <div class="col-sm-3">
                                        <input type="password" autocomplete="off" id="passwordRepeat" name="passwordRepeat" class="required form-control"/>
                                    </div>
                                </div>
                                <div class="clearfix form-actions">
                                    <div class="text-center">
                                        <button id="submit-add" class="btn btn-info" type="button">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            <spring:message code="button.submit"/>
                                        </button>

                                    </div>
                                </div>

                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
        </jsp:body>
</lesson:page>]
