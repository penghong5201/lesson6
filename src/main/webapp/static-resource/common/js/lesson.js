/**
 * 设置随session过期的cookie
 * @param name
 * @param value
 */
function setSessionCookie(name, value) {
    document.cookie = name + "=" + value + ";path=/";
}

/**
 * 获取cookie
 * @param name
 * @returns {null}
 */
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else {
        return null;
    }
}

/**
 * 删除cookie
 * @param name
 */
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

/**
 * 禁用浏览器返回按钮
 */
function disableHistoryBack() {
  history.pushState(null, null, document.URL);
  window.addEventListener('popstate', function () {
    history.pushState(null, null, document.URL);
  });
}

/**
 * 判断传入的对象是不是一个function
 * @param functionToCheck
 * @returns {*|boolean}
 */
function isFunction(functionToCheck) {
    var getType = {};
    return functionToCheck && getType.toString.call(functionToCheck) === '[object Function]';
}

/**
 * 在控制台输出传入的参数
 * @param targetArguments
 */
function consoleLogArguments(targetArguments){
    if(targetArguments){
        var fixedArguments = targetArguments[1] ? targetArguments : [targetArguments];
        for(var argumentIndex in fixedArguments){
            console.log("Arg[" + argumentIndex + "]:");
            console.log(fixedArguments[argumentIndex]);
        }
        console.log("\n");
    }
}

/**
 *  输入时去掉两边空格
 * @param $selector
 */
function bindTextChange($selector) {
    $selector.bind('input propertychange', function(){
        var result   =   $(this).val().replace(/^\s+|\s+$/g,"");
        $(this).val(result);
    });
}

function bindDeleteItemEvent($parent) {
    if ($parent) {

    } else {
        $parent = $(document.body);
    }
    $parent.find(".btn-delete-modal").on(ace.click_event, function () {
        var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");
        var data_title = $(this).attr("data-title");
        var title = lesson_message.prompt_msg;
        if (data_title) title = data_title;
        bootbox.dialog({
            message: "<span class='bigger-110'>" + title + "</span>", buttons: {
                "cancel": {
                    "label": "<i class='ace-icon fa fa-remove'></i> " + lesson_message.prompt_cancel,
                    "className": "btn-sm btn-gray",
                }, "Confirm": {
                    "label": "<i class='ace-icon fa fa-check'></i> " + lesson_message.prompt_confirm,
                    "className": "btn-sm btn-primary",
                    "callback": function () {
                        $.post(data_url, {
                            "id": data_id
                        }, function (result) {
                            if (result) {
                                $("#tr-" + data_id).remove();
                                $("#tr-detail-" + data_id).remove();
                            }
                        });

                    }
                }
            }
        });
    });
}
function saveContact(e) {
    var data_url = $(e).attr("data-url");

    var title = $(e).attr("data-title");

    if (title == "" || title == undefined) {
        title = $(e).attr("title");
    }

    new AjaxModal("contact-show-modal", {
        url:data_url,
        data: {}
    }, null, {bodyClass:"no-padding"}).title(title).titleAlignCenter().show();

}


function disableContextMenu(select){
    var targets = [];
    $(select).each(function(){
        targets.push(this);
    });
    for(var i in targets){
        targets[i].oncontextmenu=function(){
            return false;
        };
    }
}

function fixToJqueryId(id) {
    if(id){
        return id.startsWith("#") ? id.substring(1) : id;
    } else {
        throw new Error("Incorrect id");
    }
}

function fixToJqueryIdSelector(id) {

    return "#" + fixToJqueryId(id)
}

