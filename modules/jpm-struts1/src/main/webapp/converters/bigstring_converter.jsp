<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.withNull}">
    <script type="text/javascript" >
        function disable${param.f}(v){
            if (v){
                $('#f_${param.f}').val("");
                $('#f_${param.f}').attr('disabled', 'disabled');
            }else{
                $('#f_${param.f}').removeAttr('disabled');
            }
        }
    </script>
    <input type="checkbox" ${param.isNull ? 'checked' : ''} value="true" 
           id="f_${param.f}_null" name="f_${param.f}_null"
           onclick="disable${param.f}(this.checked);" /><br/>
</c:if>
<textarea cols="${param.cols}" rows="${param.rows}"  
          ${ (param.isNull and param.withNull) ? 'disabled=disabled' : ''}
          id="f_${param.f}" name="f_${param.f}">${ctx.fieldValue}</textarea>
