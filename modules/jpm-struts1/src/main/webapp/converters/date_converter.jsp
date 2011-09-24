<%@include file="../inc/tag-libs.jsp" %>
<input type="text" value="${param.value}" id="f_${param.f}" name="f_${param.f}" size="10"/>
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