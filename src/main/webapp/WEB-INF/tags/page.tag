<!DOCTYPE html>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lesson" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="hideHeader" required="false" type="java.lang.Boolean" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="bodyClasses" required="false" type="java.lang.String" %>
<%@ attribute name="css" fragment="true" %>
<%@ attribute name="script" fragment="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<sec:authentication property="principal" var="userOfLogin"/>
<html>
<%--<html manifest="${contextPath}/manifest.appcache?v=0.0.1">--%>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <base href="${contextPath}/">
    <title><spring:message code="${title}" text="${title}" /> - <spring:message code="common.title" /></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/css/jquery-ui.custom.min.css" />
    <link rel="stylesheet" href="static-resource/ace/assets/css/chosen.min.css" />
    <link rel="stylesheet" href="static-resource/ace/assets/css/daterangepicker.min.css" />
	<link rel="stylesheet" href="static-resource/ace/assets/css/ui.jqgrid.min.css" />
	<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-timepicker.min.css" />
    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/fonts.googleapis.com.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace.min.css"
          class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-part2.min.css"
          class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="static-resource/ace/assets/css/ace-ie.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="static-resource/common/css/layer.css"/>
    <link rel="stylesheet" href="static-resource/common/css/kalendae.css"/>
    <link rel="stylesheet" href="static-resource/common/css/lesson.css"/>

    <script type="application/javascript">
        var Environment = {
            isEn: ${pageContext.response.locale.toLanguageTag() == 'en'}
        };
    </script>
    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="static-resource/ace/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="static-resource/ace/assets/js/html5shiv.min.js"></script>
    <script src="static-resource/ace/assets/js/respond.min.js"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script src="static-resource/ace/assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="static-resource/ace/assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->

    <style type="text/css">
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
        }
        input[type="number"]{
            -moz-appearance: textfield;
        }

        .action-buttons > a {
            cursor: pointer;
        }

        td > a .ajax-contact-btn .ajax-btn .btn-delete-modal .btn-contact{
            cursor: pointer;
        }

        a .ajax-contact-btn .ajax-btn .btn-delete-modal .btn-contact{
            cursor: pointer;
        }

    </style>

    <jsp:invoke fragment="css"/>