function AjaxModal(modalId, reqVO, callback, config) {

    if(callback && !isFunction(callback)){
        throw new Error("Missing callback or callback is not a function.")
    }
    var defaultConfig = {
        dialogClass:"modal-lg",//大号:modal-lg,小号:modal-sm
        headerClass:"bg-primary no-padding-top no-padding-bottom",//bg-primary,bg-success,bg-danger,bg-warning,bg-info等
        headerTextClass:"",//text-primary,text-danger,text-info等
        bodyClass:""//no-padding
    };
    var fixedConfig = $.extend({}, defaultConfig, config);


    if ($(fixToJqueryIdSelector(modalId)) || $(fixToJqueryIdSelector(modalId)).size() == 0) {
        //console.log("Append modal[" + modalId + "] to body.");
        $(document.body).append(
            '<div id="' + fixToJqueryId(modalId) + '" class="modal" tabindex="-1"> ' +
                '<div class="modal-dialog ' + fixedConfig.dialogClass + '">' +
                    '<div class="modal-content">' +
                        '<div class="modal-header ' + fixedConfig.headerClass + '">' +
                            '<button style="padding-top: 5px;" type="button" class="close" data-dismiss="modal">×</button>' +
                            '<h4 class="bigger ' + fixedConfig.headerTextClass + '"></h4>' +
                        '</div>' +
                        '<div class="modal-body ' + fixedConfig.bodyClass + '"></div>' +
                    '</div>' +
                '</div>' +
            '</div>'
        );
    }

    this.modal = $(fixToJqueryIdSelector(modalId));
    var $currentModal = this.modal;
    $currentModal.find(".modal-body").html('');
    var currentAjaxModal = this;

    $.ajax({
        url: reqVO.url,
        method: reqVO.method || "GET",
        traditional: reqVO.traditional || false,
        data: reqVO.data || {},
        success: function (responseHtml) {
            if (responseHtml) {
                $currentModal.find(".modal-body").html(responseHtml);
                $currentModal.find('form').each(function(){
                    $(this).validate();
                });
              disableNumberInputMouseWheel($currentModal);
            }
            if(callback){
                callback($currentModal);
            }
        }
    });

    this.show = function(){
        this.modal.modal("show");
        return this;
    };
    this.hide = function(){
        this.modal.modal("hide");
        return this;
    };
    this.title = function(title){
        this.modal.find(".modal-header h4").html(title  || "&nbsp;");
        return this;
    };
    this.titleAlignCenter = function(){
        this.modal.find(".modal-header h4").addClass("text-center")
        return this;
    };
    this.initFooter = function () {
        if(this.modal.find(".modal-content>.modal-footer").size() == 0){
            this.modal.find(".modal-content:first").append('<div class="modal-footer"></div>')
        } else {
            this.modal.find(".modal-content>.modal-footer").empty();
        }
        function callFunctionIfFormIsValid(clickFunction) {
          var $formOfCurrentModal = $currentModal.find('form');
          if ($formOfCurrentModal.size()) {
            var pass = true;
            $formOfCurrentModal.each(function () {
              pass = pass && $(this).valid();
            });
            if (pass) {
              clickFunction();
            } else {
                return false;
            }
          } else {
            clickFunction();
          }
          return true;
        }
        function addBtnToAjaxModalFooter(type, text, clickFunction, autoCloseModal) {
            var btnId = modalId + '_' + type +'_btn_' + new Date().getTime();
            $currentModal.find(".modal-content>.modal-footer").append('<button id="' + btnId +'" type="button" class="btn btn-' + type + '">' + text + '</button>')
            if(autoCloseModal){
                $currentModal.find(fixToJqueryIdSelector(btnId)).click(function () {
                    if(clickFunction){
                      if(callFunctionIfFormIsValid(clickFunction)){
                        currentAjaxModal.hide();
                      }
                    } else {
                      currentAjaxModal.hide();
                    }
                });
            } else {
              if (clickFunction) {
                $currentModal.find(fixToJqueryIdSelector(btnId)).click(function () {
                  callFunctionIfFormIsValid(clickFunction);
                });
              }
            }
            return this;
        }
        this.addPrimaryBtn = function (text, clickFunction, autoCloseModal) {
            addBtnToAjaxModalFooter("primary", text, clickFunction, autoCloseModal);
            return this;
        };
        this.addDangerBtn = function (text, clickFunction, autoCloseModal) {
            addBtnToAjaxModalFooter("danger", text, clickFunction, autoCloseModal);
            return this;
        };
        this.addWarningBtn= function (text, clickFunction, autoCloseModal) {
            addBtnToAjaxModalFooter("warning", text, clickFunction, autoCloseModal);
            return this;
        };
        this.addSuccessBtn= function (text, clickFunction, autoCloseModal) {
            addBtnToAjaxModalFooter("success", text, clickFunction, autoCloseModal);
            return this;
        };
        this.addCancelBtn = function (text, clickFunction) {
            addBtnToAjaxModalFooter("default", text || "Cancel", clickFunction, true);
            return this;
        };
        return this;
    };
    return this;
}

