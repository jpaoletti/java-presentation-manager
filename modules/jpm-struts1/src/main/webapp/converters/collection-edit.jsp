<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<c:forEach var="o" items="${ctx.map.fullList}" varStatus="s">
    <input type="checkbox" ${(pmfn:contains(ctx.map.idList,o.key))?'checked':''}
           value="${o.key}" id="f_${param.f}" name="f_${param.f}" />&nbsp;${o.value}<br/>
</c:forEach>