<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<lesson:page title="">

    <jsp:attribute name="script">
			<link rel="stylesheet" href="static-resource/ace/assets/css/jquery.gritter.min.css" />

        <link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-editable.min.css" />




        <script src="static-resource/ace/assets/js/jquery.gritter.min.js"></script>

        <script src="static-resource/ace/assets/js/bootstrap-editable.min.js"></script>
		<script src="static-resource/ace/assets/js/ace-editable.min.js"></script>
        <script src="static-resource/ace/assets/js/jquery.iframe-transport.js"></script>
        <script src="static-resource/ace/assets/js/jquery.fileupload.js"></script>


        <script type="text/javascript">
            jQuery(function ($) {
                $('.date-picker').datepicker({
                            autoclose: true,
                            format: 'yyyy-mm-dd',
                            todayHighlight: true,
                            zIndex: 999
                        })
                        //show datepicker when clicking on the icon
                        .next().on(ace.click_event, function () {
                    $(this).prev().focus();
                });

                editable();

                bindTextChange( $("#emailPassword"));

                bindTextChange( $("#smtpServer"))

            });



            function editable() {
                $.fn.editable.defaults.mode = 'inline';
                $.fn.editableform.loading = "<div class='editableform-loading' ><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
                $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>' +
                    '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';

                // *** editable avatar *** //
                try {//ie8 throws some harmless exceptions, so let's catch'em

                    //first let's add a fake appendChild method for Image element for browsers that have a problem with this
                    //because editable plugin calls appendChild, and it causes errors on IE at unpredicted points
                    try {
                        document.createElement('IMG').appendChild(document.createElement('B'));
                    } catch (e) {
                        Image.prototype.appendChild = function (el) {
                        }
                    }

                    var last_gritter;
                    $('#avatar').editable({
                        type: 'image',
                        name: 'avatar',
                        value: null,
                        //onblur: 'ignore',  //don't reset or hide editable onblur?!
                        image: {
                            //specify ace file input plugin's options here
                            btn_choose: 'Change Avatar',
                            droppable: true,
                            maxSize: 2110000,//~2100Kb
                            //and a few extra ones here
                            name: 'file',//put the field name here as well, will be used inside the custom plugin
                            on_error: function (error_type) {//on_error function will be called when the selected file has a problem
                                if (last_gritter) $.gritter.remove(last_gritter);
                                if (error_type == 1) {//file format error
                                    last_gritter = $.gritter.add({
                                        title: 'File is not an image!',
                                        text: 'Please choose a jpg|gif|png image!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                } else if (error_type == 2) {//file size rror
                                    last_gritter = $.gritter.add({
                                        title: 'File too big!',
                                        text: 'Image size should not exceed 100Kb!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                }
                                else {//other error
                                }
                            },
                            on_success: function () {
                                $.gritter.removeAll();
                            }
                        },

                        url: function (params) {
                            // ***UPDATE AVATAR HERE*** //
                            //for a working upload example you can replace the contents of this function with
                            //examples/profile-avatar-update.js
                            console.log(params);
                            var deferred = new $.Deferred;

                            $("input[name='file']").prop('id', "file");
                            var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();

                            if (!value || value.length == 0) {
                                deferred.resolve();
                                return deferred.promise();
                            }

                            var data = new FormData($(".editableform")[0]);
                            $.ajax({
                                type: 'POST',
                                url: "file/upload.do",
                                dataType: 'json',
                                cache: false,
                                processData: false,    //需要正确设置此项
                                contentType: false,
                                enctype: '/form-data',    //需要正确设置此项
                                data: data,
                                success: function (data) {
                                    $('#logo').val(data.url);
                                    $(".nav-user-photo").attr('src', data.url);
                                    var form = $("#userInfo-edit-form").serialize();
                                    $.post("userInfo/save.do", form, function (data) {

                                    });
                                    if ("FileReader" in window) {
                                        //for browsers that have a thumbnail of selected image
                                        var thumb = $('#avatar').next().find('img').data('thumb');
                                        if (thumb) $('#avatar').get(0).src = thumb;
                                    }

                                    deferred.resolve({'status': 'OK'});

                                    if (last_gritter) $.gritter.remove(last_gritter);
                                    last_gritter = $.gritter.add({
                                        title: '<spring:message code="prompt.update.success"/> ',
                                        class_name: 'gritter-info gritter-left'
                                    });
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    alert('error:');
                                }
                            });

                            //dummy upload
//                        setTimeout(function () {
//                            if ("FileReader" in window) {
//                                //for browsers that have a thumbnail of selected image
//                                var thumb = $('#avatar').next().find('img').data('thumb');
//                                if (thumb) $('#avatar').get(0).src = thumb;
//                            }
//
//                            deferred.resolve({'status': 'OK'});
//
//                            if (last_gritter) $.gritter.remove(last_gritter);
//                            last_gritter = $.gritter.add({
//                                title: 'Avatar Updated!',
//                                text: 'Uploading to server can be easily implemented. A working example is included with the template.',
//                                class_name: 'gritter-info gritter-center'
//                            });
//
//                        }, 500);

                            return deferred.promise();

                            // ***END OF UPDATE AVATAR HERE*** //
                        },

                        success: function (response, newValue) {
                        }

                    });


                } catch (e) {
                }

            }


        </script>
    </jsp:attribute>

    <jsp:body>
        <!-- breadcrumb -->
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do"><spring:message code="common.homepage"/></a>
                </li>
                <li class="active"><spring:message code="common.user.info"/></li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue"><spring:message code="common.user.info"/>
                            </h3>

                            <div class="clearfix"></div>
                            <div  class="user-profile row col-sm-offset-3">
                                <div class="col-xs-12 col-sm-3 center">
                                    <div>
												<span class="profile-picture">
													<img id="avatar" height="180px" width="180px" class="editable img-responsive" alt="Logo"  src="${empty my.logo?'static-resource/ace/assets/images/avatars/profile-pic.jpg':my.logo}" />
												</span>
                                        <div class="space-4"></div>

                                    </div>
                                </div>
                            </div>

                            <form id="userInfo_edit_form" action="userInfo/save.do" method="post"
                                  class="form-horizontal" role="form">

                                <input   type="hidden" id="logo" name="logo" value="${my.logo}">

								　　<input type="text" id="notuse_aaa" style="visibility: hidden;" />   
								　　<input type="password" id="notuse_aab" style="visibility: hidden;" />

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="name"><spring:message code="user.name"/></label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="name" name="name" minlength="1"
                                               maxlength="50" class="required form-control"
                                               value="<c:out value="${my.name}"/>"/>
                                    </div>

                                    <label class="col-md-2 control-label no-padding-right"
                                           for="nameEn"><spring:message code="user.nameEn"/></label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="nameEn" name="nameEn"
                                               maxlength="50" class="required form-control"
                                               value="${my.nameEn}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"><spring:message
                                            code="user.gender"/></label>

                                    <div class="col-sm-3">
                                        <label class="checkbox-inline">
                                        <input type="radio" name="gender" value="M"
                                               <c:if test="${my.gender=='M'}">checked</c:if>/>&nbsp; &nbsp;<spring:message
                                            code="sex.male"/>
                                            </label>
                                        &nbsp; &nbsp; &nbsp;&nbsp;
                                        <label class="checkbox-inline">
                                        <input type="radio" name="gender" value="F"
                                               <c:if test="${my.gender=='F'}">checked</c:if>/>&nbsp; &nbsp;<spring:message
                                            code="sex.female"/>
                                            </label>
                                    </div>

                                    <label class="col-md-2 control-label no-padding-right"><spring:message
                                            code="user.fax"/></label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="fax" name="fax" minlength="1"
                                               maxlength="50" class="form-control"
                                               value="<c:out value="${my.fax}"/>"/>
                                    </div>

                                </div>

                                <div class="form-group">

                                    <label class="col-sm-3 control-label no-padding-right"
                                           for="mobile"><spring:message code="user.mobile"/></label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="mobile" name="mobile"
                                               maxlength="50" class="form-control"
                                               value="${my.mobile}"/>
                                    </div>

                                    <label class="col-md-2 control-label no-padding-right"><spring:message
                                            code="user.address"/></label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="address" name="address" minlength="1"
                                               maxlength="255" class="form-control"
                                               value="<c:out value="${my.address}"/>"/>
                                    </div>

                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"><spring:message
                                            code="user.email"/></label>

                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="email" name="email" minlength="1"
                                               maxlength="50" class="email form-control"
                                               value="<c:out value="${my.email}"/>"/>
                                    </div>

                                    <label class="col-md-2 control-label no-padding-right"
                                           for="mobile"><spring:message code="user.email.password"/></label>
                                    <div class="col-sm-3">
                                        <input type="text" autocomplete="off" id="emailPassword" name="emailPassword"
                                               maxlength="100" class="form-control"
                                               value="${my.emailPassword}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="nameEn">
                                        <spring:message code="user.smtpServer"/>
                                    </label>
                                    <div class="col-sm-8">
                                        <input type="text" autocomplete="off" id="smtpServer" maxlength="50" name="smtpServer"
                                               value="${my.smtpServer}" class=" form-control"/>
                                    </div>

                                </div>
                                <div class="clearfix form-actions">
                                    <div class="text-center">
                                        <button id="submit_add" class="btn btn-info" type="submit">
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
    </jsp:body>
</lesson:page>
