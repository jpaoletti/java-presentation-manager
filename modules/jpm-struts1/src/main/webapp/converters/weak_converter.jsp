<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/inc-full.jsp" %>
<c:if test="${param.showbutton}">
    <button id="weak${param.f}" class="button"><pmfn:message key="${param.buttontext}" /></button>
    <script type="text/javascript">
        PM_register(function(){
            $("#weak${param.f}").button({
                icons:{primary:'ui-icon-extlink'}
            }).click(function(){
        ${pmfn:urlcp(ctx.pmsession, 'list.do?pmid='.concat(param.weakid), false, null )};
                });
            });
    </script>
</c:if>
<c:if test="${param.showlist}">
    <div id="weak${param.f}-list"></div>
    <script type="text/javascript">
        PM_register(function(){
            ("#weak${param.f}-list").load("${pmfn:plainUrl(ctx.pmsession,'weakList.do?weakid='.concat(param.weakid).concat("&entityId=").concat(ctx.entity.id).concat("&field=").concat(param.f))}");
        });
    </script>
</c:if>