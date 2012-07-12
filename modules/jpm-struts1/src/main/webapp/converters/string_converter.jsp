<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true"
           id="f_${param.f}_null" name="f_${param.f}_null"
           onclick="enable_disable('f_${param.f}', this.checked);" />
</c:if>
<input class="string-converter" type="text" maxlength="${param.ml}"
       ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''}
       value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" 
       placeholder="${ctx.field.title}"/>