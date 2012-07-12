<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true" 
           id="f_${param.f}_null" name="f_${param.f}_null" 
           onclick="enable_disable('f_${param.f}', this.checked);" />
</c:if>
<input class="decimal-converter" alt="${param.f}mask" type="text" maxlength="${param.ml}" 
       ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''} 
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" />
<script type="text/javascript" src="${es.context_path}/js/jquery.caret.min.js"></script>
<script type="text/javascript" src="${es.context_path}/js/jquery.decimalMask.1.1.1.min.js"></script>
<script type="text/javascript">
    PM_register(function(){
        $("#f_${param.f}").decimalMask({
            separator: "${param.separator}",
            decSize: ${param.decimals},
            intSize: 20
        });
    });
</script>