<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    if(typeof sacupds == "undefined") var sacupds = new Array();
    sacupds["${param.f}"] = function(){
        var selectedOption = new Option("${ctx.map._selected_value}", "${ctx.map._selected_id}", false, true);
        $("#done_${param.f}").hide();
        $("#loading_${param.f}").show();
        $("#f_${param.f}").contents().each(function(){$(this).remove()});
        var j = 0;
        var filter = $("#search_${param.f}").val();
        $("#f_${param.f}").get(0)[j]= selectedOption; j++;
        $($("#f_${param.f}").get(0)).append("<option disabled='disabled'>-------------------------</option>"); j++;
        if(${ctx.map._with_null}){
            $("#f_${param.f}").get(0)[j]= new Option("","-1", false, false); j++;
        }
        if(filter.length >= ${ctx.map._min_search_size}){
            jQuery.getJSON("${es.context_path}/get_list.do?entity=${ctx.map._entity}&filter_class=${ctx.map._filter}&filter="+filter+"&id=${ctx.map._id}&display=${ctx.map._display}",function(list){
                for(i=0; i < list.length ; i++){
                    pr = list[i];
                    var match = pr.text.search(new RegExp(filter, "i"));
                    if( match >= 0){
                        $("#f_${param.f}").get(0)[j]= new Option(list[i].text, list[i].id, false, list[i].selected); j++;
                    }
                }
                $("#loading_${param.f}").hide();
                $("#done_${param.f}").show();
            });
        }else{
            $("#loading_${param.f}").hide();
            $("#done_${param.f}").show();
        }
    };
</script>
<input type="text" id="search_${param.f}" size="7" />
<div id="loading_${param.f}"><img alt="..." src="${es.templatePath}/images/loading.gif"/></div>
<span id="done_${param.f}" style="display: none;">
    <script type="text/javascript">$("#search_${param.f}").change(sacupds["${param.f}"]);</script>
    <select size="1" id="f_${param.f}" name="f_${param.f}"></select>
</span>
<script type="text/javascript">sacupds["${param.f}"]();</script>