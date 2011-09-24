<%@tag description="" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<div id="topmenu">
    <ul>
        <li><a href="${es.context_path}/index.jsp" id="topmenu1"><pmfn:message key="home"/></a></li>
        <li><a href="mailto:${pm.contact}" id="topmenu3" accesskey="3" title="<pmfn:message key="contact"/>"><pmfn:message key="contact"/></a></li>
    </ul>
</div>