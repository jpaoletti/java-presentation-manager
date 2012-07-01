<%@include file="../inc/inc-full.jsp"  %>
<pm:page title="titles.${ctx.operation.id}">
    <script type="text/javascript" src="${es.context_path}/js/pwdwidget.js"></script>
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
            <pm:std-generic-form action="profile.do" editable="true" resetable="false" >
                <fieldset>
                    <div class="control-group">
                        <label class="control-label" for="input01"><pmfn:message key="profile.edit.username" /></label>
                        <div class="controls">
                            <input value="${pmsession.user.username}" disabled="disabled"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="name"><pmfn:message key="profile.edit.name" /></label>
                        <div class="controls">
                            <input name="name" value="${pmsession.user.name}" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email"><pmfn:message key="profile.edit.mail"/></label>
                        <div class="controls">
                            <input name="email" value="${pmsession.user.email}" placeholder="${pmfn:message('profile.edit.mail')}" />
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><pmfn:message key="profile.edit.picture" /></label>
                        <div class="controls">
                            <a href="http://gravatar.com"><pmfn:message key="profile.change.picture"/></a>
                        </div>
                    </div>
                </fieldset>
            </pm:std-generic-form>
        </div>
        <div class="tab-pane" id="password">
            <img src="${es.templatePath}/img/profile-password.png" alt="${pmfn:message('profile.title.changepass')}" /><br/>
            <pm:std-generic-form action="changepassword.do" editable="true" resetable="false" >
                <div class="control-group">
                    <label class="control-label"><pmfn:message key="profile.chpass.actual" /></label>
                    <div class="controls">
                        <input type="password" name="actual" id="actual" value="">
                    </div>
                </div>
                <div id="d_newpass" class="control-group">
                    <label class="control-label"><pmfn:message key="profile.chpass.newpass" /></label>
                    <div class="controls">
                        <div class='pwdwidgetdiv' id='thepwddivnewpass'></div>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><pmfn:message key="profile.chpass.newrep" /></label>
                    <div class="controls">
                        <input type="password" name="newrep" id="newrep" value="">
                    </div>
                </div>
            </pm:std-generic-form>
        </div>
    </div>
    <div class="entity_message_container_${entity.id}"></div>
    <div class="message_container"></div>
    <script  type="text/javascript" >
        var pwdwidget = new PasswordWidget('thepwddivnewpass','newpass');
        pwdwidget.txtShow = '${pmfn:message('pm.converter.password_converter.show')}';
        pwdwidget.txtMask = '${pmfn:message('pm.converter.password_converter.mask')}';
        pwdwidget.txtGenerate = '${pmfn:message('pm.converter.password_converter.generate')}';
        pwdwidget.txtWeak='${pmfn:message('pm.converter.password_converter.weak')}';
        pwdwidget.txtMedium='${pmfn:message('pm.converter.password_converter.medium')}';
        pwdwidget.txtGood='${pmfn:message('pm.converter.password_converter.good')}';
        pwdwidget.MakePWDWidget();
    </script>
</pm:page>