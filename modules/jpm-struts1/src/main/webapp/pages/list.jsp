<%@include file="../inc/inc-full.jsp" %>
<bean:define id="operation" name="ctx" property="operation" toScope="request"/>
<bean:define id="PMLIST"    name="ctx" property="entityContainer.list" toScope="request"/>
<bean:define id="contents"  name="ctx" property="entityContainer.list.contents" type="java.util.List<Object>" toScope="request"/>
<pm:page title="list">
    <div class="boxed">
        <pm:std-header ctx="${ctx}" operations="${ctx.entityContainer.list.operations}" />
        <form action="${es.context_path}/list.do" method="GET" class="listform" id="listform">
            <input type="hidden" name="pmid" value="${entity.id}" />
            <div id="list-container">
                <div id="example_wrapper" class="dataTables_wrapper">
                    <jsp:include page="list-table.jsp" />
                    <jsp:include page="list-pagination.jsp" />
                    <jsp:include page="list-sort.jsp" />
                </div>
            </div>
            <div class="entity_message_container_${entity.id}"></div>
        </form>
    </div>
    <script type="text/javascript" charset="utf-8" src="${es.context_path}/js/list.js.jsp?pmid=${entity.id}&searchable=${ctx.entityContainer.list.searchable}"></script>
</pm:page>
