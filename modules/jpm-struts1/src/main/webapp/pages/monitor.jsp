<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="titles.monitor">
    <div class="boxed">
        <h2 class="title">${monitor.title}&nbsp;${operation.title}</h2>
    </div>
    <div id="con" class="boxed monitor_window monitor_${monitor.id}">
        <div id='line_container' ></div>
    </div>
    <script src="../js/jquery-plugin-arte.js" type="text/javascript"></script>
    <script type="text/javascript">
        PM_register(function(){
            $.arte({'ajax_url':'${es.context_path}/monitor.do?pmid=${monitor.id}&continue=true', 'on_success':update_field, 'time':'${monitor.delay}' }).start();
        });
        function update_field(data){
            var cleanup = ${monitor.cleanup};
            if(data.trim().length > 0){
                var res =  "<pre style='WHITE-SPACE: pre'>";
                if(!cleanup) res = res+$("#line_container").html();
                res=res+data+"</pre>";
                $("#line_container").html(res);
                $("#con").animate({ scrollTop: $("#con").attr("scrollHeight") - $('#con').height() }, 1000);
            }
        }
    </script>
</pm:page>