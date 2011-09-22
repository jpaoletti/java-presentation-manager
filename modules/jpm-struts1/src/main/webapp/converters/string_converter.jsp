<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <script type="text/javascript" >
        function disable${param.f}(v){
            var f = $('#f_${param.f}');
            if (v){ f.val(""); f.attr('disabled', 'disabled');
            }else{ f.removeAttr('disabled'); }
        }
    </script>
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true" id="f_${param.f}_null" name="f_${param.f}_null" onclick="disable${param.f}(this.checked);" />
</c:if>
<input type="text" maxlength="${param.ml}" ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''} value="${ctx.fieldValue}" id="f_${param.f}" name="f_${param.f}" />