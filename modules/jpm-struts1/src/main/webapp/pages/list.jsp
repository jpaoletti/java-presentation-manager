<%@include file="../inc/inc-full.jsp" %>
<bean:define id="operation" name="ctx" property="operation" toScope="request"/>
<bean:define id="PMLIST"    name="ctx" property="entityContainer.list" toScope="request"/>
<bean:define id="contents"  name="ctx" property="entityContainer.list.contents" type="java.util.List<Object>" toScope="request"/>
<pm:page title="list">
    <div class="boxed">
        <pm:std-header ctx="${ctx}" operations="${ctx.entityContainer.list.operations}" />
        <form action="${es.context_path}/list.do" method="GET" class="listform" id="listform">
            <input type="hidden" name="pmid" value="${entity.id}" />
            <script type="text/javascript" charset="utf-8">
                var pmid = "${entity.id}";
                var searchable = "${ctx.entityContainer.list.searchable}" == "true";
                var sortable = false;
                var paginable = false;
                var texts = new Array(
                "<pmfn:message key='list.search.all' />" ,
                "<pmfn:message key='list.first' />" ,
                "<pmfn:message key='list.last' />" ,
                "<pmfn:message key='list.next' />" ,
                "<pmfn:message key='list.previous' />" ,
                "<pmfn:message key='list.info' />" ,
                "<pmfn:message key='list.info.empty' />" ,
                "<pmfn:message key='list.info.filtered' />" ,
                "<pmfn:message key='pm.struts.list.rpp' />" ,
                "<pmfn:message key='list.processing' />" ,
                "<pmfn:message key='list.zero.records' />"
            );
            </script>
            <script type="text/javascript" charset="utf-8" src="${es.context_path}/js/list.js"></script>

            <div id="list-container">
                <div id="example_wrapper" class="dataTables_wrapper">
                    <jsp:include page="list-table.jsp" />
                    <jsp:include page="list-pagination.jsp" />
                    <jsp:include page="list-sort.jsp" />
                </div>
            </div>
            <div class="entity_message_container_${entity.id}"></div>
            <script type="text/javascript">
                PM_register(function(){
                    $("#page").keydown(function(event){
                        if(event.keyCode == 13)
                            this.form.submit();
                    });
                    $('#operationsort').addClass('jqModal');
                    $('#sort_page').jqm({onShow:function(hash){
                            hash.w.css('opacity',0.88).show();
                        }});
                });
            </script>
        </form>
    </div>
</pm:page>