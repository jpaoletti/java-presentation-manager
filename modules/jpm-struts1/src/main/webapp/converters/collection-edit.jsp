<%@include file="../inc/tag-libs.jsp" %>
<c:forEach var="o" items="${ctx.map.fullList}" varStatus="s">
    <input type="checkbox" ${(o.value0 == ctx.map.idList[s.index])?'checked':''}
           value="${o.value0}" id="f_${param.f}" name="f_${param.f}" />&nbsp;${o.value1}<br/>
</c:forEach>