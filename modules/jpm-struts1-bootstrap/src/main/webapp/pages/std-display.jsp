<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <pm:std-header ctx="${ctx}" />
    <c:if test="${not empty ctx.selected}">
        <div class="row-fluid">
            <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="${ctx.map.editable}" resetable="true">
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                    <div id="control-group-${field.id}" class="control-group">
                        <label class="control-label" for="f_${field.id}">${field.title}</label>
                        <div class="controls">
                            <pmfn:converted-item ctx="${ctx}" field="${field}" />
                            <c:if test="${not empty field.tooltip}">
                                <p class="help-block">${field.tooltip}</p>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </pm:std-form>
        </div>
    </c:if>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
</pm:page>