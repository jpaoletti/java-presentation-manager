<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<input class="integer-converter" type="text" maxlength="${param.ml}" 
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" 
       placeholder="${ctx.field.title}" />
<script type="text/javascript" src="${es.context_path}/js/jquery/autoNumeric.js"></script>
<script type="text/javascript">PM_register(function(){$("#f_${param.f}").autoNumeric('init', {aSep: '', aDec: '.', mDec: '0', vMax: '999999999'});});</script>