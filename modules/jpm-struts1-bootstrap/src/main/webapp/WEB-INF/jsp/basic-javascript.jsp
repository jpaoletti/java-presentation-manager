<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<script type="text/javascript">
    var contextPath = "${es.context_path}";
    var templatePath = "${es.templatePath}";
    PM_register(function(){
        <c:forEach var="message" items="${ctx.messages}">
            <c:if test="${message.systemScoped}"> msg_system.push(<pm:pm-message message="${message}"/>);</c:if>
            <c:if test="${message.entityScoped}"> msg_entity.push(<pm:pm-message message="${message}"/>);</c:if>
            <c:if test="${message.fieldScoped}">  msg_field.push (<pm:pm-message message="${message}"/>);</c:if>
        </c:forEach>
    });
</script>