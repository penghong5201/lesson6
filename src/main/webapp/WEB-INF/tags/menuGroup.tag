<%@ tag pageEncoding="UTF-8" description="菜单组，可以支持下拉" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="text" required="true" type="java.lang.String" description="导航菜单组的文字" %>
<%@ attribute name="icon" required="false" type="java.lang.String" description="导航菜单组的图标" %>
<%@ attribute name="styleClass" required="false" type="java.lang.String" description="导航菜单组自定义样式" %>
<%@ attribute name="submenu" fragment="true" %>
<li class="hover ${styleClass}">
    <a class="dropdown-toggle" href="#">
        <c:if test="${not empty icon}">
            <i class="menu-icon ${icon}"></i>
        </c:if>
        <span class="menu-text"> <spring:message code="${text}" text="${text}" /> </span>
        <b class="arrow fa fa-angle-down"></b>
    </a>
    <b class="arrow"></b>
    <ul class="submenu">
        <jsp:doBody />
    </ul>
</li>
