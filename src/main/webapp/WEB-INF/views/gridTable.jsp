<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="lessonTag" uri="http://com.biz.lesson/tag/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<lesson:page title="trip">
	<jsp:attribute name="css">
        <style type="text/css">
            /*.trip4-container select {*/
                /*border: 0;*/
                /*background: transparent;*/
                /*appearance:none;*/
                /*-moz-appearance:none;*/
                /*-webkit-appearance:none;*/
                /*padding-right: 14px;*/
            /*}*/
            /*.trip4-container select::-ms-expand { display: none; }*/
        </style>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
		<script src="static-resource/ace/assets/js/jquery.jqGrid.min.js"></script>
		<script src="static-resource/ace/assets/js/grid.setcolumns.js"></script>
		<script src="static-resource/ace/assets/js/grid.locale-en.js"></script>
		<script src="static-resource/ace/assets/js/jszip.min.js"></script>

        <script type="text/javascript">
            function saveRow(){

            }
            $("body").on("focus", "#grid-table input[name='name']", function(){
                var $this = $(this);
                selectHotel(function(hotel){
                    console.log("update data for selected hotel:" + JSON.stringify(hotel));
                    if(hotel){
                        $this.val(hotel.hotelName);
                    }
                })
            });
            $(document).keyup(function(e){
                console.log(e);
                console.log(e.keyCode);
                var $selectedGridTr = $(".ui-jqgrid-btable tr.ui-state-highlight");
                if($selectedGridTr.size()){
                    switch (e.keyCode){
                        case 13:
                            jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-save").get(0),'save');
                            jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-cancel").get(0),'cancel');
                            break;
                        case 27:
                            jQuery.fn.fmatter.rowactions.call($selectedGridTr.find(".ui-inline-cancel").get(0),'cancel');
                            break;
                    }
                }
            });
            var gridTableData =
                    [
                        {id:"t1",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                        {id:"t2",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                        {id:"t3",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                        {id:"t4",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
                        {id:"t5",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t6",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t7",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
                        {id:"t8",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                        {id:"t9",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t10",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                        {id:"t11",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                        {id:"t12",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                        {id:"t13",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"},
                        {id:"t14",name:"Laser Printer",note:"note2",stock:"Yes",ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t15",name:"Play Station",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t16",name:"Mobile Telephone",note:"note",stock:"Yes",ship:"ARAMEX",sdate:"2007-12-03"},
                        {id:"t17",name:"Server",note:"note2",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                        {id:"t18",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t19",name:"Matrix Printer",note:"note3",stock:"No", ship:"FedEx",sdate:"2007-12-03"},
                        {id:"t20",name:"Desktop Computer",note:"note",stock:"Yes",ship:"FedEx", sdate:"2007-12-03"},
                        {id:"t21",name:"Laptop",note:"Long text ",stock:"Yes",ship:"InTime",sdate:"2007-12-03"},
                        {id:"t22",name:"LCD Monitor",note:"note3",stock:"Yes",ship:"TNT",sdate:"2007-12-03"},
                        {id:"t23",name:"Speakers",note:"note",stock:"No",ship:"ARAMEX",sdate:"2007-12-03"}
                    ];

            jQuery(function($) {
                var gridTableSelector = "#grid-table";
                var tablePagerSelector = "#grid-pager";


                var parent_column = $(gridTableSelector).closest('[class*="col-"]');
                //resize to fit page size
                $(window).on('resize.jqGrid', function () {
                    $(gridTableSelector).jqGrid( 'setGridWidth', parent_column.width() );
                });

                //resize on sidebar collapse/expand
                $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
                    if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
                        //setTimeout is for webkit only to give time for DOM changes and then redraw!!!
                        setTimeout(function() {
                            $(gridTableSelector).jqGrid( 'setGridWidth', parent_column.width() );
                        }, 20);
                    }
                });

                var lastSel;
                $(gridTableSelector).jqGrid({
                    //direction: "rtl",
                    data: gridTableData,
                    datatype: "local",
                    height: 250,
                    colNames:[' ', 'ID','Last Sales','Name', 'Stock', 'Ship via','Notes'],
                    colModel:[
                        {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                            formatter:'actions',
                            formatoptions:{
                                keys:false,
                                //delbutton: false,//disable delete button
                                editbutton: false, //禁用第行的编辑图标
                                delbutton:false,
                                savebutton:false,
                                saveOptions:{recreateForm: false, beforeShowForm:beforeSaveCallback},
//                                delOptions:{recreateForm: false, beforeShowForm:beforeDeleteCallback},
                                //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                            }
                        },
                        {name:'id',index:'id', width:60, sorttype:"string", editable: true},
                        {name:'sdate',index:'sdate',width:90, editable:true, sorttype:"date",unformat: pickDate},
                        {name:'name',index:'name', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
                        {name:'stock',index:'stock', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
                        {name:'ship',index:'ship', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
                        {name:'note',index:'note', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}, editrules:{edithidden:true, required:true}}
                    ],

                    viewrecords : true,
                    rowNum:10,
                    rowList:[10,20,30],
                    pager : tablePagerSelector,
                    altRows: true,
                    //toppager: true,

                    multiselect: true,
                    //multikey: "ctrlKey",
                    multiboxonly: true,

                    loadComplete : function() {
                        console.log("数据加载完成");
                        var table = this;
//                        $(table).find(".ui-inline-save").attr("onclick", "saveRow()");
                        setTimeout(function(){
                            updatePagerIcons(table);
                            enableTooltips(table);
                        }, 0);
                    },
                    beforeSubmit : function(){
                        console.log("beforeSubmit");
                        consoleLogArguments(this)
                    },
                    onSelectRow : function(id){
                        console.log("点击行:" + id);
                        consoleLogArguments(arguments);
                        if(id && id!==lastSel){
                            $(this).restoreRow(lastSel);
                            $(this).editRow(id, true);
                            var $currentTr = $(this).find("tr#" + id);
                            $currentTr.find(".ui-inline-del").hide();
                            $currentTr.find(".ui-inline-save").show();
                            $currentTr.find(".ui-inline-cancel").show();
                            lastSel=id;
                        }
                    },

                    editurl: "#",//nothing is saved
                    caption: "jqGrid with inline editing"

                    //,autowidth: true,

                }).bind("jqGridInlineAfterSaveRow", function(e, rowid, orgClickEvent){
                    console.log("可用于更新页面信息");
                    console.log(rowid);
                });

                $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size

                //switch element when editing inline
                function aceSwitch( cellvalue, options, cell ) {
                    console.log("aceSwitch 此方法为编辑字段format")
                    setTimeout(function(){
                        $(cell) .find('input[type=checkbox]')
                                .addClass('ace ace-switch ace-switch-5')
                                .after('<span class="lbl"></span>');
                    }, 0);
                }
                //enable datepicker
                function pickDate( cellvalue, options, cell ) {
                    setTimeout(function(){
                        $(cell) .find('input[type=text]')
                                .datepicker({format:'yyyy-mm-dd' , autoclose:true});
                    }, 0);
                }


                //navButtons
                $(gridTableSelector).jqGrid('navGrid',tablePagerSelector,
                    { 	//navbar options
                        edit: false,
                        editicon : 'ace-icon fa fa-pencil blue',
                        add: true,
                        addicon : 'ace-icon fa fa-plus-circle purple',
                        del: true,
                        delicon : 'ace-icon fa fa-trash-o red',
                        search: true,
                        searchicon : 'ace-icon fa fa-search orange',
                        refresh: true,
                        refreshicon : 'ace-icon fa fa-refresh green',
                        view: true,
                        viewicon : 'ace-icon fa fa-search-plus grey',
                    },
                    {
                        //edit record form
                        //closeAfterEdit: true,
                        //width: 700,
                        recreateForm: true,
                        beforeShowForm : function(e) {
                            console.log("beforeShowForm");
//                            var form = $(e[0]);
//                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
//                            style_edit_form(form);
                        }
                    },
                    {
                        //new record form
                        //width: 700,
                        closeAfterAdd: true,
                        recreateForm: true,
                        viewPagerButtons: false,
                        beforeShowForm : function(e) {
                            console.log("beforeShowForm");
//                            var form = $(e[0]);
//                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
//                                    .wrapInner('<div class="widget-header" />')
//                            style_edit_form(form);
                        }
                    },
                    {
                        //delete record form
                        recreateForm: true,
                        beforeShowForm : function(e) {
                            console.log("beforeShowForm");
                            var form = $(e[0]);
                            if(form.data('styled')) return false;

                            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                            style_delete_form(form);

                            form.data('styled', true);
                        },
                        onClick : function(e) {
                            //alert(1);
                        }
                    },
                    {
                        //search form
                        recreateForm: true,
                        afterShowSearch: function(e){
                            console.log("beforeShowForm");
                            var form = $(e[0]);
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                            style_search_form(form);
                        },
                        afterRedraw: function(){
                            style_search_filters($(this));
                        }
                        ,
                        multipleSearch: true,
                        /**
                         multipleGroup:true,
                         showQuery: true
                         */
                    },
                    {
                        //view record form
                        recreateForm: true,
                        beforeShowForm: function(e){
                            console.log("beforeShowForm");
                            var form = $(e[0]);
                            form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                        }
                    }
                );



                function style_edit_form(form) {
                    console.log("style_edit_form");
                    //enable datepicker on "sdate" field and switches for "stock" field
                    form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})

                    form.find('input[name=stock]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
                    //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
                    //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');


                    //update buttons classes
                    var buttons = form.next().find('.EditButton .fm-button');
                    buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
                    buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
                    buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

                    buttons = form.next().find('.navButton a');
                    buttons.find('.ui-icon').hide();
                    buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
                    buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
                }

                function style_delete_form(form) {
                    console.log("style_delete_form");
                    var buttons = form.next().find('.EditButton .fm-button');
                    buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
                    buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
                    buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
                }

                function style_search_filters(form) {
                    console.log("style_search_filters");
                    form.find('.delete-rule').val('X');
                    form.find('.add-rule').addClass('btn btn-xs btn-primary');
                    form.find('.add-group').addClass('btn btn-xs btn-success');
                    form.find('.delete-group').addClass('btn btn-xs btn-danger');
                }
                function style_search_form(form) {
                    console.log("style_search_form");
                    var dialog = form.closest('.ui-jqdialog');
                    var buttons = dialog.find('.EditTable')
                    buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
                    buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
                    buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
                }

                function beforeSaveCallback(e){
                    console.log("beforeSaveCallback");
                }

                function beforeDeleteCallback(e) {
                    console.log("点击删除按钮");
                    consoleLogArguments(arguments);
                    var form = $(e[0]);
                    if(form.data('styled')) return false;

                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);

                    form.data('styled', true);
                }

                function beforeEditCallback(e) {
                    console.log("beforeEditCallback");
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                }


                //replace icons with FontAwesome icons like above
                function updatePagerIcons(table) {
                    var replacement =
                    {
                        'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
                        'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
                        'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
                        'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
                    };
                    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
                        var icon = $(this);
                        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
                    })
                }

                function enableTooltips(table) {
//                    $('.navtable .ui-pg-button').tooltip({container:'body'});
//                    $(table).find('.ui-pg-div').tooltip({container:'body'});
                }

                //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

                $(document).one('ajaxloadstart.page', function(e) {
                    $.jgrid.gridDestroy(gridTableSelector);
                    $('.ui-jqdialog').remove();
                });
            });

        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row trip4-container">
            <div class="col-xs-12" id="trip4-container">
                <table id="grid-table"></table>
                <div id="grid-pager"></div>
            </div>
        </div>
    </jsp:body>
</lesson:page>