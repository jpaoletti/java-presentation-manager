<%@tag description="This tag encapsulates a title" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "key" required="true" type="java.lang.String" %>
<%@attribute name = "key_operation" required="false" type="java.lang.String" %>
<h2 class="title">
    &nbsp;<pmfn:message key="${key}" />&nbsp;
    <c:if test="${not empty key_operation}">(<pmfn:message key="${key_operation}" />)</c:if>
</h2>
