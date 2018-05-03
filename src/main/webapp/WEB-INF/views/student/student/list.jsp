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
                                        <%--<spring:message code="user.title.list"/>--%>学生管理
                                        <span class=" btn-group pull-right">
                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADD">
                                    <a href="student/student/add.do" class="btn btn-sm btn-primary"><i
                                            class="ace-icon glyphicon glyphicon-plus"></i>
                                        <spring:message code="button.add"/>
                                    </a>
                                </sec:authorize>
                            	</span>
                                    </h3>
                            <form action="/student/student/search.do" method="post">
                                <input type="number" id="studentid" name="studentid" placeholder="输入学号">
                                <input type="text" id="name" name="name" placeholder="输入姓名">
                                <input type="date" id="stime" name="stime" placeholder="输入开始时间">
                                <input type="date" id="etime" name="etime" placeholder="输入结束时间">

                                <button class="btn btn-info" type="submit" id="getSubject1">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    查询
                                </button>

                            </form>
                                    <table id="simple-table" class="table  table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>学号</th>
                                            <th>姓名</th>
                                            <th class="hidden-480">出生日期</th>
                                            <th class="hidden-480">性别</th>
                                            <th class="hidden-480">所在班级</th>
                                            <th class="hidden-480">选修课数目</th>
                                            <th class="hidden-480">平均分</th>
                                            <th class="hidden-480">操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>

                                        <c:forEach items="${students}" var="student">
                                            <tr id="tr-${student.id}">

                                                <td>${student.studentid}</td>
                                                <td><c:out value="${student.name}"></c:out></td>
                                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${student.birthday}"/></td>
                                                <td>${student.sex}</td>
                                                <td>${student.grade.name}</td>
                                                <td>${student.count}</td>
                                                <td>${student.avgscore}</td>
                                                <td>
                                                    <div class="hidden-sm hidden-xs btn-group action-buttons">

                                                        <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_EDIT">

                                                            <a href="student/student/edit.do?id=${student.id}" title="修改信息">
                                                            <span class="green">
																	<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                            </a>
                                                        </sec:authorize>
                                                        <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_DELETE">
                                                            <c:if test="${param.enabled != 'false'}">
                                                                <a class="btn-delete-modal red" data-url="student/student/delete.do"
                                                                   data-title="<spring:message code="确定删除吗？"/>"
                                                                   data-id="${student.id}" title="删除">
                                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                </a>
                                                            </c:if>
                                                        </sec:authorize>

                                                        <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_SELECT">
                                                            <a href="student/student/select.do?id=${student.id}" class="tooltip-success" data-rel="tooltip" title="选择班级">
																			<span class="green">
																				<i class="ace-icon fa fa-flag bigger-120"></i>
																			</span>
                                                            </a>
                                                        </sec:authorize>
                                                        <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADDSCORE">
                                                            <a href="/student/student/addscore.do?id=${student.id}" class="tooltip-info" data-rel="tooltip" title="录入分数">
                                                                                <span class="blue">
                                                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                                                </span>
                                                            </a>
                                                        </sec:authorize>
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
