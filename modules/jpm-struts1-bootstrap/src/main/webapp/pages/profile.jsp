<%@include file="../inc/inc-full.jsp"  %>
<pm:page title="titles.${ctx.operation.id}">
    <h2 class="title"><pmfn:message key="profile.title" /></h2>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#profile" data-toggle="tab">
                <i class="iconic user"></i>&nbsp;<pmfn:message key="profile.title.personal"/>
            </a>
        </li>
        <li>
            <a href="#password" data-toggle="tab">
                <i class="iconic key_stroke"></i>&nbsp;<pmfn:message key="profile.title.changepass" />
            </a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="profile">
            <img src="${pmsession.user.gravatar}?d=mm&s=100" alt="gravatar" /><br/>
            <pm:std-generic-form action="profile.do?finish=yes" editable="true" resetable="false" >
                <table class="std-table">
                    <tbody>
                        <tr>
                            <th scope="row" ><pmfn:message key="profile.edit.username" /></th>
                            <td><input value="${pmsession.user.username}" disabled="disabled"/></td>
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
                            <td><a href="http://gravatar.com"><pmfn:message key="profile.change.picture"/></a></td>
                        </tr>
                    </tbody>
                </table>
            </pm:std-generic-form>
        </div>
        <div class="tab-pane" id="password">
            <img src="${es.templatePath}/img/profile-password.png" alt="${pmfn:message('profile.title.changepass')}" /><br/>
            <pm:std-generic-form action="changepassword.do?finish=yes" editable="true" resetable="false" >
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
        </div>
    </div>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
</pm:page>
