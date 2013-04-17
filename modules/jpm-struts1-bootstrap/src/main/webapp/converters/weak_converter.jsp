<%@include file="../inc/inc-full.jsp" %>
<c:if test="${param.showbutton}">
    <a id="weak${param.f}" class="btn" href="${pmfn:url(ctx.pmsession, 'list.do?pmid='.concat(param.weakid))}"><i class=" ${param.buttonstyle}"></i>&nbsp;<pmfn:message key="${param.buttontext}" /></a><br/>
</c:if>
<c:if test="${param.showlist}">
    <div id="weak${param.f}-list">
        <img alt="..." src="${es.templatePath}/images/loading.gif"/>
    </div>
    <script type="text/javascript">
        PM_register(function(){
            $.ajax({
                url: "${pmfn:plainUrl(ctx.pmsession,'weakList.do?weakid='.concat(param.weakid).concat("&entityId=").concat(ctx.entity.id).concat("&field=").concat(param.f))}",
                dataType:"text",
                context: document.body,
                success: function(data){
                    $("#weak${param.f}-list").html(data).find("table").removeClass().addClass("table table-bordered table-condensed table-striped");;
                }
            });
        });
    </script>
</c:if>