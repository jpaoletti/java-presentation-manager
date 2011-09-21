<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    if(typeof saclists == "undefined") var saclists = new Array();
    if(typeof sacupds == "undefined") var sacupds = new Array();
    saclists["${param.f}"] = jQuery.parseJSON( '${ctx.map.json_list}' );
    sacupds["${param.f}"] = function(){
        $("#f_${param.f}").contents().each(function(){$(this).remove()});
        var list = saclists["${param.f}"];
        var j = 0;
        var filter = "";
        if(typeof $("#search_${param.f}").val() != "undefined"){
            filter=$("#search_${param.f}").val();
        }
        for(i=0; i < list.length ; i++){
            pr = list[i];
            var match = pr.text.search(new RegExp(filter, "i"));
            if((filter.length >= ${param.min_search_size} && match >= 0) || list[i].selected){
                $("#f_${param.f}").get(0)[j]= new Option(list[i].text, list[i].id, false, list[i].selected);
                j++;
            }
        }
    };
</script>
<c:if test="${param.show_search}">
    <input type="text" id="search_${param.f}" size="7" /> &nbsp;
    <script type="text/javascript">$("#search_${param.f}").change(sacupds["${param.f}"]);</script>
</c:if>
<select size="1" id="f_${param.f}" name="f_${param.f}"></select>
<script type="text/javascript">sacupds["${param.f}"]();</script>
