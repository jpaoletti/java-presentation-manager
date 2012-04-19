<%@tag description="This tag creates an standard operation header" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "title" required="true" %>
<%@attribute name = "ctx" required="true" type="jpaoletti.jpm.core.PMContext" %>
<%@attribute name = "operations" required="false" type="jpaoletti.jpm.core.Operations" %>
<h2 class="title"><pmfn:message key="${title}"/></h2>
<pm:operations ctx="${ctx}" operations="${operations}" />
