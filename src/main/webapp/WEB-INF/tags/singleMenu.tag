<%@ tag pageEncoding="UTF-8" description="单一导航，可以点击，没有下拉图标" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="text" required="false" type="java.lang.String" description="导航链接的文字" %>
<%@ attribute name="link" required="true" type="java.lang.String" description="导航的目标链接" %>
<%@ attribute name="icon" required="false" type="java.lang.String" description="导航连接的图标" %>
<%@ attribute name="title" required="false" type="java.lang.String" description="导航连接的图标" %>
<li class="hover" <c:if test="${not empty title}">title="<spring:message code="${title}" text="${title}" />"</c:if>>
    <a href="${link}">
        <c:if test="${not empty icon}">
            <i class="menu-icon ${icon}"></i>
        </c:if>
        <c:if test="${not empty text}">
        <span class="menu-text"> <spring:message code="${text}" text="${text}" /> </span>
        </c:if>
    </a>
</li>