<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="systemConfig.title">
    <!-- /.breadcrumb -->
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li class="active">
                <spring:message code="systemConfig.title"/>
            </li>
        </ul>
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="systemConfig.title"/>
                            <span class="btn-group pull-right">
                                <a class="ajax-contact-btn btn btn-sm btn-primary" data-id=""
                                   data-url="manage/config/add.do"
                                   data-title="<spring:message code="systemConfig.title.add"/>"
                                   data-modal="#modal-table">
                                    <i class="ace-icon glyphicon glyphicon-plus"></i>
                                    <spring:message code="systemConfig.title.add"/>
                                </a>


                            </span>
                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="systemProperty.name"/></th>
                                <th><spring:message code="systemProperty.description"/></th>
                                <th><spring:message code="systemProperty.value"/></th>
                                <th class="center"></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${systemProperties}" var="systemProperty">
                                <tr id="tr-${systemProperty.id}">
                                    <td><c:out value="${systemProperty.name}"/></td>
                                    <td><c:out value="${systemProperty.description}"/></td>
                                    <td><c:out value="${fn:substring(systemProperty.value, 0, 100)}"/></td>
                                    <td class="center">
                                        <c:if test="${systemProperty.editable == true}">
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">

                                                <a class="ajax-contact-btn green"
                                                   data-id="${systemProperty.id}"
                                                   data-url="manage/config/edit.do?id=${systemProperty.id}"
                                                   data-title="<spring:message code="systemConfig.title.edit"/>"
                                                   data-modal="#modal-table">
                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                </a>

                                                <a class="btn-delete-modal red" style="cursor: pointer"
                                                   data-url="manage/config/delete.do"
                                                   data-id="${systemProperty.id}">

                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </a>

                                            </div>
                                        </c:if>

                                        <div class="hidden-md hidden-lg">
                                            <div class="inline pos-rel">
                                                <button class="blue2 dropdown-toggle"
                                                        data-toggle="dropdown" data-position="auto">
                                                    <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                </button>

                                                <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">

                                                    <c:if test="${not empty systemProperty.description}">
                                                        <li>
                                                            <a class="green bigger-140 show-details-btn"
                                                              >
                                                                <i class="ace-icon fa fa-angle-double-down"></i>
                                                            </a>
                                                        </li>
                                                    </c:if>

                                                    <c:if test="${systemProperty.editable == true}">
                                                        <li>
                                                            <a class="ajax-contact-btn" data-id="${systemProperty.id}"
                                                               data-url="manage/config/edit.do?id=${systemProperty.id}"
                                                               data-title="<spring:message code="systemConfig.title.edit"/>"
                                                               data-modal="#modal-table">
                                                                <span class="green">
																    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
															    </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a class="btn-delete-modal"
                                                               data-url="manage/config/delete.do"
                                                               data-id="${systemProperty.id}">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr class="detail-row" id="tr-detail-${systemProperty.id}">
                                    <td colspan="3" id="td-detail-${systemProperty.id}">
                                        <div class="table-detail">
                                            <div class="row"><c:out value="${systemProperty.description}"/></div>
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
