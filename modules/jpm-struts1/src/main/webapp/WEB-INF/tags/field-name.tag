<%@ tag description="This tag builds the name of a field" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "entity" required="true" type="jpaoletti.jpm.core.Entity" %>
<%@attribute name = "field" required="true" type="jpaoletti.jpm.core.Field" %>
<pmfn:message key="pm.field.${entity.id}.${field.id}" />&nbsp;${pmfn:tooltip(entity, field)}
