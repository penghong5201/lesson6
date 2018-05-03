<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.menus" var="menus"/>
<sec:authentication property="principal" var="userOfLogin"/>
<div id="navbar" class="navbar navbar-default navbar-collapse ace-save-state">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="welcome.do" class="navbar-brand">
                <small>
                    <i class="fa fa-chevron-right"></i>
                    ${empty config.properties['system_title'] ? 'LESSON6' : config.properties['system_title']}
                </small>
            </a>
            <button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
                <span class="sr-only">Toggle user menu</span>

                <img src="static-resource/ace/assets/images/avatars/user.jpg" alt="Jason's Photo">
            </button>
            <button id="menu-toggler" class="pull-right navbar-toggle menu-toggler" type="button" data-toggle="collapse" data-target="#sidebar" aria-expanded="true">
                <span class="sr-only">Toggle sidebar</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-buttons navbar-header pull-right navbar-collapse collapse" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${empty userOfLogin.logo?'static-resource/ace/assets/images/avatars/user.jpg':userOfLogin.logo}" alt="<lesson:i18nNameTag i18nName="${userOfLogin}" />">
								<span class="user-info">
									<small>Welcome,</small>
                                    <lesson:i18nNameTag i18nName="${userOfLogin}" />
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="userInfo/detail.do">
                                <i class="ace-icon fa fa-cog"></i>
                                <spring:message code="common.user.info"/>

                            </a>
                        </li>

                        <li>
                            <a href="password/detail.do">
                                <i class="ace-icon fa fa-user"></i>
                                <spring:message code="common.password"/>
                            </a>
                        </li>
						<c:if test="${userOfLogin.userType=='admin'}">
                        <li>
                            <c:choose>
                                <c:when test="${pageContext.response.locale.toLanguageTag() == 'en'}">
                                    <a href="${originRequest.replaceAll("[\\?\\&]?userLanguage=[^=]*", "")}${originRequest.replaceAll("[\\?\\&]?userLanguage=[^=]*", "").contains("?") ? '&' : '?'}userLanguage=zh_CN"><i class="ace-icon glyphicon glyphicon-font"></i>中文</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${originRequest.replaceAll("[\\?\\&]?userLanguage=[^=]*", "")}${originRequest.replaceAll("[\\?\\&]?userLanguage=[^=]*", "").contains("?") ? '&' : '?'}userLanguage=en"><i class="ace-icon glyphicon glyphicon-font"></i>English</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
						</c:if>
                        <li class="divider"></li>
                        <li>
                            <a href="logout.do">
                                <i class="ace-icon fa fa-power-off"></i>
                                <spring:message code="common.logout" />
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </div><!-- /.navbar-container -->
</div>