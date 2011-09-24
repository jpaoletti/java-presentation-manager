<%@include file="../inc/inc-full.jsp" %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <c:if test="${not empty ctx.selected}">
            <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="${ctx.map.editable}" resetable="true">
                <table class="std-table">
                    <tbody id="list_body" >
                        <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                            <tr>
                                <th scope="row">
                                    <pm:field-name entity="${entity}" field="${field}" />
                                </th>
                                <td>
                                    <pmfn:converted-item ctx="${ctx}" field="${field}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2">
                                <div class="entity_message_container_${entity.id}" />
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </pm:std-form>
        </c:if>
        <div class="entity_message_container_${entity.id}" />
        <div class="message_container" />
    </div>
</pm:page>