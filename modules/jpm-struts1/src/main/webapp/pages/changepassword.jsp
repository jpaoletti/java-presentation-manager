<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <div id="add" class="boxed">
        <pm:std-header ctx="${ctx}" />
        <pm:std-form contextPath="${es.context_path}" entity="${ctx.entity}" operation="${ctx.operation}" editable="true" resetable="false">
            <table class="std-table">
                <tbody id="list_body" >
                    <tr>
                        <th scope="row"><pmfn:message key="chpass.actual" /></th>
                        <td><input type="password" name="actual" id="actual" value=""></td>
                    </tr>
                    <tr>
                        <th scope="row" ><pmfn:message key="chpass.newpass" /></th>
                        <td><input type="password" name="newpass" id="newpass" value=""></td>
                    </tr>
                    <tr>
                        <th scope="row"><pmfn:message key="chpass.newrep"/></th>
                        <td><input type="password" name="newrep" id="newrep" value=""></td>
                    </tr>
                </tbody>
            </table>
        </pm:std-form>
        <div class="entity_message_container_${entity.id}"></div>
        <div class="message_container"></div>
    </div>
</pm:page>