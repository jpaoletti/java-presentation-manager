<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<ul class="collection-converter-show">
    <c:forEach var="s" items="${ctx.map.collection}">
        <li>${s}</li>
    </c:forEach>
</ul>