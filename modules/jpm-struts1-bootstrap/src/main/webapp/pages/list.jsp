<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<bean:define id="operation" name="ctx" property="operation" toScope="request"/>
<bean:define id="PMLIST"    name="ctx" property="entityContainer.list" toScope="request"/>
<bean:define id="contents"  name="ctx" property="map.listContents" type="java.util.List<Object>" toScope="request"/>
<pm:page title="titles.list">
    <script type="text/javascript" src="${es.context_path}/js/jquery.dataTables.js"></script>
    <pm:std-header ctx="${ctx}" operations="${ctx.entityContainer.list.operations}" />
    <form action="${pmfn:plainUrl(ctx.pmsession, 'list.do')}" method="GET" class="listform" id="listform">
        <input type="hidden" name="pmid"  value="${entity.id}" />
        <input type="hidden" name="order" value="${ctx.entityContainer.list.sort.fieldId}" />
        <input type="hidden" name="desc"  value="${ctx.entityContainer.list.sort.desc}" />
        <div id="list-container">
            <div id="list-operation-main-list">
                <jsp:include page="list-table.jsp" />
                <jsp:include page="list-sort.jsp" />
            </div>
        </div>
        <div class="entity_message_container_${entity.id}"></div>
    </form>
    <script type="text/javascript" charset="utf-8" src="${es.context_path}/js/list.js.jsp?pmid=${entity.id}&searchable=${ctx.entityContainer.list.searchable}"></script>
</pm:page>
