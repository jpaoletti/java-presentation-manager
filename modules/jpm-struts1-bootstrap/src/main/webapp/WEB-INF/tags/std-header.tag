<%@tag description="This tag creates an standard operation header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "ctx" required="true" type="jpaoletti.jpm.core.PMContext" %>
<%@attribute name = "operations" required="false" type="jpaoletti.jpm.core.Operations" %>
<div class="std-header" id="${ctx.entity.id}_${ctx.operation}_header">
    <div id="navigation_bar"><pmfn:navigation container="${ctx.entityContainer.owner}" /></div>
    <div class="well well-small"><pm:operations ctx="${ctx}" operations="${operations}" /></div>
</div>