/**
 * 浮云的div容器
 * @param id 当前容器id
 * @param config 配置信息 可配置信息width, height, max-height, z-index, top, left
 * @param callback 容器创建好后执行的回调函数
 * @constructor
 */
function FloatContainer(id, config, callback){

    var fixedCallback = isFunction(config) ? config : isFunction(callback) ? callback : null;
    var fixedId = fixToJqueryId(id || ("fc_" + new Date().getTime())), style="";
    var $floatContainer = $(fixToJqueryIdSelector(fixedId));
    if(!$floatContainer.size()){
        var windowWidth = $(window).width();
        var windowHeight = $(window).height();
        var fixedConfig = (isFunction(config) ? null : config) || {
                left:(windowWidth * 0.7 > 900 || windowWidth * 0.95 > 900 ? ((windowWidth - 900) /2) : windowWidth * 0.025) + "px",
                width:((windowWidth * 0.7 > 900 || windowWidth * 0.95 > 900 ? 900 : windowWidth * 0.95)) + "px",
                height: "auto",
                maxHeight:(windowHeight > 600 ? windowHeight - 200 : windowHeight * 0.9) + "px",
                top: (windowHeight > 600 ? 100 : windowHeight * 0.05) + "px",
                zIndex: 999999
            };
        style += "width:" + fixedConfig.width + ";";
        style += "height:" + fixedConfig.height + ";";
        style += "max-height:" + fixedConfig.maxHeight + ";";
        style += "z-index:" + fixedConfig.zIndex + ";";
        style += "top:" + fixedConfig.top + ";";
        style += "left:" + fixedConfig.left + ";";
        $(document.body).append('<div id="' + fixedId + '" style="' + style + '" class="float-container"><div class="float-container-header table-header"><button  type="button" class="close"><span class="white">×</span></button><div class="float-container-title">&nbsp;</div></div><div class="float-container-content">&nbsp;</div></div>');
        $("#" + fixedId).find("button.close").click(function () {
            $(this).closest(".float-container").slideUp("fast");
        });
        if(fixedCallback){
            fixedCallback(this);
        }
    }

    this.fc = $("#" + fixedId);

    this.show = function(){
        this.fc.slideDown("slow");
        this.fc.draggable();
        $(document).off('focusin.modal');
    };
    this.hide = function(){
        this.fc.slideUp("fast");
    };
    this.title = function(title){
        this.fc.find(".float-container-title").html(title  || "&nbsp;");
    };
    this.content = function(content){
        this.fc.find(".float-container-content").html(content  || "&nbsp;");
    };

}

var _os_callbackOfRestaurantSelected;
/**
 * 餐厅选择器
 * @param citySelect 餐厅所在城市,可以为城市id的jquery选择器，也可以直接为cityId
 * @param mealLevelId 餐标ID
 * @param callback 回调函数，选中的餐厅会作为回调函数参数
 */
function selectRestaurant(citySelect, mealLevelId, callback){

    _os_callbackOfRestaurantSelected = isFunction(citySelect) ? citySelect : isFunction(mealLevelId) ? mealLevelId : callback;
    var city = isFunction(citySelect) ? "" : $(citySelect).val() || citySelect;
    var restaurantFloatContainer = new FloatContainer("#restaurant_selector_container", function(){
        //console.log("Call back for Restaurant Selector");
        var $body = $('body');
        $body.on('click', '#restaurant_selector_container .show-details-btn', function(e) {
            e.preventDefault();
            $(this).closest('tr').next().toggleClass('open');
            $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
        });
        $body.on('click', '#restaurant_selector_container .btn-meal-select', function(e) {
            e.preventDefault();
            var $this = $(this);
            _os_callbackOfRestaurantSelected({
                restaurantId:$this.data("restaurant-id"),
                restaurantName:$this.data("restaurant-name"),
                mealId:$this.data("meal-id"),
                mealName:$this.data("meal-name"),
                mealLevelName:$this.data("meal-level-name"),
                mealLevelId:$this.data("meal-level-id"),
                cuisineName:$this.data("cuisine-name"),
                cuisineId:$this.data("cuisine-id"),
                price:$this.data("price"),
                mealParking:$this.data("restaurant-info")
            });
            restaurantFloatContainer.hide();
        });
        $body.on("keyup", '#restaurantNameForSearch', function() {
            var key = $(this).val();
            $("#restaurant_selector_container tbody tr:not(.detail-row)").each(function(){
                var $this = $(this);
                if($this.html().indexOf(key) == -1){
                    $this.hide().find(".show-details-btn i").removeClass('fa-angle-double-up').addClass('fa-angle-double-down');
                    $this.next().removeClass('open');
                } else {
                    $this.show();
                }
            });
        });
        function searchRestaurantForSelect() {
          $.get("provider/restaurant/select/restaurants.do?city=" + $("#id_restaurant_city").val() + "&mealLevel=" + $("#restaurant_mealLevel").val(), function(tableHtml){
            if(tableHtml){
              $("#restaurant_selector_container .restaurant-table-container").html(tableHtml);
              $("#restaurant-select-table").DataTable({
                "lengthMenu": [[-1], ["所有"]],
                "columnDefs": [{"targets": [2,4], "orderable": false}],
                "order": [[1, "desc"]]
              });
            }
          });
        }
        $body.on("change", '#id_restaurant_city', searchRestaurantForSelect);
        $body.on("change", '#restaurant_mealLevel', searchRestaurantForSelect);
    });
    $.get("provider/restaurant/select.do?city=" + city + (isFunction(mealLevelId) ? "" : "&mealLevel=" + mealLevelId), function (responseHtml) {
        restaurantFloatContainer.title(Environment.isEn ? "Restaurant Select" : "餐厅选择");
        restaurantFloatContainer.content(responseHtml);
        restaurantFloatContainer.show();
    });
}

