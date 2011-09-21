<%@include file="../inc/tag-libs.jsp" %>
<div id="sort_page" class="jqmWindow">
    <pmfn:message key='list.sort.field' /> <br/>
    <select name="order" onchange="this.form.submit();" >
        <c:forEach var="field" items="${pmfn:displayedFields(entity, 'sort')}">
            <option value="${field.id}" ${(ctx.entityContainer.list.sort.fieldId==field.id)?'selected':''}>
                <pm:field-name entity='${entity}' field='${field}' />
            </option>
        </c:forEach>
    </select>
    <select name="desc" onchange="this.form.submit();">
        <option ${(ctx.entityContainer.list.sort.desc)?'selected':''} value="true"><pmfn:message key="list.sort.desc" /></option>
        <option ${(not ctx.entityContainer.list.sort.desc)?'selected':''}  value="false"><pmfn:message key="list.sort.asc" /></option>
    </select><br/>
</div>
