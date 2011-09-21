<%@tag description="This tag creates an standard form" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name = "contextPath" required="true" type="java.lang.String" %>
<%@attribute name = "entity" required="true" type="jpaoletti.jpm.core.Entity" %>
<%@attribute name = "operation" required="true" type="jpaoletti.jpm.core.Operation" %>
<%@attribute name = "editable" required="true" type="java.lang.Boolean"  %>
<c:if test="${editable}">
    <form action="${contextPath}/${operation.id}.do?pmid=${entity.id}"  accept-charset="UTF-8" >
        <input type="hidden" name="finish" value="yes" />
        <fieldset>
            <div class="content">
                <jsp:doBody />
                <input type="submit" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.submit"/>" />
                <input type="reset" id="${entity.id}_submit" value="<pmfn:message key="pm.struts.form.reset" />" />
            </div>
        </fieldset>
    </form>
</c:if>
<c:if test="${not editable}">
    <div class="content">
        <jsp:doBody />
    </div>
</c:if>