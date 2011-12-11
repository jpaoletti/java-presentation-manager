<%@tag description="This tag encapsulates site header" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<div id="header">
    <div id="logo">
        <h1 class="title"><pmfn:message key="${pm.title}"/></h1>
        <h2 class="title"><pmfn:message key="${pm.subtitle}"/></h2>
    </div>
    <c:if test="${not empty pmsession.user}">
        <div id="userbox">
            <a href="javascript:loadPage('${es.context_path}/show.do?pmid=secuserprofile&identified=username:${pmsession.user.username}');">
                <img src='${pmsession.user.gravatar}?s=40&d=mm' alt="${pmsession.user.name}" title="${pmsession.user.name}"/>
            </a>
        </div>
    </c:if>
</div>
<c:if test="${pm.hideableHeader}">
    <script type="text/javascript">
        function setExpandCookie(b){
            jQuery.cookie("header_expanded", b, {expires: 100});
        }
        function initBtn(){
            if(jQuery.cookie("header_expanded") == null ){
                setExpandCookie(true);
            };
            if(jQuery.cookie("header_expanded") != 'true'){
                $("#header").hide();
            }
        }
        PM_register(function(){
            initBtn();
            $("#btnColapseExpand").click(function(){
                var header = $("#header");
                header.slideToggle(400, function () {
                    setExpandCookie(header.is(":visible"));
                    $(".index_layout_content").css('top', $("#full_header").height() + "px");
                });
            });
        });
    </script>
</c:if>