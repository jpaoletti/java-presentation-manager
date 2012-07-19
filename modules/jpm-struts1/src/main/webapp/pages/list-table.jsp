<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<table id="list" class="display" >
    <thead>
        <tr>
            <th scope="col" class="jpm-list-operation-col" style="width:${ctx.entityContainer.list.operationColWidth}">&nbsp;</th>
            <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><c:if test="${not empty field.width}"><th scope="col" id="col_${field.id}" style='width:${field.width}px;'></c:if><c:if test="${empty field.width}"><th scope="col" id="col_${field.id}" ></c:if><pm:field-name field="${field}" /></th></c:forEach>
        </tr>
    </thead>
    <tbody id="list_body" >
        <c:forEach var="item" items="${contents}" varStatus="status" >
            <tr class="${pmfn:highlight(entity,null,item,null)}">
                <th scope="row" class="jpm-list-operation-bar">
                    ${pmfn:itemCheckbox(ctx,contents,item)}${pmfn:rowNumber(ctx.entityContainer.list, contents,item)}${pmfn:listItemOperations(ctx, contents, item)}
                </th>
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><td class="align-${field.align}"><pmfn:converted-item ctx="${ctx}" operation="${operation}" item="${item}" field="${field}" /></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <c:if test="${ctx.entityContainer.list.searchable}">
            <tr>
                <th id="first_footer"></th>
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                    <th>
                        <input type="text" 
                               name="search_${field.title}" 
                               placeholder="${pmfn:message('list.input.search')}"
                               class="search_init"
                               />
                    </th>
                </c:forEach>
            </tr>
        </c:if>
    </tfoot>
</table>