var _os_callbackOfHotelSelected;
/**
 * 酒店选择器
 * @param citySelect 酒店所在城市,可以为城市id的jquery选择器，也可以直接为cityId
 * @param callback 回调函数，选种的酒店会作为回调函数参数
 */
function selectHotel(citySelect, callback){

    _os_callbackOfHotelSelected = isFunction(citySelect) ? citySelect : callback;
    var city = isFunction(citySelect) ? "" : $(citySelect).val() || citySelect;
    var restaurantSelectContainer = new FloatContainer("#hotel_selector_container", function(restaurantSelectContainer){
        var $body = $('body');
        $body.on('click', '#hotel_selector_container .btn-hotel-select', function(e) {
            e.preventDefault();
            var $this = $(this);
            _os_callbackOfHotelSelected({
                hotelId:$this.data("hotel-id"),
                hotelName:$this.data("hotel-name"),
                hotelLevel:$this.data("hotel-level"),
                hotelParking:$this.data("hotel-info")
            });
            restaurantSelectContainer.hide();
        });
        $body.on("click", '#btn-hotel-search', function() {
            var levels = [];
            $("input[name='levelsOfHotelSelect']:checked").each(function(){
                levels.push($(this).val());
            });
            $.ajax({
                url: "provider/hotel/select/hotels.do",
                traditional: true,
                data: {
                    city: $("#id_hotel_city").val(),
                    distance: $("#distanceOfHotelSelect").val(),
                    levels: levels,
                    name: $("#hotelNameForSearch").val()
                },
                success: function (tableHtml) {
                    if (tableHtml) {
                        $(".hotel-select-table-container").html(tableHtml);
                    }
                }
            });
        });
    });
    $.get("provider/hotel/select.do?city=" + city, function (responseHtml) {
        restaurantSelectContainer.title(Environment.isEn ? "Hotel Select" : "酒店选择");
        restaurantSelectContainer.content(responseHtml);
        restaurantSelectContainer.show();
    });
}


var _os_callbackOfHotelTabSelected;
/**
 * 酒店选择器
 * @param citySelect 酒店所在城市,可以为城市id的jquery选择器，也可以直接为cityId
 * @param callback 回调函数，选种的酒店会作为回调函数参数
 */
function selectHotelTab(param,callback){

    _os_callbackOfHotelTabSelected = callback;
    var hotelSelectContainer = new FloatContainer("#hotel_selector_container", function(hotelSelectContainer){
        var $body = $('body');
        $body.on('click', '#hotel_selector_container .btn-hotel-select', function(e) {
            e.preventDefault();
            var $this = $(this);
            _os_callbackOfHotelTabSelected({
                hotelId:$this.data("hotel-id"),
                hotelName:$this.data("hotel-name"),
                hotelLevel:$this.data("hotel-level"),
                hotelParking:$this.data("hotel-info"),
                hotelAvgPrice:$this.data("hotel-avg-price")
            });
            hotelSelectContainer.hide();
        });
    });
    
    console.log(param);
    
    $.get("provider/hotel/selectTab.do",param, function (responseHtml) {
        hotelSelectContainer.title(Environment.isEn ? "Hotel Select" : "酒店选择");
        hotelSelectContainer.content(responseHtml);
        hotelSelectContainer.show();
    });
}

