<%@include file="../inc/tag-libs.jsp" %>
<ul class="collection-converter-show">
    <c:forEach var="s" items="${ctx.map.collection}">
        <li>${s}</li>
    </c:forEach>
</ul>