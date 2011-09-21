<%@include file="../inc/tag-libs.jsp" %>
<input type="text" value="${param.value}" id="f_${param.f}" name="f_${param.f}" />
<script type="text/javascript" src="${es.context_path}/js/jquery-ui.js"></script>
<script type='text/javascript'>
    PM_register(function(){
        $('#f_${param.f}').datepicker({
            buttonImage: '${es.context_path}/templates/${pm.template}/img/calendar.gif',
            buttonImageOnly: true,
            buttonText: '',
            showOn: 'both',
            speed: 'fast',
            dateFormat: '${param.format}'
        });
    });
</script>