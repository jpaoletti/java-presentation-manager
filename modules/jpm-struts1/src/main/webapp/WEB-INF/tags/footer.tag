<%@tag description="" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld"  prefix="fmt" %>
<div id="footer">
    <div id="appversion">v${pm.appversion}</div>
    <p id="legal"><pmfn:message key="footer.copyright.pre" /><fmt:formatDate pattern="yyyy"  value="<%=new java.util.Date() %>" />&nbsp;${pm.copyright}&nbsp;<pmfn:message key="footer.copyright.post" /></p>
</div>