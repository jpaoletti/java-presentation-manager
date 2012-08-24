<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<input type="file" name="f_${param.f}" accept="${param.accept}" class="file-converter"/>
<c:if test="${param.delete}">
    <span class="file-converter-chechbox"><input type="checkbox" name="f_${param.f}_delete" value="true" /><pmfn:message key="file.converter.delete" /></span>
</c:if>
<span class="file-text">${param.text}</span>