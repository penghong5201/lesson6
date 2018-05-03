<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="row">
    <div class="col-xs-12">
<div class="col-xs-12">
    <form id="form" class="form-horizontal">
        <div class="space-12"> </div>
        <input hidden type="text" autocomplete="off" id="userId"
               name="userId" value="${userId}" class="col-xs-10 col-sm-5">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="password">
                <spring:message code="common.password.new"/>
            </label>
            <div class="col-sm-9">
                <input type="password" autocomplete="off" id="password"
                       placeholder="<spring:message code="common.password.new"/>"
                       name="pwd" class="col-xs-10 col-sm-5">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right"
                   for="confirmPassword">
                <spring:message code="common.password.repeat"/>
            </label>
            <div class="col-sm-9">
                <input type="password" autocomplete="off" id="confirmPassword"
                       placeholder="<spring:message code="common.password.repeat"/>"
                       name="pwd" class="col-xs-10 col-sm-5">
            </div>
        </div>
        <div id="password-not-match-msg" class="form-group">
            <label class="col-sm-3 control-label no-padding-right"
                   for="confirmPassword">
            </label>
            <div class="col-sm-9">
                <spring:message code="user.password.not.equal"/>
            </div>
        </div>
    </form>
</div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-confirm-reset btn-primary">
        <spring:message code="button.confirm"/>
    </button>
    <button type="button" class="btn btn-cancel-reset btn-default">
        <spring:message code="button.cancel"/>
    </button>

</div>
<script>
    $(".user-reset-pwd-btn").click(function () {
        var $password = $("#password");
        $password.val("");
        $("#confirmPassword").val("");
        $("#id-of-user").val($(this).data("id"));
        $("#name-of-reset-user").html($(this).data("name"));
        $("#password-not-match-msg").hide();
        $("#user-reset-password-modal").modal();
        $password.focus();
    });
    $(".btn-cancel-reset").click(function () {
        $("#form"). closest('.modal').modal("hide");
    });
    $(".btn-confirm-reset").click(function () {

        var userId = $("#userId").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (password && confirmPassword && password == confirmPassword) {
            $.post("manage/user/save_resetPassword.do", {
                "userId": userId,
                "pwd": password
            }, function (result) {
                if (result) {
                    $("#form"). closest('.modal').modal("hide");
                    var passwordNotEqual = '<spring:message code="common.password.success" />';
                    layer.alert(passwordNotEqual);
                }
            });
        } else {
            $("#password-not-match-msg").show();
        }
    });
</script>