</head>
<body class="${empty bodyClasses ? (empty userOfLogin.skin ?'no-skin':userOfLogin.skin): bodyClasses} ">
<c:choose>
    <c:when test="${hideHeader}">
        <jsp:doBody/>
    </c:when>
    <c:otherwise>
        <sec:authentication property="principal.menus" var="menus"/>
        <c:import url="/WEB-INF/views/common/header.jsp"/>
        <div id="main-container" class="main-container ace-save-state">
            <script type="text/javascript">
                try{ace.settings.loadState('main-container')}catch(e){}
            </script>
            <div id="sidebar" class="sidebar responsive ace-save-state">
                <script type="text/javascript">
                    try{ace.settings.loadState('sidebar')}catch(e){}
                </script>
				<c:if test="${userOfLogin.userType=='admin'}">
	                <div class="sidebar-shortcuts" id="sidebar-shortcuts">
	                    <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
	                        <a class="btn btn-success" href="dashboard.do">
	                            <i class="ace-icon fa fa-list"></i>
	                        </a>
	                        <a class="btn btn-info" href="dashboard.do">
	                            <i class="ace-icon fa fa-calendar-check-o"></i>
	                        </a>
	
	                        <a class="btn btn-warning" href="dashboard.do">
	                            <i class="ace-icon fa fa-stack-overflow"></i>
	                        </a>
	
	                        <a class="btn btn-danger" href="dashboard.do">
	                            <i class="ace-icon fa fa-money"></i>
	                        </a>
	                    </div>
	
	                    <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
	                        <span class="btn btn-success"></span>
	
	                        <span class="btn btn-info"></span>
	
	                        <span class="btn btn-warning"></span>
	
	                        <span class="btn btn-danger"></span>
	                    </div>
	                </div><!-- /.sidebar-shortcuts -->

	                <ul class="nav nav-list">
	                	
		                    <li>
		                        <a href="dashboard.do">
		                            <i class="menu-icon fa fa-tachometer"></i>
		                            <span class="menu-text"> Dashboard </span>
		                        </a>
		                        <b class="arrow"></b>
		                    </li>
		                    <c:forEach var="menu" items="${menus}" varStatus="menuStatus">
		                        <li class="${_activeMainMenu_ eq menuStatus.index ? 'active open' : ''}">
		                            <a href="#" class="dropdown-toggle">
		                                <i class="menu-icon ${empty menu.icon ? 'fa fa-list' : menu.icon}"></i>
		                                <span class="menu-text"><lesson:i18nNameTag i18nName="${menu}" /></span>
		                                <b class="arrow fa fa-angle-down"></b>
		                            </a>
		                            <b class="arrow"></b>
		                            <ul class="submenu">
		                                <c:forEach var="child" items="${menu.children}" varStatus="subMenuStatus">
		                                    <li class="${_activeMainMenu_ eq menuStatus.index  and _activeSubMenu_ eq subMenuStatus.index ? 'active' : ''}">
		                                        <a data-main-menu="${menuStatus.index}" data-sub-menu="${subMenuStatus.index}" data-href="${pageContext.request.contextPath}${child.url}" class="navable-a-tag">
		                                            <lesson:i18nNameTag i18nName="${child}" />
		                                        </a>
		                                    </li>
		                                </c:forEach>
		                            </ul>
		                        </li>
		                    </c:forEach>
	                    </ul><!-- /.nav-list -->
                    </c:if>
                    <c:if test="${userOfLogin.userType=='hotel'}">
	                    <ul class="nav nav-list">
		                    <li>
		                        <a href="partner/hotel/welcome.do">
		                            <i class="menu-icon fa fa-tachometer"></i>
		                            <span class="menu-text"> Welcome </span>
		                        </a>
		                        <b class="arrow"></b>
		                    </li>
		                    <li>
		                        <a href="partner/hotel/requirement/list.do" class="arrowed-right">
		                            <span class="menu-text"><spring:message code="welcome.hotel.request"/></span>
		                            <b class="arrow fa fa-angle-right"></b>
		                        </a>
		                        <b class="arrow"></b>
		                    </li>
		                    <li>
		                        <a href="partner/hotel/requirement/arround.do" class="arrowed-right">
		                            <span class="menu-text"><spring:message code="welcome.hotel.group"/></span>
		                            <b class="arrow fa fa-angle-right"></b>
		                        </a>
		                        <b class="arrow"></b>
		                    </li>
		                    <li>
		                        <a href="partner/hotel/specialOffer/list.do" class="arrowed-right">
		                            <span class="menu-text"><spring:message code="welcome.hotel.offer"/></span>
		                            <b class="arrow fa fa-angle-right"></b>
		                        </a>
		                        <b class="arrow"></b>
		                    </li>
						</ul><!-- /.nav-list -->
					</c:if>
                <c:if test="${userOfLogin.userType=='carRenter'}">
                    <ul class="nav nav-list">
                        <li>
                            <a href="partner/coach/welcome.do">
                                <i class="menu-icon fa fa-tachometer"></i>
                                <span class="menu-text"> Welcome </span>
                            </a>
                            <b class="arrow"></b>
                        </li>
                        <li>
                            <a href="partner/coach/list.do?status=1" class="arrowed-right">
                                <span class="menu-text"><spring:message code="welcome.carRenter.request"/></span>
                                <b class="arrow fa fa-angle-right"></b>
                            </a>
                            <b class="arrow"></b>
                        </li>
                        <li>
                            <a href="partner/coach/list.do?status=10" class="arrowed-right">
                                <span class="menu-text"><spring:message code="welcome.carRenter.confirm"/></span>
                                <b class="arrow fa fa-angle-right"></b>
                            </a>
                            <b class="arrow"></b>
                        </li>
                    </ul><!-- /.nav-list -->
                </c:if>
               
					
                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i id="sidebar-toggle-icon" class="ace-save-state ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>
            <div class="main-content lesson-container">
                <script type="text/javascript">
                    try{ace.settings.loadState('main-container')}catch(e){}
                </script>
                <div class="main-content-inner">
                    <div class="page-content">
                        <div class="ace-settings-container" id="ace-settings-container" style="z-index: 99999;">
                            <div class="btn btn-app btn-xs btn-warning ace-settings-btn " data-value="show" id="ace-settings-btn">
                                <i class="ace-icon fa fa-cog bigger-130"></i>
                            </div>

                            <div class="ace-settings-box clearfix" id="ace-settings-box">
                                <div class="pull-left width-50">
                                    <div class="ace-settings-item">
                                        <div class="pull-left">
                                            <select id="skin-colorpicker" class="hide">
                                                <option ${userOfLogin.skin=='no-skin'?'selected':''} data-skin="no-skin" value="#438EB9">#438EB9</option>
                                                <option ${userOfLogin.skin=='skin-1'?'selected':''} data-skin="skin-1" value="#222A2D">#222A2D</option>
                                                <option ${userOfLogin.skin=='skin-2'?'selected':''} data-skin="skin-2" value="#C6487E">#C6487E</option>
                                                <option ${userOfLogin.skin=='skin-3'?'selected':''} data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                                            </select>
                                        </div>
                                        <span>&nbsp; Choose Skin</span>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input ${userOfLogin.sidebar||userOfLogin.navbar?'checked':''} type="checkbox" class="ace ace-checkbox-2 ace-save-state" name="ace-settings-navbar" id="ace-settings-navbar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                                    </div>

                                    <div class="ace-settings-item">
                                        <input ${userOfLogin.sidebar?'checked':''} type="checkbox" class="ace ace-checkbox-2 ace-save-state" name="ace-settings-sidebar" id="ace-settings-sidebar" autocomplete="off">
                                        <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                                    </div>

                                </div><!-- /.pull-left -->


                               
                            </div><!-- /.ace-settings-box -->
                        </div>
                        <jsp:doBody/>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
