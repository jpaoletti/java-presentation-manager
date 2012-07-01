<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="index.jsp"><pmfn:message key="${pm.title}"/></a>
            <div class="nav-collapse">
                <c:if test="${not empty ctx.pmsession}">
                    <pmfn:menu pmsession="${ctx.pmsession}"/>
                </c:if>
                <%@include file="/WEB-INF/jsp/user-menu.jsp" %>
            </div>
        </div>
    </div>
</div>