<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<c:if test="${not empty ctx.pmsession}">
    <div class="avatar"><img src="${ctx.user.gravatar}?s=28&d=mm"/></div>
    <div class="pull-right btn-group user-box">
        <a class="btn" href="${pmfn:url(ctx.pmsession,'profile.do')}" title="Perfil">
            <i class="icon-user"></i>&nbsp;${ctx.user.name}
        </a>
        <a class="btn" href="mailto:${es.pm.contact}" title="${pmfn:message('jpm.contact.title')}">
            <i class="icon-envelope"></i>
        </a>
        <a class="btn" href="${pmfn:plainUrl(ctx.pmsession,'logout.do')}" title="Salir">
            <i class="icon-off"></i>
        </a>
    </div>
</c:if>
<c:if test="${empty ctx.pmsession}">
    <div class="pull-right login-box">
        <form class="form-inline" action="${es.context_path}/login.do" method="POST">
            <span class="message_container hide login-error"></span>
            <input id="username" name="username" type="text" class="input-small" placeholder="${pmfn:message('login.username')}">
            <input id="password" name="password" type="password" class="input-small" placeholder="${pmfn:message('login.password')}">
            <button type="submit" class="btn"><pmfn:message key='login.sign.in' />&nbsp;<i class="icon-arrow-right"></i></button>
        </form>
    </div>
</c:if>