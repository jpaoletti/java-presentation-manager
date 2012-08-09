<%@include file="../inc/tag-libs.jsp" %>
<script src="${es.context_path}/js/bootstrap-plugins/bootstrap-datepicker.js"></script>
<script src="${es.context_path}/js/bootstrap-plugins/locales/bootstrap-datepicker.${param.lang}.js"></script>
<div class="input-append date" data-date="${param.value}">
    <input class="input-small" type="text" value="${param.value}" id="f_${param.f}" name="f_${param.f}" size="10"/><span class="add-on"><i class="icon-calendar"></i></span>
</div>
<script type='text/javascript'>
    PM_register(function(){
        $('#f_${param.f}').parent().datepicker({
            format: '${param.format}'.replace(/\y/g, 'yy'),
            language: '${param.lang}',
            autoclose: true
        });
    });
</script>