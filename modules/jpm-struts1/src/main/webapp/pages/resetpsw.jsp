<%@include file="../inc/inc-full.jsp" %>
<pm:page title="titles.resetpsw">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="false" resetable="false">
            <table class="std-table">
                <tbody id="list_body" >
                    <tr>
                        <th scope="row"><pmfn:message key="pm.security.ui.generatedpsw.user" /></th>
                        <td>${ctx.map.username}</td>
                    </tr>
                    <tr>
                        <th scope="row" ><pmfn:message key="pm.security.ui.generatedpsw.psw" /></th>
                        <td>${ctx.map.generatedpsw}</td>
                    </tr>
                </tbody>
            </table>
        </pm:std-form>
        <div class="entity_message_container_${entity.id}"></div>
        <div class="message_container"></div>
    </div>
</pm:page>