<%@tag description="This tag encapsulates a title" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "key" required="true" type="java.lang.String" %>
<%@attribute name = "key_operation" required="false" type="java.lang.String" %>
<h2 class="title">
    <a href="javascript:history.back();" title="<pmfn:message key="pm.title.back"/>">
        <img alt="<pmfn:message key="pm.title.back"/>"
             src="${es.context_path}/templates/${pm.template}/img/arrow_back.gif"
             style="vertical-align:middle;"/></a>
    &nbsp;<pmfn:message key="${key}" />&nbsp;
    <c:if test="${not empty key_operation}">(<pmfn:message key="${key_operation}" />)</c:if>
    <a href="javascript:location.reload(true)" title="<pmfn:message key="pm.title.refresh"/>">
        <img alt="<pmfn:message key="pm.title.refresh"/>"
             src="${es.context_path}/templates/${pm.template}/img/reload.gif"
             style="vertical-align:middle;"/></a>
</h2>