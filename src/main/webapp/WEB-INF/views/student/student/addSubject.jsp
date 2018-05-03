<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="user.title.${cmd}">
    <jsp:attribute name="script">
        <!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="static-resource/ace/assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="static-resource/ace/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
            if ('ontouchstart' in document.documentElement) document.write("<script src='static-resource/ace/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
		<script src="static-resource/ace/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="static-resource/ace/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
		<script src="static-resource/ace/assets/js/jquery.raty.min.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-multiselect.min.js"></script>
		<script src="static-resource/ace/assets/js/select2.min.js"></script>
		<script src="static-resource/ace/assets/js/jquery-typeahead.js"></script>

		<!-- ace scripts -->
		<script src="static-resource/ace/assets/js/ace-elements.min.js"></script>
		<script src="static-resource/ace/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
            jQuery(function ($) {
                var demo1 = $('select[name="subjectId"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'});
                var container1 = demo1.bootstrapDualListbox('getContainer');
                container1.find('.btn').addClass('btn-white btn-info btn-bold');

                $('.multiselect').multiselect({
                    enableFiltering: true,
                    enableHTML: true,
                    buttonClass: 'btn btn-white btn-primary',
                    templates: {
                        button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
                        ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                        filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
                        filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
                        li: '<li><a tabindex="0"><label></label></a></li>',
                        divider: '<li class="multiselect-item divider"></li>',
                        liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
                    }
                });

            $("#getSubject").click(function () {
                var id = $("#duallist").val();
                alert(id[0]);
            })
            });
        </script>
        		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="static-resource/ace/assets/font-awesome/4.5.0/css/font-awesome.min.css"/>

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-duallistbox.min.css"/>
		<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-multiselect.min.css"/>
		<link rel="stylesheet" href="static-resource/ace/assets/css/select2.min.css"/>

		<!-- text fonts -->
		<link rel="stylesheet" href="static-resource/ace/assets/css/fonts.googleapis.com.css"/>

		<!-- ace styles -->
		<link rel="stylesheet" href="static-resource/ace/assets/css/ace.min.css" class="ace-main-stylesheet"
              id="main-ace-style"/>

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="static-resource/ace/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="static-resource/ace/assets/css/ace-skins.min.css"/>
		<link rel="stylesheet" href="static-resource/ace/assets/css/ace-rtl.min.css"/>

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="static-resource/ace/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="static-resource/ace/assets/js/ace-extra.min.js"></script>

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="static-resource/ace/assets/js/html5shiv.min.js"></script>
		<script src="static-resource/ace/assets/js/respond.min.js"></script>
		<![endif]-->
        <link rel="stylesheet" href="static-resource/ace/assets/css/jquery.gritter.min.css"/>
        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-editable.min.css"/>
        <script src="static-resource/ace/assets/js/jquery.gritter.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
		<script src="static-resource/ace/assets/js/ace-editable.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
        <script src="static-resource/ace/assets/js/bootstrap-tag.min.js"></script>
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

                <li>
                    <a href="manage/users.do">
                        <spring:message code="user.title.list"/>
                    </a>
                </li>
                <li class="active">
                        <%--<spring:message code="user.title.${cmd}"/>--%>
                    选课
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue">
                                    <%--<spring:message code="user.title.${cmd}"/>--%>
                                学生选课
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="javascript:history.go(-1)" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>

                            <form action="student/student/save_${cmd}.do" method="post" class="form-horizontal"
                                  role="form"
                                  id="admin-add-form">
                                <input type="hidden" name="id" id="id" value="${student.id}">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="name">
                                        学生姓名：
                                    </label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="on" id="name" name="name" readonly
                                               value="${student.name}"
                                               class="required form-control"/>
                                    </div>
                                </div>

                                <div class="space-6"></div>
                                <div class="hr hr-16 hr-dotted"></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-top" for="duallist"> Dual
                                        listbox </label>

                                    <div class="col-sm-8">
                                        <select multiple="multiple" size="10" name="subjectId" id="duallist">
                                            <c:forEach items="${subjects}" var="subject">
                                                <c:if test="${!empty newSubject}">

                                                    <option value="${subject.id}" <c:if test="${lessonTag:contains(newSubject, subject)}">selected</c:if>>${subject.name}</option>

                                                </c:if>
                                                <c:if test="${empty newSubject}">
                                                <option value="${subject.id}">${subject.name}</option>
                                                </c:if>
                                                <%--<option value="${subject.id}" <c:if test="${ids.contains(subject.id)}">selected</c:if>>${subject.name}</option>--%>
                                            </c:forEach>
                                        </select>

                                        <div class="hr hr-16 hr-dotted"></div>
                                    </div>
                                </div>
                                <!--提交按钮-->
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">


                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-info" type="submit" id="getSubject1">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            确认选课
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->

                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>

    </jsp:body>
</lesson:page>