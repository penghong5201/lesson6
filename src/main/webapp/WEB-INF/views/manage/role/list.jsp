<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>

<sec:authorize access="hasRole('OPT_ROLE_EDIT')"><c:set var="canEdit" value="True"/></sec:authorize>
<sec:authorize access="hasRole('OPT_ROLE_DELETE')"><c:set var="canDelete" value="True"/></sec:authorize>
<lesson:page title="role.title.list">

    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>

            <li class="active"><spring:message code="role.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="role.title.list"/>
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/roles/add.do" class="btn btn-sm btn-primary"><i class="ace-icon glyphicon glyphicon-plus"></i><spring:message code="button.add"/></a>
                            </span>
                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th ><spring:message code="role.name"/></th>
                                <th class="hidden-md hidden-sm hidden-xs"><spring:message code="role.description"/></th>
                                <th class="hidden-480"><spring:message code="role.resource"/></th>
                                <th class="center"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${roles}" var="role">
                                <tr id="tr-${role.id}">

                                    <td><lessonTag:i18nNameTag i18nName="${role}"/></td>
                                    <td class="hidden-md hidden-sm hidden-xs">${role.description}</td>
                                    <td class="hidden-480">
                                        <c:forEach items="${role.menuItems}" var="menuItem">
                                            <lessonTag:i18nNameTag i18nName="${menuItem.mainMenu}"/> &gt; <lessonTag:i18nNameTag i18nName="${menuItem}"/>
                                            <br>
                                        </c:forEach>
                                    </td>
                                    <td class="center">
                                        <div class="hidden-sm hidden-xs btn-group action-buttons">
                                            <a href="manage/roles/edit.do?id=${role.id}" class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </a>
                                            <a data-url="manage/roles/save_delete.do"
                                               data-id="${role.id}" class="btn-delete-modal red">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </a>
                                        </div>
                                        <div class="hidden-md hidden-lg">
                                            <div class="inline pos-rel">
                                                <button class="btn btn-minier btn-primary dropdown-toggle"
                                                        data-toggle="dropdown" data-position="auto">
                                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                </button>
                                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                    <li>
                                                        <a class="green bigger-140 show-details-btn"
                                                          >
                                                            <i class="ace-icon fa fa-angle-double-down"></i>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="manage/roles/edit.do?id=${role.id}">
                                                            <span class="green"><i
                                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                        </a></li>
                                                    <li>
                                                        <a data-url="manage/roles/save_delete.do"
                                                            data-id="${role.id}" class="btn-delete-modal"
                                                        >
                                                            <span class="red"><i
                                                                    class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="detail-row" id="tr-detail-${role.id}">
                                    <td colspan="8" id="td-detail-${role.id}">
                                        <div class="table-detail">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message
                                                            code="role.description"/></div>
                                                    <div class="profile-info-value">
                                                        <span><c:out value="${role.description}"/></span>
                                                    </div>
                                                </div>
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message
                                                            code="role.resource"/></div>
                                                    <div class="profile-info-value">
                                                        <c:forEach items="${role.menuItems}" var="menuItem">
                                                            <lessonTag:i18nNameTag i18nName="${menuItem.mainMenu}"/> &gt; <lessonTag:i18nNameTag i18nName="${menuItem}"/>
                                                            <br>
                                                        </c:forEach>
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

</lesson:page>
