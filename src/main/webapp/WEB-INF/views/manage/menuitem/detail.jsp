<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="menuitem.title.detail">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>

            <li>
                <a href="manage/mainMenus.do"><spring:message code="mainmenu.title.list"/></a>
            </li>
            <li ><a href="manage/mainMenus/detail.do?id=${menuitem.mainMenu.id}">
                <spring:message code="menuitem.title.list"/>
            </a></li>

            <li class="active"><spring:message code="resource.title.list"/></li>
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
                                <div class="profile-info-name"> <spring:message  code="menuitem.name"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.name}</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name"> <spring:message  code="menuitem.nameEn"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.nameEn}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> <spring:message  code="menuitem.link"/>  </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.link}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name">  <spring:message  code="menuitem.description"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.description}</span>
                                </div>
                            </div>
                        </div>

                        <h3 class="header smaller lighter blue"><spring:message code="resource.title.list"/>

                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/resources/add.do?menuItemId=${menuitem.id}" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-plus"></i><spring:message code="button.add"/></a>
                            </span>
                        </h3>
                        <c:if test="${not empty menuitem.resources}">
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>

                                    <th ><spring:message code="resource.name"/></th>
                                    <th  class="hidden-md hidden-sm hidden-xs"><spring:message code="resource.nameEn"/></th>
                                    <th  class="hidden-md hidden-sm hidden-xs"><spring:message code="resource.symbol"/></th>
                                    <th  class="hidden-md hidden-sm hidden-xs"><spring:message code="resource.description"/> </th>
                                    <th class="center"></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${menuitem.resources}" var="resource" varStatus="status">
                                    <tr id="tr-${resource.id}">

                                        <td>${resource.name}</td>
                                        <td  class="hidden-md hidden-sm hidden-xs">${resource.nameEn}</td>
                                        <td  class="hidden-md hidden-sm hidden-xs">${fn:replace(resource.symbol,';',';<br>')}</td>
                                        <td  class="hidden-md hidden-sm hidden-xs">${resource.description}</td>
                                        <td class="center">
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">
                                                <a href="manage/resources/edit.do?id=${resource.id}" class="green">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>
                                                <a href="manage/resources/delete.do?id=${resource.id}" class="red">
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
                                                            <a href="${pageContext.request.contextPath}/manage/resources/edit.do?id=${resource.id}">
                                                            <span class="green"><i
                                                                    class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                            </a></li>
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/manage/resources/delete.do?id=${resource.id}">
                                                            <span class="red"><i
                                                                    class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr class="detail-row" id="tr-detail-${resource.id}">
                                        <td colspan="8" id="td-detail-${resource.id}">
                                            <div class="table-detail">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="resource.nameEn"/></div>
                                                        <div class="profile-info-value">
                                                            <span><c:out value="${resource.nameEn}"/></span>
                                                        </div>
                                                    </div>
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"><spring:message
                                                                code="resource.description"/></div>
                                                        <div class="profile-info-value">
                                                            <c:out value="${resource.description}"/>
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