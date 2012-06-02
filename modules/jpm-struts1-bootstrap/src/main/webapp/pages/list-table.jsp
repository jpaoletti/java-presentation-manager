<%@include file="../inc/inc-full.jsp" %>
<table class="table table-bordered table-condensed table-striped">
    <thead>
        <tr>
            <c:if test="${ctx.entityContainer.list.showRowNumber}"><th class="row-number-col">#</th></c:if>
            <th scope="col" class="jpm-list-operation-col center" style="width:${ctx.entityContainer.list.operationColWidth}"><i class="icon-cog"></i></th>
            <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><c:if test="${not empty field.width}"><th scope="col" id="col_${field.id}" style='width:${field.width}px;'></c:if><c:if test="${empty field.width}"><th scope="col" id="col_${field.id}" ></c:if><pm:field-name field="${field}" /></th></c:forEach>
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
                        ${pmfn:listItemOperations(ctx, contents, item)}
                        </div>
                    </label>
                </th>
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><td align="${field.align}"><pmfn:converted-item ctx="${ctx}" operation="${operation}" item="${item}" field="${field}" /></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <c:if test="${ctx.entityContainer.list.showRowNumber}"><th class="row-number-col"></th></c:if>
            <td colspan="${fn:length(pmfn:displayedFields(entity, ctx.operation.id))+1}">
                <jsp:include page="list-pagination.jsp" />
            </td>
        </tr>
    </tfoot>
</table>
<script type="text/javascript">
    PM_register(function(){
        $(".ui-list-icon-container").each(function(){
            $(this)
            .addClass("btn")
            .removeClass("ui-list-icon-container");
        });
    });
</script>