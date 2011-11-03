<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    if(typeof sacupds == "undefined") var sacupds = new Array();
    PM_register(function(){
        sacupds["${param.f}"] = function(){
            var selectedOption = new Option("${ctx.map._selected_value}", "${ctx.map._selected_id}", false, true);
            $("#done_${param.f}").hide();
            $("#loading_${param.f}").show();
            $("#f_${param.f}").contents().each(function(){$(this).remove()});
            var j = 0;
            var filter = $("#search_${param.f}").val();
            <c:if test="${ctx.map._min_search_size > 0 }">
            $("#f_${param.f}").get(0)[j]= selectedOption; j++;
            $($("#f_${param.f}").get(0)).append("<option disabled='disabled'>-------------------------</option>"); j++;
            </c:if>
            if(${ctx.map._with_null}){
                $("#f_${param.f}").get(0)[j]= new Option("","-1", false, false); j++;
            }
            if(filter.length >= ${ctx.map._min_search_size}){
                jQuery.getJSON("${es.context_path}/get_list.do?entity=${ctx.map._entity}&filter_class=${ctx.map._filter}&filter="+filter+"&id=${ctx.map._id}&display=${ctx.map._display}",function(list){
                    jQuery.each(list, function (i, item){                    
                        $("#f_${param.f}").get(0)[j]= new Option(list[i].value, list[i].key, false, "${ctx.map._selected_id}"==list[i].key); j++;
                    });
                    $("#loading_${param.f}").hide();
                    $("#done_${param.f}").show();
                });
            }else{
                $("#loading_${param.f}").hide();
                $("#done_${param.f}").show();
            }
        }
    });
</script>
<c:if test="${ctx.map._min_search_size == 0 }">
    <input type="hidden" id="search_${param.f}" value="" />
</c:if>
<c:if test="${ctx.map._min_search_size > 0 }">
    <input type="text" id="search_${param.f}" size="7" class="object-converter-search"/>
    <script type="text/javascript">
        PM_register(function(){
            $("#search_${param.f}").keyup(sacupds["${param.f}"]); 
        });
    </script>
</c:if>
<div id="loading_${param.f}"><img alt="..." src="${es.templatePath}/images/loading.gif"/></div>
<span id="done_${param.f}" style="display: none;">
    <select size="1" id="f_${param.f}" name="f_${param.f}" class="object-converter-select object-converter-select-${ctx.map._min_search_size}"></select>
</span>
<script type="text/javascript">
    PM_register(function(){
        sacupds["${param.f}"]();
    });
</script>