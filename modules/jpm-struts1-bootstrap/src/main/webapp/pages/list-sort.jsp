<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<script type="text/javascript">
    PM_register(function(){
        $('#operationsort').remove();
        $("#col_${ctx.entityContainer.list.sort.fieldId}")
        .addClass("sorted-${(ctx.entityContainer.list.sort.desc)?'down':'up'}");
        //<c:forEach var="field" items="${pmfn:displayedFields(entity, 'sort')}">
        $("#col_${field.id}").addClass('sortable').click(function(){
            $("input[name='order']").val('${field.id}');
            $("input[name='desc']").val(($("input[name='desc']").val()=='true')?'false':'true');
            $("#listform").submit();
        });
        //</c:forEach>
    });
</script>