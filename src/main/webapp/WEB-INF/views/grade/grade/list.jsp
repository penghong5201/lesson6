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
                                <%--<spring:message code="user.title.list"/>--%>班级管理
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_GRADE_GRADE_ADD">
                                    <a href="grade/grade/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>班级名称</th>
                                    <th class="hidden-480">人数</th>
                                    <th class="hidden-480">平均分</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:forEach items="${grades}" var="grade">
                                    <tr id="tr-${grade.id}">

                                        <td><c:out value="${grade.name}"></c:out></td>
                                        <td>${grade.count}</td>
                                        <td>${grade.avgscore}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">

                                                <sec:authorize ifAnyGranted="OPT_GRADE_GRADE_EDIT">

                                                    <a href="grade/grade/edit.do?id=${grade.id}">
                                                            <span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_GRADE_GRADE_DELETE">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a class="btn-delete-modal red" data-url="grade/grade/delete.do"
                                                           data-title="确定删除吗？"
                                                           data-id="${grade.id}">
                                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                        </a>
                                                    </c:if>
                                                </sec:authorize>

                                                <%--<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																			<span class="green">
																				<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																			</span>--%>
                                                </a>
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
