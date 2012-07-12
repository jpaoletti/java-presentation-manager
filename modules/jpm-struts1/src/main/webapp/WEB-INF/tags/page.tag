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
            <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
            <meta name="viewport" content="width=device-width, initial-scale=1"> 
            <title><pmfn:message key="${pm.title}"/> - <pmfn:message key="${title}"/></title>
            <link href="${es.templatePath}/all.css" rel="stylesheet" type="text/css" />
            <link rel="shortcut icon" href="${es.templatePath}/img/favicon.ico" />
            <script type="text/javascript" src="${es.context_path}/js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/misc.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/jquery/jquery-object-converter.js"></script>
        </head>
        <body class="${bodyClass}">
            <%@include file="/WEB-INF/jsp/loading.jsp" %>
            <div class="index_layout" id="page-container" style="display: none;">
                <%-- Paramter noHeader can be used for ajax page inclusion --%>
                <c:if test="${not param.noHeader}">
                    <%@include file="/WEB-INF/jsp/header.jsp" %>
                </c:if>
                <div class="index_layout_content">
                    <c:if test="${empty pmsession}">
                        <%@include file="/WEB-INF/jsp/login.jsp" %>
                    </c:if>
                    <c:if test="${not empty pmsession}">
                        <span id="bodySpan">
                            <% try {%>
                            <div class="content">
                                <jsp:doBody />
                            </div>
                            <% } catch (Exception e) {
                                jpaoletti.jpm.core.PresentationManager.getPm().error(e);
                            %>
                            <pmfn:message key="pm.page.error"/>
                            <%}%>
                        </span>
                    </c:if>
                </div>
            </div>
            <div id="jpm-popup"><div id="jpm-popup-ajax"></div></div>
            <%@include file="/WEB-INF/jsp/confirm-dialog.jsp" %>
            <%@include file="/WEB-INF/jsp/basic-javascript.jsp" %>
        </body>
    </html>
</c:if>
<c:if test="${empty pm}">
    <%@include file="/WEB-INF/jsp/pm-error.jsp" %>
</c:if>