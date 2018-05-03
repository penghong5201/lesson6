<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="mainmenu.title.detail">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do">
                    <spring:message code="common.homepage" />
                </a>
            </li>

            <li>
                <a href="manage/mainMenus.do">
                    <spring:message code="mainmenu.title.list" />
                </a>
            </li>
            <li class="active">
                <spring:message code="mainmenu.title.detail" />
            </li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name">
                                    <spring:message code="mainmenu.name"/>
                                </div>
                                <div class="profile-info-value">
                                    <span>${mainmenu.name}</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name">
                                    <spring:message code="mainmenu.nameEn"/>
                                </div>

                                <div class="profile-info-value">
                                    <span>${mainmenu.nameEn}</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name">
                                    <spring:message code="mainmenu.description"/>
                                </div>

                                <div class="profile-info-value">
                                    <span>${mainmenu.description}</span>
                                </div>
                            </div>
                        </div>

                        <h3 class="header smaller lighter blue"><spring:message code="menuitem.title.list"/>
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                 <a href="manage/menuItems/add.do?mainMenuId=${mainmenu.id}" class="btn btn-sm btn-primary">
                                        <i class="ace-icon glyphicon glyphicon-plus"></i>
                                        <span class="hidden-320">
                                            <spring:message code="button.add"/>
                                        </span>
                                    </a>
                            </span>
                        </h3>
                        <c:if test="${not empty mainmenu.menuItems}">
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>

                                    <th><spring:message code="menuitem.name"/></th>
                                    <th><spring:message code="menuitem.nameEn"/></th>
                                    <th class="hidden-md hidden-sm hidden-xs"><spring:message code="menuitem.index"/></th>
                                    <th class="hidden-md hidden-sm hidden-xs"><spring:message code="menuitem.link"/></th>
                                    <th class="hidden-md hidden-sm hidden-xs"><spring:message code="menuitem.symbol"/></th>
                                    <th class="hidden-md hidden-sm hidden-xs"><spring:message code="menuitem.description"/></th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${mainmenu.menuItems}" var="menuItem" varStatus="status">
                                    <tr id="tr-${menuItem.id}">

                                        <td>${menuItem.name}</td>
                                        <td>${menuItem.nameEn}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${menuItem.code}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${menuItem.link}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${fn:replace(menuItem.symbol,',',',<br>')}</td>
                                        <td class="hidden-md hidden-sm hidden-xs">${menuItem.description}</td>

                                        <td class="center">
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                <a href="manage/menuItems/detail.do?id=${menuItem.id}" class="blue2">
                                                    <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                                                </a>
                                                <a href="manage/menuItems/edit.do?id=${menuItem.id}" class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>
                                                <a
                                                        data-id="${menuItem.id}"
                                                        data-url="manage/menuItems/save_delete.do"
                                                        class="red btn-delete-modal" >
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
                                                                <i class="ace-icon fa fa-angle-double-down"></i>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="manage/menuItems/detail.do?id=${menuItem.id}">
                                                <span class="blue2">
                                                <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                                                    </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="manage/menuItems/edit.do?id=${menuItem.id}">
                                                            <span class="green"><i
                                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                            </a></li>
                                                        <li>
                                                            <a  data-id="${menuItem.id}"
                                                                data-url="manage/menuItems/save_delete.do"
                                                                class="red btn-delete-modal" >
                                                            <span class="red"><i
                                                                    class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="detail-row" id="tr-detail-${menuItem.id}">
                                        <td colspan="8" id="td-detail-${menuItem.id}">
                                            <div class="table-detail">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="menuitem.index"/></div>
                                                        <div class="profile-info-value">
                                                            <span><c:out value="${menuItem.code}"/></span>
                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="menuitem.link"/></div>
                                                        <div class="profile-info-value">
                                                            <span><c:out value="${menuItem.link}"/></span>
                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="menuitem.roles"/></div>
                                                        <div class="profile-info-value">
                                                            <span>${fn:replace(menuItem.symbol,',',',<br>')}</span>
                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="menuitem.description"/></div>
                                                        <div class="profile-info-value">
                                                            <span><c:out value="${menuItem.description}"/></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>