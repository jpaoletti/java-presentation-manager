<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../../inc/inc-full.jsp" %>
<table id="list" class="display" >
    <thead>
        <tr>
            <c:forEach var="field" items="${pmfn:displayedFields(ctx.user, ctx.map.weak, 'list')}">
                <th scope="col" style="width:${field.width}px;" ><pm:field-name field="${field}" /></th>
            </c:forEach>
        </tr>
    </thead>
    <tbody id="list_body" >
        <c:forEach var="item" items="${ctx.map.list}" >
            <tr ${(ctx.map.addInstanceId=='true')?"instance-id='".concat(pmfn:instanceId(ctx, ctx.map.weak, item)).concat("'"):""}>
                <c:forEach var="field" items="${pmfn:displayedFields(ctx.user, ctx.map.weak, 'list')}">
                    <td class="align-${field.align}">
                        <pmfn:converted-item ctx="${ctx}" field="${field}" item="${item}" operation="${ctx.map.woperation}" entityContainer="${ctx.map.weakContainer}" />
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </tbody>
</table>