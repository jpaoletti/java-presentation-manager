<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="titles.monitor">
    <h2 class="title">${monitor.title}&nbsp;${operation.title}</h2>
    <div id="con" class="boxed monitor_window monitor_${monitor.id}">
        <div id='line_container' ></div>
    </div>
    <script src="${es.context_path}/js/jquery-plugin-arte.js" type="text/javascript"></script>
    <script src="${es.context_path}/js/jquery.scrollTo-1.4.2-min.js" type="text/javascript"></script>
    <script type="text/javascript">
        PM_register(function(){
            $.arte({
                'ajax_url':'${pmfn:plainUrl(ctx.pmsession, '/monitor.do?continue=true&pmid='.concat(monitor.id))}', 
                'on_success': function (data){
                    var cleanup = ${monitor.cleanup};
                    if(data.trim().length > 0){
                        var res =  "";
                        if(!cleanup) res = res+$("#line_container").html();
                        res=res+data;
                        $("#line_container").html(res);
                    }
                    $.scrollTo("max",1000);
                }, 
                'time':'${monitor.delay}' 
            }).start();
        });
    </script>
</pm:page>