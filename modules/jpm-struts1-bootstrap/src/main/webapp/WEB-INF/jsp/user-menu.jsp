<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
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