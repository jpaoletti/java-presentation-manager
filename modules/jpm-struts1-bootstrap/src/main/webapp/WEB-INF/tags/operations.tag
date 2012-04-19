<%@tag description="Operations for an operation" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@attribute name = "ctx" required="true" type="jpaoletti.jpm.core.PMContext" %>
<%@attribute name = "operations" required="false" type="jpaoletti.jpm.core.Operations" %>
<div id="operations_bar" class="btn-group">
    <c:forEach var="operation" items="${(not empty operations.operations)?operations.operations:ctx.map.operations.operations}">
        <pmfn:checkPerm perm="${operation.perm}">
            <c:if test="${empty operation.url}">
                <a id="operation${operation.id}" class="btn" href="${pmfn:urlc(ctx.pmsession, operation.id.concat('.do?pmid=').concat(ctx.entity.id).concat((not empty ctx.map.item)?'&item='.concat(ctx.map.item):''), operation.confirm)}">
                    <i class="ui-icon ui-icon-operation-${operation.id}"></i>
                    <c:if test="${!operation.compact}">${operation.title}</c:if>
                </a>
            </c:if>
            <c:if test="${not empty operation.url}">
                <a id="operation${operation.id}" class="btn" href="${operation.url}">
                    <i class="ui-icon ui-icon-operation-${operation.id}"></i>
                    <c:if test="${!operation.compact}">${operation.title}</c:if>
                </a>
            </c:if>
        </pmfn:checkPerm>
    </c:forEach>
</div>