<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true" 
           id="f_${param.f}_null" name="f_${param.f}_null" 
           onclick="enable_disable('f_${param.f}', this.checked);" />
</c:if>
<input class="integer-converter" alt="intmask" type="text" maxlength="${param.ml}" 
       ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''} 
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" />
<script type="text/javascript" src="${es.context_path}/js/jquery.meio.mask.min.js"></script>
<script type="text/javascript">
    PM_register(function(){
        $.mask.masks.intmask = {mask: '999999999'}
        $(".integer-converter").setMask();
    });
</script>