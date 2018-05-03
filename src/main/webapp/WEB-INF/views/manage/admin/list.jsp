<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<lesson:page title="user.title.list">
    <jsp:attribute name="css">
        <style type="text/css">
            #name-of-ban-user, #name-of-reset-user {
                font-weight: bold;
                color: red;
            }

            #password-not-match-msg {
                display: none;
                color: #a94442;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script type="application/javascript">



        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>
               <li class="active">
                   <spring:message code="user.title.list"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <input type="hidden" id="id-of-user">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                <spring:message code="user.title.list"/>
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_USER_ADD">
                                    <a href="manage/user/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th><spring:message code="user.userId"/></th>
                                    <th><spring:message code="user.name"/></th>
                                    <th class="hidden-480"><spring:message code="user.nameEn"/></th>
                                    <th class="hidden-480"><spring:message code="user.tel"/>/<spring:message code="user.fax"/>/<spring:message code="user.mobile"/></th>
                                    <th class="hidden-480"><spring:message code="user.roles"/></th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${admins}" var="user">
                                    <tr id="tr-${user.userId}">

                                        <td>${user.userId}</td>
                                        <td>${user.name}</td>
                                        <td class="hidden-480">${user.nameEn}</td>
                                        <td class="hidden-480"> <c:if test="${not empty user.tel}"> T:${user.tel}
                                            <br></c:if>
                                            <c:if test="${not empty user.fax}">F:${user.fax}
                                                <br></c:if>
                                            <c:if test="${not empty user.mobile}">F:${user.mobile}
                                                <br></c:if>
                                        </td>
                                        <td class="hidden-480">${user.roles}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                <sec:authorize ifAnyGranted="OPT_USER_RESET">
                                                    <a data-id="${user.userId}" class="blue2 ajax-contact-btn"
                                                       data-url="manage/user/resetPassword.do?userId=${user.userId}"
                                                       data-title="<spring:message code="common.password"/> <lessonTag:i18nNameTag i18nName="${user}" /> " >
                                                        <i class="ace-icon fa fa-key bigger-120"></i>
                                                    </a>
                                                </sec:authorize>

                                                <sec:authorize ifAnyGranted="OPT_USER_EDIT">
                                                    <a href="manage/user/edit.do?userId=${user.userId}"
                                                       class="green">
                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_USER_DELETE">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a class="btn-delete-modal red" data-url="manage/user/delete.do"
                                                           data-title="<spring:message code="button.disable"/> <lessonTag:i18nNameTag i18nName="${user}" />"
                                                           data-id="${user.userId}">
                                                            <i class="ace-icon fa fa-ban bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </sec:authorize>
                                            </div>
                                            <div class="hidden-md hidden-lg">
                                                <div class="inline pos-rel">
                                                    <button class="blue2 dropdown-toggle"
                                                            data-toggle="dropdown" data-position="auto">
                                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                    </button>

                                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                        <li>
                                                            <a class="green bigger-140 show-details-btn">
                                                                <i class="ace-icon fa fa-angle-double-down"></i>
                                                            </a>
                                                        </li>
                                                        <sec:authorize ifAnyGranted="OPT_USER_RESET">
                                                            <li>
                                                                <a data-id="${user.userId}" class="blu2 ajax-contact-btn"
                                                                   data-url="manage/user/resetPassword.do?userId=${user.userId}"
                                                                   data-title="<spring:message code="common.password"/> <lessonTag:i18nNameTag i18nName="${user}" /> " >
                                                                    <span class="orange">
                                                                    <i class="ace-icon fa fa-key bigger-120"></i>
                                                                        </span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                        <sec:authorize ifAnyGranted="OPT_USER_EDIT">
                                                            <li>
                                                                <a href="manage/user/edit.do?id=${user.userId}">
																<span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                        <sec:authorize ifAnyGranted="OPT_USER_DELETE">
                                                            <li>
                                                                <a class="btn-delete-modal red" data-url="manage/user/delete.do"
                                                                   data-title="<spring:message code="button.disable"/> <lessonTag:i18nNameTag i18nName="${user}" />"
                                                                   data-id="${user.userId}">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
                                                                </a>
                                                            </li>
                                                        </sec:authorize>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="detail-row" id="tr-detail-${user.userId}">
                                        <td colspan="8" id="td-detail-${user.userId}">
                                            <div class="table-detail">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message code="name.en"/></div>
                                                        <div class="profile-info-value"><c:out value="${user.nameEn}"/></div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                            <div class="profile-info-name"><spring:message code="tel"/>/<spring:message code="fax"/>/<spring:message code="mobile"/></div>
                                                            <div class="profile-info-value">
                                                                <c:if test="${not empty user.tel}"> T:${user.tel}
                                                                    <br></c:if>
                                                                <c:if test="${not empty user.fax}">F:${user.fax}
                                                                    <br></c:if>
                                                                <c:if test="${not empty user.mobile}">F:${user.mobile}
                                                                    <br></c:if>
                                                            </div>
                                                        </div>
                                                        <div class="profile-info-row">
                                                            <div class="profile-info-name"><spring:message code="user.roles"/></div>
                                                            <div class="profile-info-value">
                                                                <c:forEach items="${user.roles}" var="role">
                                                                    <lessonTag:i18nNameTag i18nName="${role.name}"/><br>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>
