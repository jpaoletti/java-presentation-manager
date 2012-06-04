<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    if(typeof sacupds == "undefined") var sacupds = new Array();
    PM_register(function(){
        sacupds["${param.f}"] = function(){
            var field = "${param.f}";
            var selectedOption = new Option("${ctx.map._selected_value}", "${ctx.map._selected_id}", false, true);
            $("#done_"+field).hide();
            $("#loading_"+field).show();
            $("#f_"+field).contents().each(function(){$(this).remove()});
            var j = 0;
            var filter = $("#search_"+field).val();
            //<c:if test="${ctx.map._min_search_size > 0 }">
            $("#f_"+field).get(0)[j]= selectedOption; j++;
            $($("#f_"+field).get(0)).append("<option disabled='disabled'>-------------------------</option>"); j++;
            //</c:if>
            if(${ctx.map._with_null}){
                $("#f_"+field).get(0)[j]= new Option("","-1", false, false); j++;
            }
            if(filter.length >= ${ctx.map._min_search_size}){
                jQuery.getJSON("${ctx.map.jsonUrl}?filter=" + filter + "&relatedFieldValue=" + $("#f_${param.related}").val(),function(list){
                    jQuery.each(list, function (i, item){
                        $("#f_"+field).get(0)[j]= new Option(list[i].value, list[i].key, false, "${ctx.map._selected_id}"==list[i].key); j++;
                    });
                    $("#loading_"+field).hide();
                    $("#done_"+field).show();
                });
            }else{
                $("#loading_"+field).hide();
                $("#done_"+field).show();
            }
        }
    });
</script>
<c:if test="${ctx.map._min_search_size == 0 }">
    <input type="hidden" id="search_${param.f}" value="" />
</c:if>
<c:if test="${ctx.map._min_search_size > 0 }">
    <input type="text" id="search_${param.f}" size="7" class="object-converter-search span1"/>
    <script type="text/javascript">
        PM_register(function(){
            $("#search_${param.f}").keyup(function() {
                delay(sacupds["${param.f}"], 1000 );
            });
        });
    </script>
</c:if>
<c:if test="${not empty param.related}">
    <script type="text/javascript">
        PM_register(function(){
            $("#f_${param.related}").live('change', sacupds["${param.f}"]);
        });
    </script>
</c:if>
<div id="loading_${param.f}" class="object-converter-loading"><img alt="..." src="${es.templatePath}/images/loading.gif"/></div>
<span id="done_${param.f}" style="display: none;">
    <select size="1" id="f_${param.f}" name="f_${param.f}" class="object-converter-select object-converter-select-${ctx.map._min_search_size}"></select>
    <c:if test="${not empty param.add}">
        <script type="text/javascript" src="${es.context_path}/js/jquery/jquery.form.js"></script>
        <button id='object-converter-add-btn-${param.f}' type='button' class='btn object-converter-add-btn'><i class="ui-icon ui-icon-operation-add"></i></button>
    </c:if>
</span>
<script type="text/javascript">
    PM_register(function(){
        sacupds["${param.f}"]();
        //<c:if test="${not empty param.add}">
        $("#object-converter-add-btn-${param.f}").click(function(){
            popup("${param.add}");
            setTimeout(function(){
                $("#form_${param.oentity}_add").ajaxForm(function(){
                    $('#popup-modal').modal('hide');
                    sacupds["${param.f}"]();
                });
            }, 2100);
        });
        //</c:if>
    });
</script>