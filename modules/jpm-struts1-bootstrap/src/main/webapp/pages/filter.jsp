<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <pm:std-header ctx="${ctx}" />
    <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="${ctx.map.editable}" resetable="true">
        <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
            <div class="control-group">
                <label class="control-label" for="f_${field.id}">${field.title}</label>
                <div class="controls">
                    <pm:filter-operations field_id="${field.id}" filter="${ctx.entityContainer.filter}" />
                    <pmfn:converted-item ctx="${ctx}" field="${field}" fieldValue="${ctx.entityContainer.filter.filterValues[field.id][0]}" />
                </div>
            </div>
        </c:forEach>
    </pm:std-form>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
</pm:page>