<%@tag description="" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<div id="topmenu">
    <ul>
        <li><a href="${es.context_path}/index.jsp" id="topmenu1"><pmfn:message key="home"/></a></li>
        <li><a href="mailto:${pm.contact}" id="topmenu3" accesskey="3" title="<pmfn:message key="contact"/>"><pmfn:message key="contact"/></a></li>
        <c:if test="${pm.hideableHeader}">
            <li><button id="btnColapseExpand">&nbsp;</button></li>
        </c:if>
    </ul>
</div>
<c:if test="${pm.hideableHeader}">
    <script type="text/javascript">
        var cookie_name='header_expanded';
        function setCookie(b){
            jQuery.cookie(cookie_name, b, {expires: 100});
        }
        function initBtn(){
            if(jQuery.cookie(cookie_name) == null ){
                setCookie(true);
            };
            if(jQuery.cookie(cookie_name) != 'true'){
                $("#header").hide();
            }
        }
        PM_register(function(){
            initBtn();
            $("#btnColapseExpand").button({
                text:false,
                icons:{primary:"ui-icon-carat-2-n-s"}
            }).click(function(){
                var header = $("#header");
                header.slideToggle(400, function () {
                    setCookie(header.is(":visible"));
                });
            });
        });
    </script>
</c:if>