var _os_callbackOfSceneSelected;
/**
 * 景点选择器
 * @param citySelect 景点所在城市,可以为城市id的jquery选择器，也可以直接为cityId
 * @param callback 回调函数，选种的景点会作为回调函数参数
 */
function selectScene(citySelect, callback){

    _os_callbackOfSceneSelected = isFunction(citySelect) ? citySelect : callback;
    var city = isFunction(citySelect) ? "" : $(citySelect).val() || citySelect;
    var sceneFloatContainer = new FloatContainer("#scene_selector_container", function(){
        console.log("Call back for Scene Selector");
        var $body = $('body');
        $body.on('click', '#scene_selector_container .show-details-btn', function(e) {
            e.preventDefault();
            $(this).closest('tr').next().toggleClass('open');
            $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
        });
        $body.on('click', '#scene_selector_container .btn-ticket-select', function(e) {
            e.preventDefault();
            var $this = $(this);
            _os_callbackOfSceneSelected({
                sceneId:$this.data("scene-id"),
                sceneName:$this.data("scene-name"),
                ticketId:$this.data("ticket-id"),
                ticketName:$this.data("ticket-name"),
                ticketDescription:$this.data("ticket-description"),
                price:$this.data("price")
            });
            sceneFloatContainer.hide();
        });
        $body.on("keyup", '#sceneNameForSearch', function() {
            var key = $(this).val();
            $("#scene_selector_container tbody tr:not(.detail-row)").each(function(){
                var $this = $(this);
                if($this.html().indexOf(key) == -1){
                    $this.hide().find(".show-details-btn i").removeClass('fa-angle-double-up').addClass('fa-angle-double-down');
                    $this.next().removeClass('open');
                } else {
                    $this.show();
                }
            });
        });
        $body.on("change", '#id_scene_city', function() {
            $.get("base/scene/select/scenes.do?city=" + $(this).val(), function(tableHtml){
                if(tableHtml){
                    $("#scene-select-table").replaceWith(tableHtml);
                }
            });
        });
    });
    $.get("base/scene/select.do?city=" + city, function (responseHtml) {
        sceneFloatContainer.title(Environment.isEn ? "Scene Select" : "景点选择");
        sceneFloatContainer.content(responseHtml);
        sceneFloatContainer.show();
    });
}
var _os_callbackOfBaseHotelSelected;
function searchBaseHotel(citySelect, callback){

    _os_callbackOfBaseHotelSelected = isFunction(citySelect) ? citySelect : callback;
    var city = isFunction(citySelect) ? "1" : $(citySelect).val() || citySelect;

    function executeHotelSearch() {
        var levels = [];
        $("input[name='levelsOfHotelSelect']:checked").each(function () {
            levels.push($(this).val());
        });
        $.ajax({
            url: "provider/hotel/search/hotels.do",
            traditional: true,
            data: {
                page: $("#page").val(),
                pageSize: $("#pageSize").val(),
                cityId: $("#id_hotel_city").val(),
                distance: $("#distanceOfHotelSelect").val(),
                levels: levels,
                name: $("#hotelNameForSearch").val()
            },
            success: function (tableHtml) {
                if (tableHtml) {
                    $(".hotel-search-table-container").html(tableHtml);
                }
            }
        });
    }



    var baseHotelSelectContainer = new FloatContainer("#base_hotel_selector_container", function(restaurantSelectContainer){
        var $body = $('body');
        $body.on('click', '#base_hotel_selector_container .btn-hotel-select', function(e) {
            e.preventDefault();
            var $this = $(this);
            _os_callbackOfBaseHotelSelected({
                hotelId:$this.data("hotel-id"),
                hotelName:$this.data("hotel-name"),
                hotelLevel:$this.data("hotel-level")
            });
            restaurantSelectContainer.hide();
        });
        $body.on("click", '#btn-hotel-search', function() {
        	$("#page").val('0');
            executeHotelSearch();
        });

        $body.on("click", 'a.topage', function() {
            var page = $(this).data('page');
            if (!isNaN(page)) {
                $("#page").val(page);
                executeHotelSearch();
            }
        });


    });
    $.get("provider/hotel/search.do?cityId=" + city, function (responseHtml) {
        baseHotelSelectContainer.title(Environment.isEn ? "Hotel Select" : "酒店选择");
        baseHotelSelectContainer.content(responseHtml);
        baseHotelSelectContainer.show();
    });
}

