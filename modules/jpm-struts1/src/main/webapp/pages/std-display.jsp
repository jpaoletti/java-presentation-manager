<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <pm:std-header ctx="${ctx}" />
    <c:if test="${not empty ctx.selected}">
        <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="${ctx.map.editable}" resetable="true">
            <table class="std-table">
                <tbody id="list_body" >
                    <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                        <tr>
                            <th scope="row">
                                <pm:field-name field="${field}" />
                            </th>
                            <td>
                                <pmfn:converted-item ctx="${ctx}" field="${field}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </pm:std-form>
    </c:if>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
</pm:page>