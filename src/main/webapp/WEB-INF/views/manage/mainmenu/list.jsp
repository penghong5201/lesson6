<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="mainmenu.title.list">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do">
                    <spring:message code="common.homepage"/>
                </a>
            </li>
            <li class="active"><spring:message code="mainmenu.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="mainmenu.title.list"/>
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/mainMenus/add.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon glyphicon glyphicon-plus"></i>
                                    <spring:message code="button.add"/>
                                </a>
                            </span>
                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="mainmenu.index"/></th>
                                <th><spring:message code="mainmenu.name"/></th>
                                <th><spring:message code="mainmenu.nameEn"/></th>
                                <th class="hidden-md hidden-sm hidden-xs"><spring:message code="mainmenu.description"/></th>
                                <th class="center"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${mainmenus}" var="mainmenu">
                                <tr id="tr-${mainmenu.id}">
                                    <td>${mainmenu.code}</td>
                                    <td>${mainmenu.name}</td>
                                    <td>${mainmenu.nameEn}</td>
                                    <td class="hidden-md hidden-sm hidden-xs">${mainmenu.description}</td>
                                    <td class="center">
                                        <div class="hidden-sm hidden-xs btn-group action-buttons">
                                            <a href="manage/mainMenus/detail.do?id=${mainmenu.id}"
                                               class="blue2">
                                                <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                                            </a>
                                            <a href="manage/mainMenus/edit.do?id=${mainmenu.id}"
                                               class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </a>
                                            <a data-id="${mainmenu.id}"
                                               data-url="manage/mainMenus/save_delete.do"
                                               class="red btn-delete-modal">
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
                                                        <a class="green bigger-140 show-details-btn">
                                                            <span class="green"><i class="ace-icon fa fa-angle-double-down"></i></span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="manage/mainMenus/detail.do?id=${mainmenu.id}">
                                                			<span class="blue2"><i class="ace-icon glyphicon glyphicon-list bigger-120"></i></span>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="manage/mainMenus/edit.do?id=${mainmenu.id}">
                                                            <span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                        </a></li>
                                                    <li>
                                                        <a data-id="${mainmenu.id}"
                                                           data-url="manage/mainMenus/save_delete.do" class="red btn-delete-modal">
                                                            <span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="detail-row" id="tr-detail-${mainmenu.id}">
                                    <td colspan="8" id="td-detail-${mainmenu.id}">
                                        <div class="table-detail">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message
                                                            code="mainmenu.description"/></div>
                                                    <div class="profile-info-value">
                                                        <span><c:out value="${mainmenu.description}"/></span>
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
