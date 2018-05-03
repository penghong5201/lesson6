<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="resource.title.list">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do"><spring:message code="homepage"/> </a>
            </li>
            <li>
                <a href="manage/mainMenus.do">
                    <spring:message code="mainmenu.title.list" />
                </a>
            </li>
            <li>
                <a href="manage/menuItems/detail.do?id=${resource.menuItem.id}"><spring:message code="menuitem.title.list" /></a>
            </li>
            <li class="active"><spring:message code="resource.title.add"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="menuitem.title.add"/>
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/menuItems/detail.do?id=${resource.menuItem.id}" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i>返回</a>
                            </span>
                        </h3>
                        <form action="${pageContext.request.contextPath}/manage/resources/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                            <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                            <input type="hidden" name="id" value="${resource.id}"/>
                            <input type="hidden" name="menuItem.id" value="${resource.menuItem.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="name"> <spring:message code="resource.name"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="name"  name="name" value="${resource.name}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="nameEn">  <spring:message code="resource.nameEn"/> </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="nameEn" name="nameEn" value="${resource.nameEn}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="symbol">  <spring:message code="resource.symbol"/></label>

                                <div class="col-sm-9">
                                    <textarea id="symbol" name="symbol"  class="col-xs-10 col-sm-5">${resource.symbol}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="description">  <spring:message code="resource.description"/></label>

                                <div class="col-sm-9">
                                    <textarea id="description" name="description" class="col-xs-10 col-sm-5">${resource.description}</textarea>
                                </div>
                            </div>


                            <div class="clearfix form-actions">
                                <div class="text-center">
                                    <button class="btn btn-info" type="submit">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        <spring:message code="button.submit"/>
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