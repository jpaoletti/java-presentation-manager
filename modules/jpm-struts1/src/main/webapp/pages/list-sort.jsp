<%@include file="../inc/tag-libs.jsp" %>
<br/>
<div id="sortDialog" title="<pmfn:message key='operation.sort' />">
    <div class="col1" >
        <pmfn:message key='list.sort.field' /><br/>
        <select id="dialogOrder" name="order">
            <c:forEach var="field" items="${pmfn:displayedFields(entity, 'sort')}">
                <option value="${field.id}" ${(ctx.entityContainer.list.sort.fieldId==field.id)?'selected':''}>
                    <pm:field-name entity='${entity}' field='${field}' />
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="col2">
        <pmfn:message key='list.sort.direction' /><br/>
        <select id="dialogDesc" name="desc">
            <option ${(ctx.entityContainer.list.sort.desc)?'selected':''} value="true"><pmfn:message key="list.sort.desc" /></option>
            <option ${(not ctx.entityContainer.list.sort.desc)?'selected':''}  value="false"><pmfn:message key="list.sort.asc" /></option>
        </select>
    </div>
</div>
