<%@include file="../inc/inc-full.jsp" %>
<c:if test="${param.showbutton}">
    <a href="${es.context_path}/list.do?pmid=${param.weakid}" class='button edit' > &nbsp;&nbsp; <pmfn:message key="${param.buttontext}" /></a>
</c:if>
<c:if test="${param.showlist}">
    <div class="boxed">
        <table id="list" class="display" >
            <thead>
                <tr>
                    <c:forEach var="field" items="${pmfn:displayedFields(ctx.map.weak, 'list')}">
                        <th scope="col" style="width:${field.width}px;" ><pm:field-name entity="${ctx.map.weak}" field="${field}" /></th>
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
    </div>
</c:if>