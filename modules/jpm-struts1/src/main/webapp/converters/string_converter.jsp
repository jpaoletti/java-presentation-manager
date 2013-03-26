<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<input class="string-converter" type="text" maxlength="${param.ml}"
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" 
       placeholder="${ctx.field.title}"/>