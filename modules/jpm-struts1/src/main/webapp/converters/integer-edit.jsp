<%@include file="../inc/tag-libs.jsp" %>
<input class="integer-converter" alt="intmask" type="text" maxlength="${param.ml}" 
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" />
<script type="text/javascript" src="${es.context_path}/js/jquery.meio.mask.min.js"></script>
<script type="text/javascript">
    PM_register(function(){
        $.mask.masks.intmask = {mask: '999999999'}
        $(".integer-converter").setMask();
    });
</script>