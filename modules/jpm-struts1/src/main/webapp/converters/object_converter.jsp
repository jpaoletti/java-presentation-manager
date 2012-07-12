<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<div id="object-converter-${param.f}">
    <div class="object-converter-loading"><img alt="..." src="${es.templatePath}/images/loading.gif"/></div>
    <div class="object-converter-items">
        <input type="text" class="object-converter-search"/>
        <select id="f_${param.f}" name="f_${param.f}" class="object-converter-select object-converter-select-${ctx.map._min_search_size}"></select>
        <button type='button' class='btn object-converter-add-btn'><i class="ui-icon ui-icon-operation-add"></i></button>
    </div>
</div>
<script type="text/javascript">
    if(typeof sacupds == "undefined") var sacupds = new Array();
    PM_register(function(){
        $("#object-converter-${param.f}").objectConverter({
            "field"             : "${param.f}",
            'url'               : "${ctx.map.jsonUrl}",
            'selected_value'    : "${ctx.map._selected_value}",
            "selected_id"       : ${ctx.map._selected_id},
            "related"           : "${param.related}",
            "related_entity"    : "${param.oentity}",
            "search"            : "${ctx.map._search}"=="true"?true:false,
            "add"               : "${param.add}",
            "min_search_size"   : ${ctx.map._min_search_size},
            "with_null"         : "${ctx.map._with_null}"=="true"?true:false
        });
    });
</script>