<%@include file="../inc/inc-full.jsp" %>
<%
    Entity weak = WeakConverter.getEntity(ctx);
    Collection listv = WeakConverter.getCollection(ctx);
    request.setAttribute("weak", weak);
    request.setAttribute("woperation", weak.getOperations().getOperation("list"));
    request.setAttribute("contents", listv);
%>
<c:if test="${param.showbutton}">
    <a href="${es.context_path}/list.do?pmid=${param.weakid}" class='button edit' > &nbsp;&nbsp; <pmfn:message key="${param.buttontext}" /></a>
</c:if>
<c:if test="${param.showlist}">
    <div class="boxed">
        <table id="list" class="display" >
            <thead>
                <tr>
                    <c:forEach var="field" items="${pmfn:displayedFields(weak, 'list')}">
                        <th scope="col" style="width:${field.width}px;" ><pm:field-name entity="${weak}" field="${field}" /></th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody id="list_body" >
                <c:forEach var="item" items="${contents}" >
                    <tr>
                        <c:forEach var="field" items="${pmfn:displayedFields(weak, 'list')}">
                            <td align="text-align:${field.align};">
                                <pm:converted-item es="${es}" operation="${woperation}" entity="${weak}" item="${item}" field="${field}" />
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>