<%@tag description="This tag encapsulates a PM title" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name = "entity" required="true" type="jpaoletti.jpm.core.Entity" %>
<%@attribute name = "operation" required="true" type="jpaoletti.jpm.core.Operation"%>
<c:if test="${operation.showTitle}"><h2 class="title"><pmfn:message key="entity.pmtitle" arg0="${entity.title}" arg1="${operation.title}" /></h2></c:if>