/**
 * 从form中为JqueryGridTable获取可显示数据
 * @param formSelector
 * @returns {{}}
 */
function getJGridDataFromForm(formSelector){

    var result = {};
    var fieldAndValues = $(formSelector).serialize().replace(/\+/g, " ") || "";
    var fieldAndValueArray = fieldAndValues.split("&");
    for(var fieldAndValueIndex in fieldAndValueArray){
        var fieldAndValue = fieldAndValueArray[fieldAndValueIndex] || "";
        var keyAndValue = fieldAndValue.split("=");
        var $targetField = $(formSelector + " *[name='" + keyAndValue[0] + "']");
        if($targetField.prop('nodeName') == 'SELECT'){
            result[keyAndValue[0]] = $targetField.find("option:selected").html();
        } else if ($targetField.prop('nodeName') == 'INPUT' && $targetField.prop('type').toLocaleLowerCase() == 'radio') {
            $targetField.each(function(){
                if(this.checked){
                    result[keyAndValue[0]] = $(this).data("name") || decodeURI(keyAndValue[1]);
                }
            })
        } else {
            result[keyAndValue[0]] = decodeURI(keyAndValue[1]);
        }
    }
    return result;
}

/**
 * 为JqueryGridTable获取对应字段的值
 * @param fieldSelector
 * @returns {*|{}}
 */
function getFormFieldValueForJGrid(fieldSelector){
    var $targetField = $(fieldSelector);
    return $targetField.prop('nodeName') == 'SELECT' ? $targetField.find("option:selected").html() : $targetField.val();
}

/**
 * 交第一个字母转换为小写
 * @param string 原始String，如:CityId
 * @returns {string} 转换后的String, 如:cityId
 */
function firstCharToLowerCase(string){

    return string ? string.substring(0,1).toLowerCase() + string.substring(1) : string;
}

/**
 * 禁用number类型的input鼠标滚动事件
 */
function disableNumberInputMouseWheel(target) {
  var $target = target ? $(target) : $("body");
  $target.find("input[type='number']").each(function () {
    $(this).attr("onmousewheel", "return false;");
    this.addEventListener('DOMMouseScroll', function (event) {
      event = event || window.event;
      event.preventDefault()
    }, false)
  });
}

