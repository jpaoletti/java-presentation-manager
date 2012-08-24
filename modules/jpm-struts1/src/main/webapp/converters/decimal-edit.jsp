<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<input class="decimal-converter" type="text" maxlength="${param.ml}"
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" 
       placeholder="${ctx.field.title}"/>
<script type="text/javascript" src="${es.context_path}/js/jquery/jquery.decimalmask.js"></script>
<script type="text/javascript">
    PM_register(function(){$("#f_${param.f}").decimalMask(Array(21).join("9")+"${param.separator}"+Array(${param.decimals}).join("9"));});
</script>