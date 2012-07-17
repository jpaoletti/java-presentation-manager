<%@tag description="This tag creates an standard form" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name = "action" required="true" type="java.lang.String" %>
<%@attribute name = "editable" required="true" type="java.lang.Boolean"  %>
<%@attribute name = "resetable" required="false" type="java.lang.Boolean"  %>
<%@attribute name = "formId" required="false" type="java.lang.String" %>
<div class="content ui-widget">
    <c:if test="${editable}">
        <form action="${pmfn:plainUrl(ctx.pmsession, action)}" accept-charset="UTF-8"
              method="POST" class="form-horizontal" id="${formId}">
            <input type="hidden" name="finish" value="yes" />
            <fieldset>
                <jsp:doBody />
                <div class="form-actions">
                    <a class="btn btn-danger" id="${entity.id}_cancel" href="javascript:history.back();"><i class="icon-remove"></i>&nbsp;<pmfn:message key="pm.struts.form.cancel"/></a>
                    <c:if test="${resetable}">
                        <button class="btn btn-warning" type="reset" id="${entity.id}_reset"><i class="icon-fire"></i>&nbsp;<pmfn:message key="pm.struts.form.reset"/></button>
                    </c:if>                    
                    <button class="btn btn-success" type="submit" id="${entity.id}_submit"><i class="icon-ok"></i>&nbsp;<pmfn:message key="pm.struts.form.submit"/></button>
                </div>
            </fieldset>
        </form>
    </c:if>
    <c:if test="${not editable}">
        <jsp:doBody />
    </c:if>
</div>