var AjaxFile = new function(){

  this.upload = function (fileInputId, callback) {
    if (fileInputId) {
        $(fixToJqueryIdSelector(fileInputId)).fileupload({
        url: "file/upload.do",
        dataType: 'json',
        done: callback
      });
    } else {
        throw Error("Missing file input id")
    }

  };
  this.download = function (file) {
    console.log("Download " + file);
    $("#ajaxFileDownloadIframe").remove();
    $("body").append('<iframe style="display: none" id="ajaxFileUploadIframe" src="file/download.do?file=' + file + '" download></iframe>');
  };
};
$(document).ajaxError(function( event, jqxhr, settings, thrownError ) {
    if(jqxhr && jqxhr.status == 403){
        layer.msg(permissionDenyMsg);
    }
});
var lastClickTime, lastKeyPressTime;
$(function () {

  $(document).keyup(function (e) {
    lastKeyPressTime = new Date().getTime();
  });
  $(document).on('click', 'table.ui-jqgrid-btable select', function () {
    lastKeyPressTime = new Date().getTime();
  });
  $('body').on('click', '.btn-hotel-select,.btn-meal-select', function () {
    lastKeyPressTime = new Date().getTime();
  });
    bindDeleteItemEvent();

    $('form').each(function () {
        $(this).validate();
    });
    $('.chosen-select').chosen({allow_single_deselect: true, search_contains:true, disable_search_threshold: 10});
    $('.lesson-radio-toggle').change(function(){
        console.log("toggle work...");
        if(this.checked){
            var $this = $(this);
            var target = $this.data("toggle-target");
            if($this.hasClass("action-show")){
                $(target).show();
            } else if($this.hasClass("action-hide")){
                $(target).hide();
            }
        }
    });

    $('.show-details-btn').on('click', function (e) {
        e.preventDefault();
        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
    });
    $('.navable-a-tag').on('click', function(e){
        e.preventDefault();
        var $this = $(this);
        setSessionCookie("activeMenu", $this.data("main-menu") + "_" + $this.data("sub-menu"));
        window.location.href=$this.data('href');
    });

    $('.ajax-detail-btn').on('click', function (e) {
        e.preventDefault();

        var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        $(this).closest('tr').next().toggleClass('open');
        $(this).find(ace.vars['.icon']).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');

        $("#td-detail-" + data_id).load(data_url, function (responseTxt, statusTxt, xhr) {
            if (statusTxt == "success")

                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
        });
    });
    $("body").on("click", ".ajax-contact-btn", function(e){
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");

        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }

        new AjaxModal("contact-show-modal", {
            url:data_url,
            data: {}
        },null,{bodyClass:"no-padding"}).title(title).titleAlignCenter().show();



    });
    $('.ajax-map-btn').on('click', function (e) {
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");
        var lon_id = $(this).attr("data-lon-id");
        var lat_id = $(this).attr("data-lat-id");
        var address_id = $(this).attr("data-address-id");
        var address = $("#" + address_id).val();
        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }
        if (address) {
            data_url = data_url + "&address=" + encodeURI(address);
        }

        if ($("#google_map_div").length == 0) {
            $(document.body).append('<div id="google_map_div"><div id="modal-table" class="modal fade modal " tabindex="-1" style="display: none;"> <div class="modal-dialog '
                + '-lg" style=""> <div class="modal-content" > <div class="modal-header no-padding center bg-primary"> <div class="table-header"> <button  type="button" class="close" data-dismiss="modal" aria-hidden="true"> <span class="white">×</span> </button> <div class="table-header-title"></div> </div> </div> <div class="modal-body no-padding"> </div> </div> </div> </div> </div>');

            $(".modal-body").load(data_url, function (responseTxt, statusTxt, xhr) {
                if (statusTxt == "success") {
                    $(".table-header-title").html(title)
                    $("#modal-table").modal('show');
                }
                if (statusTxt == "error")
                    alert("Error: " + xhr.status + ": " + xhr.statusText);
            });
        } else {
            lat = $("#" + lat_id).val();
            lon = $("#" + lon_id).val();

            if (lon == '' || lon == undefined) {
                lon = 8.683;
            }
            if (lat == '' || lat == undefined) {
                lat = 50.117;
            }
            $("#longitude1").val(lon);
            $("#latitude1").val(lat);
            $(".table-header-title").html(title)
            $("#address1").val(address)
            $("#modal-table").modal('show');
            latlng = new google.maps.LatLng(lat, lon);
            map.setCenter(latlng)
            marker.setPosition(latlng);
        }

    });



    $('.ajax-meal-btn').on('click', function (e) {
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");

        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }


        new AjaxModal("simple-show-modal", {
            url:data_url,
            data: {}
        }, null, {bodyClass:"no-padding"}).title(title).titleAlignCenter().show();



    });

    $('.ajax-breakfast-btn').on('click', function (e) {
        e.preventDefault();
        //var data_id = $(this).attr("data-id");
        var data_url = $(this).attr("data-url");

        var title = $(this).attr("data-title");

        if (title == "" || title == undefined) {
            title = $(this).attr("title");
        }


        new AjaxModal("simple-show-modal", {
            url:data_url,
            data: {}
        }, null, {bodyClass:"no-padding"}).title(title).titleAlignCenter().show();



    });

    $('.btn-contact').on('click', function (e) {
        e.preventDefault();
        saveContact(this);
    });

    $('.btn-user').on('click', function (e) {
        e.preventDefault();

        saveContact(this);

    });

    $("table thead input.select-all-toggle").click(function(){
        var $this = $(this);
        var $col = $this.parent();
        var checked = this.checked;
        console.log(checked);
        $this.closest("table").find("tbody tr").each(function(){
            $(this).find("td:eq(" + $col.get(0).cellIndex + ") input[type='checkbox']").get(0).checked=checked;
        });
    });


	$('[data-rel=popover]').popover({html:true});

  $("a.history-back").click(function(e){
    e.preventDefault();
    window.history.back();
  });

  disableNumberInputMouseWheel();

  //所有email class 输入时去掉两边空格
  bindTextChange($(".email"));


});