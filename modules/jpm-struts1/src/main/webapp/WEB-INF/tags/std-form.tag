<%@tag description="This tag creates an standard form" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name = "contextPath" required="true" type="java.lang.String" %>
<%@attribute name = "entity" required="true" type="jpaoletti.jpm.core.Entity" %>
<%@attribute name = "operation" required="true" type="jpaoletti.jpm.core.Operation" %>
<%@attribute name = "editable" required="true" type="java.lang.Boolean"  %>
<%@attribute name = "resetable" required="false" type="java.lang.Boolean"  %>
<div class="content ui-widget">
    <c:if test="${editable}">
        <form action="${pmfn:plainUrl(ctx.pmsession, operation.id.concat('.do?pmid=').concat(entity.id))}" accept-charset="UTF-8" method="POST">
            <input type="hidden" name="finish" value="yes" />
            <fieldset>
                <jsp:doBody />
                <br/>
                <button class="submit" type="submit" id="${entity.id}_submit"><pmfn:message key="pm.struts.form.submit"/></button>
                <c:if test="${resetable}">
                    &nbsp;&nbsp;
                    <button class="reset" type="submit" id="${entity.id}_reset"><pmfn:message key="pm.struts.form.reset"/></button>
                </c:if>
            </fieldset>
        </form>
    </c:if>
    <c:if test="${not editable}">
        <jsp:doBody />
    </c:if>
</div>