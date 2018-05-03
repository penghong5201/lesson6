<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>

<lesson:page title="role.title.${cmd}">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>

            <li>
                <a href="provider/shop/list.do"><spring:message code="role.title.list"/></a>
            </li>
            <li class="active"><spring:message code="role.title.${cmd}"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="role.title.${cmd}"/>
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/roles.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i><spring:message code="button.back"/></a>
                            </span>
                        </h3>
                        <form action="${pageContext.request.contextPath}/manage/roles/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                            <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                            <input type="hidden" name="id" id="id" value="${role.id}"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="name"> <spring:message code="role.name"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="name" name="name"  value="${role.name}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="nameEn"> <spring:message code="role.nameEn"/></label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="nameEn" name="nameEn" value="${role.nameEn}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right" for="description"> <spring:message code="role.description"/> </label>

                                <div class="col-sm-9">
                                    <textarea type="text" autocomplete="off" id="description" name="description" placeholder="<spring:message code="description"/>" class="col-xs-10 col-sm-5">${role.description}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2  control-label no-padding-right"> <spring:message code="role.resource"/> </label>
                            </div>
                                <div class="form-group">

                                <div class="col-sm-12">
                                    <table border=0 align="center">
                                        <tr>
                                            <c:set var="menuItemIndex" value="0"/>
                                            <c:set var="resourceIndex" value="0"/>
                                            <c:forEach items="${mainmenus}" var="mainMenu" varStatus="status">
                                                <td valign="top" class="no">
                                                    <table border=0 align="center">
                                                        <tr>
                                                            <th><strong><lessonTag:i18nNameTag i18nName="${mainMenu}"/></strong></th>
                                                        </tr>
                                                        <c:forEach items="${mainMenu.menuItems}" var="menuItem"
                                                                   varStatus="menuItemStatus">
                                                            <c:set var="menuItemIndex" value="${menuItemIndex+1}"/>
                                                            <tr>
                                                                <td class="no">
                                                                    <table border=0 align="left">
                                                                        <tr>
                                                                            <td class="no" colspan="2" nowrap style="padding: 5px;">
                                                                                <label><input
                                                                                    type="checkbox" name="menuItems"
                                                                                    class="ace"
                                                                                    id="menuItemId_${menuItem.id}"
                                                                                    value="${menuItem.id}"/><span class="lbl"
                                                                                        for="menuItemId_${menuItem.id}"><lessonTag:i18nNameTag i18nName="${menuItem}"/></span></label>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="no" width="30"></td>
                                                                            <td class="no">
                                                                                <c:forEach items="${menuItem.resources}"
                                                                                           var="resource"
                                                                                           varStatus="resourceStatus">
                                                                                    <c:set var="resourceIndex"
                                                                                           value="${resourceIndex+1}"/>
                                                                                    <label>
                                                                                    <input type="checkbox" name="resources"
                                                                                           class="ace ace-checkbox-2"
                                                                                           id="resourceId_${resource.id}"
                                                                                           value="${resource.id}"/><span class="lbl"
                                                                                        for="resourceId_${resource.id}"><lessonTag:i18nNameTag i18nName="${resource}"/></span>
                                                                                    </label><br>
                                                                                </c:forEach>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </div>
                            </div>


                            <div class="clearfix form-actions">
                                <div class="text-center">
                                    <button class="btn btn-info" type="submit">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        <spring:message  code="button.submit"/>
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
    <SCRIPT LANGUAGE="JavaScript">

        <c:forEach items="${role.menuItems}" var="menuItem" varStatus="status">
        var obj${status.count} = document.getElementById('menuItemId_${menuItem.id}');
        if (obj${status.count}) obj${status.count}.checked = true;
        </c:forEach>

        <c:forEach items="${role.resources}" var="resource" varStatus="status">
        var obj${status.count} = document.getElementById('resourceId_${resource.id}');
        if (obj${status.count}) obj${status.count}.checked = true;
        </c:forEach>
    </SCRIPT>
</lesson:page>