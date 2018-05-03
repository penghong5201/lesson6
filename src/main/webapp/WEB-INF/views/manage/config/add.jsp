<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="page-content">

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">

                    <form id="config-form" action="manage/config/save_${cmd}.do" method="post" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="id" id="id" value="${property.id}"/>


                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">
                                <spring:message
                                        code="systemProperty.name"/> </label>

                            <div class="col-sm-6">
                                <input id="name" type="text" autocomplete="off" name="name" maxlength="90" minlength="1" value="${property.name}" class="required form-control">

                            </div>
						 </div>
						 <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"> <spring:message code="systemProperty.value"/> </label>

                            <div class="col-sm-6">
                                <textarea name="value" class="form-control" id="value"><c:out value="${property.value}"/></textarea>
                            </div>
						 </div>
						<div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"> <spring:message
                                    code="systemProperty.description"/> </label>

                            <div class="col-sm-6">
                                <textarea name="description" class="form-control" id="description"><c:out value="${property.description}"/></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name"><spring:message code="systemProperty.editable"/></label>
                            <div class="col-sm-6">
                                <select class="form-control chosen-select" id="status" name="editable" >
                                    <option value="1" <c:if test="${property.editable == true}">selected</c:if>><spring:message code="systemProperty.editable.1" /></option>
                                    <option value="0" <c:if test="${property.editable == false}">selected</c:if>><spring:message code="systemProperty.editable.0" /></option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div><!-- /.span -->
            </div><!-- /.row -->

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div>

<div class="clearfix modal-footer">

    <button id="submit-confirm" class="btn btn-info" type="submit">
        <spring:message code="button.save"/>
    </button>
</div>

<script>
    $(function ($) {

        $("#submit-confirm").click(function () {
            $('#config-form').submit();
        });

    });
</script>

