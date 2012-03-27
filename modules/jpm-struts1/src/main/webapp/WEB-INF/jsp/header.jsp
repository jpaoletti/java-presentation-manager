<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<div class="index_layout_header">
    <div id="full_header"><pm:header /><pmfn:menu pmsession="${pmsession}" /></div>
</div>
<script type="text/javascript" src="${es.context_path}/js/menu.js"></script>
<script type="text/javascript">
    PM_register(function(){
        ddsmoothmenu.init({
            mainmenuid: "menu", //menu DIV id
            orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
            classname: 'ddsmoothmenu', //class added to menu's outer DIV
            //customtheme: ["#1c5a80", "#18374a"],
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        })
    });
</script>