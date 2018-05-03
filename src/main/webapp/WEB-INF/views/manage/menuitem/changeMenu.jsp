<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="manage" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<manage:page title="菜单更改">
    <div class="ui container content">
    <form action="/manage/menuitem/updateItemMenuParentMenu.do" method="post">
        <table class="ui celled table">
            <thead>
            <tr>
                <th> <spring:message code="common.name"/></th>
                <th></th>
                <th>序号</th>
                <th>链接</th>
                <th>需要权限</th>
                <th>备注</th>
                <th>菜单更改</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${mainmenus}" var="mainmenu" varStatus="status">
                <tr class="r${status.count%2}">
                    <td style="font-weight: bolder">${mainmenu.name}</td>
                    <td style="font-weight: bolder">子菜单名称</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <c:forEach items="${mainmenu.menuItems}" var="menuItem" varStatus="status">
                    <tr class="r${status.count%2}">
                        <td></td>
                        <td>${menuItem.name}</td>
                        <td>${menuItem.code}</td>
                        <td>${menuItem.link}</td>
                        <td>${fn:replace(menuItem.symbol,';',';<br>')}</td>
                        <td>${menuItem.description}</td>
                        <td>
                            <select id="menu" name="menu"
                                    onchange="changeItemParentFn(${menuItem.mainMenu.id},this.value,${menuItem.id})">
                                <c:forEach items="${mainmenus}" var="menu">
                                    <option value="${menu.id}"
                                            <c:if test="${menuItem.mainMenu.id == menu.id}">selected="selected"</c:if>>${menu.name}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" value="${menuItem.id}" name="menuItemIds">
                            <input type="hidden" id="menuItem_${menuItem.id}" value=""
                                   name="itemParentIds">
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <input type="submit" class="ui primary button" value="保存"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <script type="text/javascript">
        function changeItemParentFn(menuId, chMenuId, menuItemId) {
            if (menuId != chMenuId) {
                document.getElementById("menuItem_" + menuItemId).value = chMenuId;
            } else {
                document.getElementById("menuItem_" + menuItemId).value = "";
            }
        }
    </script>
</manage:page>
