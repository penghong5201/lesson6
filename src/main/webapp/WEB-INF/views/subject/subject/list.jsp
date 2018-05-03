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
                                <%--<spring:message code="user.title.list"/>--%>学科管理
                                <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_SUBJECT_SUBJECT_ADD">
                                    <a href="subject/subject/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                            </h3>
                            <table id="simple-table" class="table  table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>学科名称</th>
                                    <th class="hidden-480">选修人数</th>
                                    <th class="hidden-480">平均分</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>

                                <tbody>

                                <c:forEach items="${subjects}" var="subject">
                                    <tr id="tr-${subject.id}">

                                        <td><c:out value="${subject.name}"></c:out></td>
                                        <td>${subject.stuSubs.size()}</td>
                                        <td>${subject.avgscore}</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group action-buttons">

                                                <sec:authorize ifAnyGranted="OPT_SUBJECT_SUBJECT_EDIT">

                                                    <a href="subject/subject/edit.do?id=${subject.id}">
                                                            <span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                    </a>
                                                </sec:authorize>
                                                <sec:authorize ifAnyGranted="OPT_SUBJECT_SUBJECT_DELETE">
                                                    <c:if test="${param.enabled != 'false'}">
                                                        <a class="btn-delete-modal red" data-url="subject/subject/delete.do"
                                                           data-title="确定删除吗？"
                                                           data-id="${subject.id}">
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
