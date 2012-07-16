<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<script type="text/javascript" src="${es.context_path}/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${es.context_path}/js/jquery.hotkeys.js"></script>
<script type="text/javascript" src="${es.context_path}/js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="${es.context_path}/js/jquery-ui-combobox.js"></script>
<script type="text/javascript" src="${es.context_path}/js/expand.js"></script>
<script type="text/javascript" src="${es.context_path}/js/jquery.cookie.js"></script>
<script type="text/javascript">
        var msg_system = new Array();
        var msg_entity = new Array();
        var msg_field = new Array();
        var contextPath = "${es.context_path}";
        <c:forEach var="message" items="${ctx.messages}">
        <c:if test="${message.systemScoped}"> msg_system.push(<pm:pm-message message="${message}"/>);</c:if>
        <c:if test="${message.entityScoped}"> msg_entity.push(<pm:pm-message message="${message}"/>);</c:if>
        <c:if test="${message.fieldScoped}">  msg_field.push (<pm:pm-message message="${message}"/>);</c:if>
        </c:forEach>
        jQuery(document).ready(function() {
        try{
            $("button.submit").click(function(){
                this.form.submit();
            });
            $("button.submit").button({
                icons: {
                    primary: "ui-icon-check"
                }
            });
            $("button.reset").click(function(){
                this.form.reset();
                return false;
            });
            $("button.reset").button({
                icons: {
                    primary: "ui-icon-arrowreturnthick-1-w"
                }
            });
            jQuery.each(PM_onLoadFunctions, function(){
                try{
                    this();
                }catch(e){
                    alert("Error: "+e);
                }
            });
                    
            jQuery.each(msg_system, function(){
                var cl = ".message_container";
                jQuery(cl).addClass("pm_message_"+this.type);
                jQuery(cl).html(this.text);
            });

            jQuery.each(msg_entity, function(){
                var cl = ".entity_message_container_"+this.entity;
                jQuery(cl).addClass("pm_message_"+this.type);
                jQuery(cl).html(this.text);
            });

            jQuery.each(msg_field, function(){
                var cl = ".field_message_container_"+this.entity+"_"+this.field;
                jQuery(cl).addClass("pm_message_"+this.type);
                jQuery(cl).parent().addClass("pm_message_"+this.type);
                jQuery(cl).html(this.text);
            });                        
        }finally{
            $("#loading-div").hide();
            $("#page-container").show();
        }
    });
</script>