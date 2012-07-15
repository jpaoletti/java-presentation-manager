<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    PM_register(function(){
        var form = $("#control-group-${param.f}").parents("form");
        var row = jQuery('<div/>').appendTo(form).addClass("row-fluid");
        var row2 = jQuery('<div/>').appendTo(form).addClass("row-fluid");
        var div1 = jQuery('<div/>').appendTo(row).addClass("${param.class1}");
        var div2 = jQuery('<div/>').appendTo(row).addClass("${param.class2}");
        var buttons = jQuery('<div/>').appendTo(row2).addClass("span12 clearfix");
        var all = $(".control-group");
        $.each(all.slice(0,${param.count1}), function(){
            $(this).detach().appendTo(div1);
        });
        $.each(all.slice(${param.count1}), function(){
            $(this).detach().appendTo(div2);
        });
        form.find(".well-small").appendTo(buttons);
        $("#control-group-${param.f}").remove();
    });
</script>