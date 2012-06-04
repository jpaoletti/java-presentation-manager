<%@ tag description="This tag builds the name of a field" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "field" required="true" type="jpaoletti.jpm.core.Field" %>
<%@attribute name = "tooltip" required="false" type="java.lang.Boolean" description="Show tooltip" %>
${field.title}&nbsp;${(empty tooltip or tooltip)?pmfn:tooltip(field):""}
