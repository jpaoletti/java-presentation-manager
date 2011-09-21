<%@tag description="This tag creates an standard operation header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "ctx" required="true" type="jpaoletti.jpm.core.PMContext" %>
<%@attribute name = "operations" required="false" type="jpaoletti.jpm.core.Operations" %>
<pm:pmtitle entity="${ctx.entity}" operation="${ctx.operation}" />
<pmfn:operations ctx="${ctx}" operations="${operations}" labels="true" />
<div id="navigation_bar"><pmfn:navigation container="${ctx.entityContainer.owner}" /></div>