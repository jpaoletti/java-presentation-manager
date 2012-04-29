<%@tag description="This tag creates an standard form" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name = "contextPath" required="true" type="java.lang.String" %>
<%@attribute name = "entity" required="true" type="jpaoletti.jpm.core.Entity" %>
<%@attribute name = "operation" required="true" type="jpaoletti.jpm.core.Operation" %>
<%@attribute name = "editable" required="true" type="java.lang.Boolean"  %>
<%@attribute name = "resetable" required="false" type="java.lang.Boolean"  %>
<div>
    <c:if test="${editable}">
        <form action="${pmfn:plainUrl(ctx.pmsession, operation.id.concat('.do?finish=yes&pmid=').concat(entity.id))}" 
              accept-charset="UTF-8" 
              method="POST" class="form-horizontal"
              id="form_${ctx.entity.id}_${operation.id}">
            <fieldset>
                <jsp:doBody />
                <div class="well well-small">
                    <button class="btn btn-danger" id="${entity.id}_cancel" onclick="history.back();"><i class="icon-remove"></i>&nbsp;<pmfn:message key="pm.struts.form.cancel"/></button>
                    <c:if test="${resetable}">
                        <button class="btn btn-warning" type="submit" id="${entity.id}_reset"><i class="icon-fire"></i>&nbsp;<pmfn:message key="pm.struts.form.reset"/></button>
                    </c:if>                    
                    <button class="btn btn-primary" type="submit" id="${entity.id}_submit"><i class="icon-ok"></i>&nbsp;<pmfn:message key="pm.struts.form.submit"/></button>
                </div>
            </fieldset>
        </form>
    </c:if>
    <c:if test="${not editable}">
        <form action="#" class="form-horizontal">
            <fieldset>
                <jsp:doBody />
            </fieldset>
        </form>
    </c:if>
</div>