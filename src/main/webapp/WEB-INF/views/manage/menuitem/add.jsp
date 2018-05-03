<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<lesson:page title="menuitem.title.list">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="common.homepage"/></a>
            </li>
            <li>
                <a href="manage/mainMenus.do"><spring:message code="mainmenu.title.list"/></a>
            </li>
            <li class="active">
                <spring:message code="menuitem.title.${cmd}" />
            </li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <div class="profile-user-info profile-user-info-striped">
                            <div class="profile-info-row">
                                <div class="profile-info-name">  <spring:message code="mainmenu.name"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.mainMenu.name}</span>
                                </div>
                            </div>

                            <div class="profile-info-row">
                                <div class="profile-info-name"> <spring:message  code="mainmenu.nameEn"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.mainMenu.nameEn}</span>
                                </div>
                            </div>
                            <div class="profile-info-row">
                                <div class="profile-info-name"> <spring:message  code="mainmenu.description"/> </div>

                                <div class="profile-info-value">
                                    <span>${menuitem.mainMenu.description}</span>
                                </div>
                            </div>
                        </div>
                        <h3 class="header smaller lighter blue"><spring:message  code="menuitem.title.list"/>

                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/mainMenus/detail.do?id=${menuitem.mainMenu.id}" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i><spring:message code="button.back"/></a>
                            </span>
                        </h3>
                        <form action="${pageContext.request.contextPath}/manage/menuItems/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                            <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                            <input type="hidden" name="id" value="${menuitem.id}"/>
                            <input type="hidden" name="mainMenu.id" value="${menuitem.mainMenu.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="account"> <spring:message  code="menuitem.name"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" name="name" value="${menuitem.name}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="nameEn"><spring:message  code="menuitem.nameEn"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="nameEn" name="nameEn"  value="${menuitem.nameEn}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="icon">
                                    <spring:message code="menuitem.icon"/>
                                </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="icon" name="icon"  value="<c:out value="${menuitem.icon}"/> " class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="code"> <spring:message  code="menuitem.index"/> </label>

                                <div class="col-sm-9">
                                    <input type="number" step="1" id="code"  name="code" value="${menuitem.code}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="link"> <spring:message  code="menuitem.link"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="link" name="link" value="${menuitem.link}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="symbol"> <spring:message  code="menuitem.symbol"/> </label>

                                <div class="col-sm-9">
                                    <textarea id="symbol" name="symbol" placeholder="<spring:message  code="menuitem.roles"/>" class="col-xs-10 col-sm-5">${menuitem.symbol}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="description"><spring:message  code="menuitem.description"/> </label>

                                <div class="col-sm-9">
                                    <textarea id="description" name="description" class="col-xs-10 col-sm-5">${menuitem.description}</textarea>
                                </div>
                            </div>



                            <div class="clearfix form-actions">
                                <div class="center">
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
</lesson:page>