<%@ tag description="This tag encapsulates a standard html page" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="loading" required="false"  %>
<!DOCTYPE html>
<c:if test="${not empty pm}">
    <html>
        <head>
            <meta http-equiv="content-type" content="text/html; charset=utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1"> 
            <title><pmfn:message key="${pm.title}"/> - <pmfn:message key="${title}"/></title>
            <link href="${es.templatePath}/all.css" rel="stylesheet" type="text/css" />
            <link rel="shortcut icon" href="${es.templatePath}/img/favicon.ico" />
            <script type="text/javascript" src="${es.context_path}/js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/misc.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/menu.js"></script>
        </head>
        <body class="${bodyClass}">
            <div id="loading-div">
                <div class="mainLoading">
                    <img src="${es.templatePath}/images/main_loading.gif"  alt="..." />
                </div>
            </div>
            <div class="index_layout" id="page-container" style="display: none;">
                <%-- Paramter noHeader can be used for ajax page inclusion --%>
                <c:if test="${not param.noHeader}">
                    <div class="index_layout_header">
                        <div id="full_header"><pm:header /><pmfn:menu pmsession="${pmsession}" /></div>
                    </div>
                </c:if>
                <div class="index_layout_content">
                    <c:if test="${empty pmsession}">
                        <jsp:include page="pages/login.jsp" />
                    </c:if>
                    <c:if test="${not empty pmsession}">
                        <span id="bodySpan">
                            <% try {%>
                            <div class="content">
                                <jsp:doBody />
                            </div>
                            <% } catch (Exception e) {
                                jpaoletti.jpm.core.PresentationManager.getPm().error(e);
                            %>
                            <pmfn:message key="pm.page.error"/>
                            <%}%>
                        </span>
                    </c:if>
                </div>
            </div>
            <div id="confirmationDialog" title="<pmfn:message key='pm.operation.confirm.title' />" style="display: none;">
                <p><span class="ui-icon ui-icon-alert dialog-icon"></span>
                    <pmfn:message key='pm.operation.confirm.question' />
                </p>
            </div>

            <script type="text/javascript" src="${es.context_path}/js/jquery.dataTables.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/jquery.hotkeys.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/jquery-ui-1.8.16.custom.min.js"></script>
            <script type="text/javascript" src="${es.context_path}/js/jquery-ui-combobox.js"></script>
            <script type="text/javascript" src="js/expand.js"></script>
            <script type="text/javascript" src="js/jquery.cookie.js"></script>
            <script type="text/javascript">
                var msg_system = new Array();
                var msg_entity = new Array();
                var msg_field  = new Array();
                <c:forEach var="message" items="${ctx.messages}">
                    <c:if test="${message.systemScoped}"> msg_system.push(<pm:pm-message message="${message}"/>);</c:if>
                    <c:if test="${message.entityScoped}"> msg_entity.push(<pm:pm-message message="${message}"/>);</c:if>
                    <c:if test="${message.fieldScoped}">  msg_field.push (<pm:pm-message message="${message}"/>);</c:if>
                </c:forEach>
                    jQuery(document).ready(function() {
                        try{
                            ddsmoothmenu.init({
                                mainmenuid: "menu", //menu DIV id
                                orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
                                classname: 'ddsmoothmenu', //class added to menu's outer DIV
                                //customtheme: ["#1c5a80", "#18374a"],
                                contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
                            })
                            
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
                    
                            $( "#confirmationDialog" ).dialog({
                                modal: true, 
                                resizable: false,
                                bgiframe: true,
                                width: 300,
                                height: 200,
                                autoOpen: false
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
        </body>
    </html>
</c:if>
<c:if test="${empty pm}">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <title>Error</title>
        <style type="text/css" >
            #pm_error_div{ margin: 70px; padding: 40px; border: black solid thin; background-color: red; font-size: large; font-weight: bold; width:400px; }
            #error_img{ vertical-align:middle; float:left; width:100px; margin: 0px 20px 0px 0px; }
        </style>
    </head>
    <body>
        <div id="pm_error_div">
            <img alt="error" src="error.png" id="error_img">
            Presentation Manager detected some errors on initialization and was not
            able to start. Please check the logs .
        </div>
    </body>
</html>
</c:if>