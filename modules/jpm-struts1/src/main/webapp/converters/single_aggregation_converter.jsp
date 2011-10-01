<select size="1" id="f_${param.f}" name="f_${param.f}"></select>
<script type="text/javascript">
    PM_register(function(){
        var list = jQuery.parseJSON( '${ctx.map.json_list}' );
        for(i=0; i < list.length ; i++){
            $("#f_${param.f}").get(0)[i]= new Option(list[i].text, list[i].id, false, list[i].selected);
        }
        $("#f_${param.f}").combobox();
    });
</script>
