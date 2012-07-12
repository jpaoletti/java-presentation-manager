<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<a href="${pmfn:plainUrl(ctx.pmsession,ctx.map.other_operation.concat('.do?pmid=').concat(ctx.map.other_entity).concat('&identified=').concat(ctx.map.identified))}">${ctx.map.display}</a>