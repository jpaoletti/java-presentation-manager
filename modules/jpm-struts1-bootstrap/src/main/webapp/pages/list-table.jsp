<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<table class="table table-bordered table-condensed table-striped">
    <thead>
        <tr>
    <c:if test="${ctx.entityContainer.list.showRowNumber}"><th class="row-number-col">#</th></c:if>
    <th scope="col" class="jpm-list-operation-col center" style="width:${ctx.entityContainer.list.operationColWidth}"><i class="icon-cog"></i></th>
    <c:forEach var="field" items="${pmfn:displayedFields(ctx.user, entity, ctx.operation.id)}"><c:if test="${not empty field.width}"><th scope="col" id="col_${field.id}" style='width:${field.width}px;'></c:if><c:if test="${empty field.width}"><th scope="col" id="col_${field.id}" ></c:if><pm:field-name field="${field}" tooltip="false" /></th></c:forEach>
</tr>
</thead>
<tbody id="list_body" >
<c:forEach var="item" items="${contents}" varStatus="status" >
    <tr class="${pmfn:highlight(entity,null,item,null)}">
    <c:if test="${ctx.entityContainer.list.showRowNumber}"><th class="row-number-col">${pmfn:rowNumber(ctx.entityContainer.list, contents,item)}</th></c:if>
    <th scope="row" class="jpm-list-operation-bar">
        <label class="${(ctx.entityContainer.list.hasSelectedScope)?'checkbox':''} inline clearfix">
            ${pmfn:itemCheckbox(ctx,contents,item)}
            <div class='btn-group'>
                <c:if test="${ctx.entityContainer.list.compactedOperations}">
                    <a class="btn btn-small dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-cog"></i> &nbsp;<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu operation-dropdown-menu">
                </c:if>
                ${pmfn:listItemOperations(ctx, contents, item)}
                <c:if test="${ctx.entityContainer.list.compactedOperations}">
                    </ul>
                </c:if>
            </div>
        </label>
    </th>
    <c:forEach var="field" items="${pmfn:displayedFields(ctx.user, entity, ctx.operation.id)}"><td class="align-${field.align}"><pmfn:converted-item ctx="${ctx}" operation="${operation}" item="${item}" field="${field}" /></td>
    </c:forEach>
    </tr>
</c:forEach>
</tbody>
<tfoot>
    <tr>
<c:if test="${ctx.entityContainer.list.showRowNumber}"><th class="row-number-col"></th></c:if>
<td colspan="${fn:length(pmfn:displayedFields(ctx.user, entity, ctx.operation.id))+1}">
    <jsp:include page="list-pagination.jsp" />
</td>
</tr>
</tfoot>
</table>
<script type="text/javascript">
    PM_register(function(){
        if(${ctx.entityContainer.list.compactedOperations}){
            $(".ui-list-icon-container").each(function(){
                $(this).append("&nbsp;"+$(this).attr("title")).wrap('<li />')
            })
        }else{
            $(".ui-list-icon-container").addClass("btn btn-small");
        }
        $(".ui-list-icon-container").removeClass("ui-list-icon-container");
    });
</script>