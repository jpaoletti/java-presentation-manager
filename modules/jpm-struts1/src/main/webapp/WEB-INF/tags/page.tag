<%@ tag description="This tag encapsulates a standard html page" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title><pmfn:message key="${pm.title}"/> - <pmfn:message key="${title}"/></title>
        <link href="${es.context_path}/templates/${pm.template}/all.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" href="${es.context_path}/templates/${pm.template}/img/favicon.ico" />
        <script type="text/javascript" src="${es.context_path}/js/jquery-1.6.4.min.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jqueryslidemenu.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/misc.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.modal.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.center.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery.hotkeys.js"></script>
        <script type="text/javascript" src="${es.context_path}/js/jquery-ui-1.8.16.custom.min.js"></script>
    </head>
    <body class="${bodyClass}">
        <% try {%>
        <jsp:doBody />
        <% } catch (Exception e) {
            jpaoletti.jpm.core.PresentationManager.getPm().error(e);
        %>
        <pmfn:message key="pm.page.error"/>
        <%}%>
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
                        this();
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
                });
        </script>
    </body>
</html>