<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="icon" required="false" type="java.lang.String" description="手机待小屏幕下，在顶部展示的开关按钮的图标" %>
<div class="invisible">
    <button data-target="#sidebar_tag_${sidebar_seq}" data-toggle="collapse" type="button" class="pull-right navbar-toggle collapsed">
        <span class="sr-only">Toggle sidebar</span>

        <i class="ace-icon ${empty icon ? 'fa fa-dashboard' : icon} white bigger-125"></i>
    </button>
    <div id="sidebar_tag_${sidebar_seq}" class="sidebar h-sidebar navbar-collapse collapse ace-save-state">
        <ul class="nav nav-list">
            <jsp:doBody />
        </ul>
    </div>
</div>
<script type="application/javascript">
    jQuery(function($) {
        $('#sidebar_tag_${sidebar_seq}').insertBefore('.page-content');

        $('.navbar-toggle[data-target="#sidebar_tag_${sidebar_seq}"]').insertAfter('#menu-toggler');


        $(document).on('settings.ace.two_menu', function(e, event_name, event_val) {
            if(event_name == 'sidebar_fixed') {
                if( $('#sidebar_tag_').hasClass('sidebar-fixed') ) {
                    $('#sidebar_tag_${sidebar_seq}').addClass('sidebar-fixed');
                    $('#navbar').addClass('h-navbar');
                }
                else {
                    $('#sidebar_tag_${sidebar_seq}').removeClass('sidebar-fixed');
                    $('#navbar').removeClass('h-navbar');
                }
            }
        }).triggerHandler('settings.ace.two_menu', ['sidebar_fixed' ,$('#sidebar_tag_${sidebar_seq}').hasClass('sidebar-fixed')]);
    })
</script>