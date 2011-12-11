<%@include file="../inc/inc-full.jsp"  %>
<%-- Standard display page for an entity instance item --%>
<pm:page title="titles.${ctx.operation.id}">
    <div id="add" class="boxed">
        <pm:std-generic-header ctx="${ctx}" title="profile.title"/>
        <h3><pmfn:message key="profile.title.personal"/></h3>
        <pm:std-generic-form action="${es.context_path}/profile.do" editable="true" resetable="true" >
            <table class="std-table">
                <tbody>
                    <tr>
                        <th scope="row" ><pmfn:message key="profile.edit.username" /></th>
                        <td>${pmsession.user.username}</td>
                    </tr>
                    <tr>
                        <th scope="row" ><pmfn:message key="profile.edit.name" /></th>
                        <td><input name="name" value="${pmsession.user.name}" /></td>
                    </tr>
                    <tr>
                        <th scope="row"><pmfn:message key="profile.edit.mail"/></th>
                        <td><input name="email" value="${pmsession.user.email}" /></td>
                    </tr>
                    <tr>
                        <th scope="row"><pmfn:message key="profile.edit.picture" /></th>
                        <td>
                            <img src="${pmsession.user.gravatar}?d=mm" alt="gravatar" /><br/>
                            <a href="http://gravatar.com"><pmfn:message key="profile.change.picture"/></a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </pm:std-generic-form>
        <h3><pmfn:message key="profile.title.changepass" /></h3>
        <pm:std-generic-form action="${es.context_path}/changepassword.do" editable="true" resetable="true" >
            <table class="std-table">
                <tbody>
                    <tr>
                        <th scope="row"><pmfn:message key="profile.chpass.actual" /></th>
                        <td><input type="password" name="actual" id="actual" value=""></td>
                    </tr>
                    <tr>
                        <th scope="row" ><pmfn:message key="profile.chpass.newpass" /></th>
                        <td><input type="password" name="newpass" id="newpass" value=""></td>
                    </tr>
                    <tr>
                        <th scope="row"><pmfn:message key="profile.chpass.newrep"/></th>
                        <td><input type="password" name="newrep" id="newrep" value=""></td>
                    </tr>
                </tbody>
            </table>
        </pm:std-generic-form>
        <div class="entity_message_container_${entity.id}"></div>
        <div class="message_container"></div>
    </div>
</pm:page>