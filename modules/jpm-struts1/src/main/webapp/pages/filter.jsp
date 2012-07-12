<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.filter">
    <pm:std-header ctx="${ctx}" />
    <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="true" resetable="true">
        <table class="std-table">
            <tbody id="list_body" >
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                    <tr>
                        <th scope="row">
                            <pm:field-name field="${field}" />
                        </th>
                        <td>
                            <pm:filter-operations field_id="${field.id}" filter="${ctx.entityContainer.filter}" />
                        </td>
                        <td>
                            <pmfn:converted-item ctx="${ctx}" field="${field}" fieldValue="${ctx.entityContainer.filter.filterValues[field.id][0]}"/>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3">
                        <div class="entity_message_container_${entity.id}"/>
                    </td>
                </tr>
            </tfoot>
        </table>
    </pm:std-form>
</pm:page>