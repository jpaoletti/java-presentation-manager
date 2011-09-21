<%@include file="../inc/tag-libs.jsp" %>
<c:forEach var="o" items="${ctx.tmpList}" varStatus="s">
    <input type="checkbox"
           ${(fn:contains(ctx.fieldValue, o))?'checked':''}
           value="${s.index}" id="f_${param.f}" name="f_${param.f}" />&nbsp;${o}<br/>
</c:forEach>