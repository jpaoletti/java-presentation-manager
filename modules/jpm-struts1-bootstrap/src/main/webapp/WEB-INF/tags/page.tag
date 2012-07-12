<?xml version="1.0" encoding="UTF-8"?>
<%@ tag description="This tag encapsulates a standard html page" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="loading" required="false"  %>
<!DOCTYPE html>
<c:if test="${not empty pm}">
    <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1"> 
            <title><pmfn:message key="${pm.title}"/> - <pmfn:message key="${title}"/></title>
            <link href="${es.templatePath}all.css?v=${es.pm.appversion}" rel="stylesheet" type="text/css" />
            <link href="${es.templatePath}img/favicon.ico?v=${es.pm.appversion}" rel="shortcut icon" />
            <!--[if lt IE 9]><script type="text/javascript" src="${es.context_path}/js/html5.js"></script><![endif]-->
            <script type="text/javascript" src="${es.context_path}/js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/bootstrap.min.js?v=${es.pm.appversion}"></script>
            <script type="text/javascript" src="${es.context_path}/js/jpm.js?v=${es.pm.appversion}"></script>
            <script type="text/javascript" src="${es.context_path}/js/custom.js?v=${es.pm.appversion}"></script>
        </head>
        <body class="${bodyClass} outer-index">
            <%@include file="/WEB-INF/jsp/loading.jsp" %>
            <div id="page-container" style="display: none;">
                <%-- Parameter noHeader can be used for ajax page inclusion --%>
                <c:if test="${not param.noHeader}">
                    <%@include file="/WEB-INF/jsp/header.jsp" %>
                </c:if>
                <div class="container-fluid" >
                    <c:if test="${empty ctx.pmsession}">
                        <%@include file="/WEB-INF/jsp/welcome.jsp" %>
                    </c:if>
                    <c:if test="${not empty ctx.pmsession}">
                        <% try {%>
                        <jsp:doBody />
                        <% } catch (Exception e) {
                            jpaoletti.jpm.core.PresentationManager.getPm().error(e);
                        %>
                        <pmfn:message key="pm.page.error"/>
                        <%}%>
                    </c:if>
                </div>
            </div>
            <%@include file="/WEB-INF/jsp/confirm-dialog.jsp" %>
            <%@include file="/WEB-INF/jsp/basic-javascript.jsp" %>
            <%@include file="/WEB-INF/jsp/footer.jsp" %>
            <%@include file="/WEB-INF/jsp/popup.jsp" %>
        </body>
    </html>
</c:if>
<c:if test="${empty pm}">
    <%@include file="/WEB-INF/jsp/pm-error.jsp" %>
</c:if>