<%@tag description="This tag creates an standard operation header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "ctx" required="true" type="jpaoletti.jpm.core.PMContext" %>
<%@attribute name = "operations" required="false" type="jpaoletti.jpm.core.Operations" %>
<span id="${ctx.entity.id}_${ctx.operation}_header">
    <div id="navigation_bar"><pmfn:navigation container="${ctx.entityContainer.owner}" /></div>
    <div id="operations_bar"><pmfn:operations ctx="${ctx}" operations="${operations}" labels="true" /></div>
</span>