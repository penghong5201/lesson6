<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<lesson:page title="accesslog.title.search">

    <jsp:attribute name="script">
		<script src="static-resource/ace/assets/js/moment.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="static-resource/ace/assets/js/daterangepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-clockpicker.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>

        <script type="text/javascript">
          $(function(){
              $('#dateRangePicker').daterangepicker({
                autoUpdateInput:false,
                "autoApply": false,
                locale: {
                  format: 'YYYY-MM-DD'
                }
              },function(start, end) {
                var startDate = start.format("YYYY-MM-DD");
                var endDate = end.format("YYYY-MM-DD");
                $("#startTime").val(startDate);
                $("#endTime").val(endDate);
                $("#dateRangePicker").val(startDate + " - " + endDate);
              }).on('cancel.daterangepicker', function(ev, picker) {
                $("#startTime").val("");
                $("#endTime").val("");
                $("#dateRangePicker").val("");
              });
          });
        </script>
    </jsp:attribute>

    <jsp:attribute name="css">
        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-datepicker3.min.css"/>
		<link rel="stylesheet" href="static-resource/ace/assets/css/daterangepicker.min.css"/>
        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-clockpicker.min.css"/>
        <style type="text/css">
 		</style>
    </jsp:attribute>

    <jsp:body>
        <c:set var="hasOrders" value="false"/>
        <div class="row">

            <div class="col-xs-12">
                <div class="widget-box  <c:if test="${hasOrders}">collapsed</c:if>" >
                    <div class="widget-header ">
                        <h3 class="widget-title ">
                            <spring:message code="accesslog.title.search"/>
                        </h3>
                        <div class=" widget-toolbar no-border " style="margin-right: 30px">
                            <a  data-action="collapse">
                                <i class="ace-icon fa ${hasOrders ? "fa-chevron-down":"fa-chevron-up"}"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="widget-main">
                                <form id="form" method="get" action="manage/accesslog/search.do" class="form-horizontal" role="form">
                                   
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="accesslog.keywords"/> </label>

                                            <div class="col-sm-6">
                                                <input class=" form-control " value="${vo.keywords}" type="text" autocomplete="off" name="keywords">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="accesslog.timestamp"/> </label>

                                            <div class="col-sm-6">
                                                <div class="row">
                                                    <div class="col-xs-12 col-sm-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="fa fa-calendar bigger-110"></i>
                                                            </span>
                                                            <input class="form-control" type="text" autocomplete="off" id="dateRangePicker" <c:if test="${not empty vo.startTime and not empty vo.endTime}">value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.startTime}"/> - <fmt:formatDate pattern="yyyy-MM-dd" value="${vo.endTime}"/>'</c:if>>
                                                            <input type="hidden" name="startTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.startTime}"/>" id="startTime">
                                                            <input type="hidden" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${vo.endTime}"/>" id="endTime">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="accesslog.user"/> </label>
                                            <div class="col-sm-6">
                                                <lesson:userSelect fieldName="user" withNone="true" selectedUserId="${vo.user}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"><spring:message code="accesslog.uri"/></label>
                                            <div class="col-sm-6">
                                            	<input class="form-control " value="${vo.uri}" type="text" autocomplete="off" name="uri">
                                            </div>
                                        </div>
                                    <div class="col-sm-12">
                                        <div class="clearfix ">
                                            <div class="text-center">
                                                &nbsp; &nbsp; &nbsp;
                                                <button class="btn btn-primary btn-sm" type="submit" name="searchButton">
                                                    <spring:message code="button.search"/>
                                                </button>
                                               
                                            </div>
                                        </div>
                                    </div>
									<div class="col-sm-12 space-10"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="space-2"></div>

                <div class="widget-box   widget-color-blue2 " >
                    <div class="widget-header ">
                        <h3 class="widget-title lighter">

                        </h3>
                        <div class=" widget-toolbar no-border " style="margin-right: 30px">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>

                    <div class="widget-body">
                        <div class="widget-main padding-6 no-padding-left no-padding-right">
                            <div class="ui-widget-content">
                                <div>
                                <table id="simple-table" class="table tables table-nonfluid table-bordered table-hover" >
                                    <thead>
                                    <tr>
										<th class="detail-col"></th>                                    
                                        <th><spring:message code="accesslog.user"/></th>
                                        <th><spring:message code="accesslog.timestamp"/></th>
                                        <th><spring:message code="accesslog.uri"/></th>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <c:forEach items="${accesslogs.content}" var="accesslog">
                                        <tr>
										    <td class="center">
										        <c:if test="${not empty accesslog.requestParams}">
										            <div class="action-buttons">
										                <a href="#" class="green bigger-140 show-details-btn">
										                    <i class="ace-icon fa fa-angle-double-down"></i>
										                </a>
										            </div>
										        </c:if>
										    </td>
                                            <td>${accesslog.user}</td>
                                            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${accesslog.timestamp}"/></td>
                                            <td>${accesslog.uri}</td>
                                        </tr>
                                        <tr class="detail-row">
                                        	<td colspan="4"><c:out value="${accesslog.requestParams}"/></td>
                                         </tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                                <lesson:springPagePagination url="manage/accesslog/search.do" defaultPageSize="200" springPage="${accesslogs}"/>
                                
             					</div>
                            </div>

                        </div>

                    </div>
                </div>


            </div>
        </div>
        <!-- /.modal -->

    </jsp:body>
</lesson:page>