<!-- basic scripts -->

<script type="text/javascript">
    if ('ontouchstart'
        in document.documentElement) document.write("<script src='static-resource/ace/assets/js/jquery.mobile.custom.min.js'>"
        + "<"
        + "/script>");
</script>
<script src="static-resource/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="static-resource/ace/assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="static-resource/ace/assets/js/jquery-ui.custom.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.easypiechart.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.sparkline.index.min.js"></script>
<%--<script src="static-resource/ace/assets/js/jquery.flot.min.js"></script>--%>
<%--<script src="static-resource/ace/assets/js/jquery.flot.pie.min.js"></script>--%>
<%--<script src="static-resource/ace/assets/js/jquery.flot.resize.min.js"></script>--%>
<script src="static-resource/ace/assets/js/chosen.jquery.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.dataTables.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.dataTables.bootstrap.min.js"></script>
<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
<script src="static-resource/ace/assets/js/moment.min.js"></script>

<!-- ace scripts -->
<script src="static-resource/ace/assets/js/spinbox.min.js"></script>
<script src="static-resource/ace/assets/js/ace-elements.min.js"></script>
<script src="static-resource/ace/assets/js/ace.min.js"></script>

<!-- support for jquery validator -->
<script src="static-resource/ace/assets/js/jquery.validate.min.js"></script>

<!-- support for act beetbox -->
<script src="static-resource/ace/assets/js/bootbox.js"></script>

<script src="static-resource/common/js/layer.js"></script>
<script src="static-resource/common/js/lesson.js"></script>
<script src="msg.do"></script>

<jsp:invoke fragment="script"/>
<script>
    var permissionDenyMsg = '<spring:message code="common.permission.deny" />';
    $("input[name='depIds']").change(function () {
        var form = $('#department').serialize();
        $.post("userInfo/saveCheckDepartment.do", form, function () {

        });
    });

$("#ace-settings-navbar").change(function () {
    var checked = $(this).prop('checked');
    $.post("userInfo/saveCheckNavbar.do", {Navbar:checked}, function () {

    });
});

$("#ace-settings-sidebar").change(function () {
    var checked = $(this).prop('checked');
    $.post("userInfo/saveCheckSidebar.do", {sidebar:checked}, function () {

    });
});

$("#skin-colorpicker").change(function () {
    var val = $(this).find('option:selected').data('skin');
    $.post("userInfo/saveSkin.do", {skin:val}, function () {

    });
});
</script>
</html>
