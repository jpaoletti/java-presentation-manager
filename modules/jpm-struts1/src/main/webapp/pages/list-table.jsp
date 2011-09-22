<%@include file="../inc/inc-full.jsp" %>
<table id="list" class="display" >
    <thead>
        <tr>
            <th scope="col" style="width:${ctx.entityContainer.list.operationColWidth}">&nbsp;</th>
            <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><c:if test="${not empty field.width}"><th scope="col" style='width:${field.width}px;'></c:if><c:if test="${empty field.width}"><th scope="col"></c:if><pm:field-name entity="${entity}" field="${field}" /></th></c:forEach>
        </tr>
    </thead>
    <tbody id="list_body" >
        <c:forEach var="item" items="${contents}" varStatus="status" >
            <tr class="${pmfn:highlight(entity,null,item,null)}">
                <td style="color:gray; white-space: nowrap;">
                    <c:if test="${ctx.entityContainer.list.hasSelectedScope}">
                        <input type="checkbox" id="selected_item" value="${fn:indexOf(contents,item)}" onchange="selectItem(this.value);" ${(fn:contains(ctx.entityContainer.selectedIndexes,fn:indexOf(contents,item)))?'checked':''} />
                    </c:if>
                    ${pmfn:rowNumber(ctx.entityContainer.list,item)}&nbsp;${pmfn:listItemOperations(ctx, contents, item)}
                </td>
                <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}"><td align="${field.align}"><pmfn:converted-item ctx="${ctx}" operation="${operation}" item="${item}" field="${field}" /></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <c:if test="${ctx.entityContainer.list.searchable}">
            <tr>
                <th><input type="hidden" name="search" class="search_init" /></th>
                    <c:forEach var="field" items="${pmfn:displayedFields(entity, ctx.operation.id)}">
                    <th><input type="text" name="search_<pm:field-name entity="${entity}" field="${field}" />" value="<pmfn:message key="list.input.search"/>" class="search_init" /></th>
                    </c:forEach>
            </tr>
        </c:if>
    </tfoot>
</table>
<script type="text/javascript" >
    function selectItem(i){
        $.ajax({ url: "selectItem.do?pmid="+"${pmid}"+"&idx="+i});
    }
    PM_register(function(){
        $(".confirmable_true").bind('click',function(){
            return confirm("<pmfn:message key='pm.operation.confirm.question' />");
        });
    });
</script>