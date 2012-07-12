<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="${es.context_path}/js/codemirror/lib/codemirror.css"/>
<link rel="stylesheet" href="${es.context_path}/js/codemirror/theme/default.css"/>
<script src="${es.context_path}/js/codemirror/lib/codemirror.js"></script>
<script src="${es.context_path}/js/codemirror/mode/xml.js"></script>
<script src="${es.context_path}/js/codemirror/mode/javascript.js"></script>
<script src="${es.context_path}/js/codemirror/mode/css.js"></script>
<script src="${es.context_path}/js/codemirror/mode/htmlmixed.js"></script>
<textarea cols="100" rows="20" id="f_${param.f}" name="f_${param.f}" class="html-converter">${ctx.fieldValue}</textarea>
<script type="text/javascript">
    PM_register(function(){
        CodeMirror.fromTextArea(document.getElementById("f_${param.f}"), {mode: "text/html"});
    });
</script>
