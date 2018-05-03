<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="mainmenu.title.${cmd}">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do">
                    <spring:message code="common.homepage" />
                </a>
            </li>

            <li>
                <a href="manage/mainMenus.do">
                    <spring:message code="mainmenu.title.list" />
                </a>
            </li>
            <li class="active">
                <spring:message code="mainmenu.title.${cmd}" />
            </li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue"><spring:message code="mainmenu.title.detail" />
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="manage/mainMenus.do" class="btn btn-sm btn-primary"><i class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                        </h3>
                        <form action="manage/mainMenus/save_${cmd}.do" method="post" class="form-horizontal" role="form">
                            <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                            <input type="hidden" name="id" id="id" value="${mainmenu.id}"/>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="account">
                                    <spring:message code="mainmenu.name"/>
                                </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" name="name" value="${mainmenu.name}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="nameEn">
                                    <spring:message code="mainmenu.nameEn"/>
                                </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="nameEn" name="nameEn" value="${mainmenu.nameEn}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="icon">
                                    <spring:message code="mainmenu.icon"/>
                                </label>

                                <div class="col-sm-9">
                                    <input type="text" autocomplete="off" id="icon" name="icon" value="${mainmenu.icon}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="orderIndex">
                                    <spring:message code="mainmenu.index"/>
                                </label>

                                <div class="col-sm-9">
                                    <input type="number" step="1" id="orderIndex"  name="code" value="${mainmenu.code}" class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="description">
                                    <spring:message code="mainmenu.description"/>
                                </label>

                                <div class="col-sm-9">
                                    <textarea type="text" autocomplete="off" id="description" name="description"  class="col-xs-10 col-sm-5">${mainmenu.description}</textarea>
                                </div>
                            </div>

                            <sec:authorize ifAnyGranted="OPT_MAINMENU_ADD,OPT_MAINMENU_EDIT">
                                <div class="clearfix form-actions">
                                    <div class="text-center">
                                        <button class="btn btn-info" type="submit">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            <spring:message code="button.submit"/>
                                        </button>
                                    </div>
                                </div>
                            </sec:authorize>

                        </form>
                    </div><!-- /.span -->
                </div><!-- /.row -->

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>