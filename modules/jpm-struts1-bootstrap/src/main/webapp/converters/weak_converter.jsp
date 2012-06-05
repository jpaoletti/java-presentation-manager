<%@include file="../inc/inc-full.jsp" %>
<c:if test="${param.showbutton}">
    <a id="weak${param.f}" class="btn" href="${pmfn:url(ctx.pmsession, 'list.do?pmid='.concat(param.weakid))}"><i class="icon-th-list"></i>&nbsp;<pmfn:message key="${param.buttontext}" /></a><br/>
</c:if>
<c:if test="${param.showlist}">
    <table id="list" class="table table-bordered table-condensed table-striped" >
        <thead>
            <tr>
                <c:forEach var="field" items="${pmfn:displayedFields(ctx.map.weak, 'list')}">
                    <th scope="col" style="width:${field.width}px;" ><pm:field-name field="${field}" /></th>
                </c:forEach>
            </tr>
        </thead>
        <tbody id="list_body" >
            <c:forEach var="item" items="${ctx.fieldValue}" >
                <tr>
                    <c:forEach var="field" items="${pmfn:displayedFields(ctx.map.weak, 'list')}">
                        <td align="text-align:${field.align};">
                            <pmfn:converted-item ctx="${ctx}" field="${field}" item="${item}" operation="${ctx.map.woperation}" entityContainer="${ctx.map.weakContainer}" />
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>