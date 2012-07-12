<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="titles.welcome">
    <div class="boxed">
        <h2 class="title"><pmfn:message key="index.welcome" arg0="${pmsession.user.name}"/></h2>
        <jsp:useBean id="date" class="java.util.Date"/>
        <p><pmfn:message key="index.time" /><fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm"/></p>
    </div>
</pm:page>