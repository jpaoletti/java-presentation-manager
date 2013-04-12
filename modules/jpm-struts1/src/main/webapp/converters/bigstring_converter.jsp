<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true" 
           id="f_${param.f}_null" name="f_${param.f}_null"
           onclick="disable('${param.f}', this.checked);" /><br/>
</c:if>
<textarea cols="${param.cols}" rows="${param.rows}"  
          ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''}
          id="f_${param.f}" name="f_${param.f}">${ctx.fieldValue}</textarea>
