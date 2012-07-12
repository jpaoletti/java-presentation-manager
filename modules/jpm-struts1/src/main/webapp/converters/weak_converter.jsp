<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<c:if test="${param.showbutton}">
    <button id="weak${param.f}" class="button"><pmfn:message key="${param.buttontext}" /></button>
    <script type="text/javascript">
        PM_register(function(){
            $("#weak${param.f}").button({
                icons:{primary:'ui-icon-extlink'}
            }).click(function(){
        ${pmfn:urlcp(ctx.pmsession, 'list.do?pmid='.concat(param.weakid), false, null )};
                });
            });
    </script>
</c:if>
<c:if test="${param.showlist}">
    <table id="list" class="display" >
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