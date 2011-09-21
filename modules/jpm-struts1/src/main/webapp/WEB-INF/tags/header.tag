<%@tag description="This tag encapsulates site header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<div id="header">
    <pm:topmenu />
    <div id="logo">
        <h1 class="title"><a href="${es.context_path}"><pmfn:message key="${pm.title}"/></a></h1>
        <h2 class="title"><pmfn:message key="${pm.subtitle}"/></h2>
    </div>
    <c:if test="${not empty pmsession.user}">
        <div id="userbox">
            <img src='${pmsession.user.gravatar}?s=50&d=mm' alt="gravatar"/> &nbsp;${pmsession.user.name} &nbsp; |
            <c:if test="${pm.loginRequired}">
                &nbsp; <a href="javascript:loadPage('${es.context_path}/show.do?pmid=secuserprofile&identified=username:${pmsession.user.username}');"><pmfn:message key="user.profile"/></a> &nbsp; |
            </c:if>
            &nbsp; <a href="${es.context_path}/logout.do" title="<pmfn:message key="logout"/>"><pmfn:message key="logout"/></a>
        </div>
    </c:if>
</div>