<%@include file="../inc/tag-libs.jsp" %>
<input type="password" value="" id="f_${param.f}" name="f_${param.f}" onkeyup="check_equal(this.form);" /><br/>
<input type="password" value="" id="r_${param.f}" name="r_${param.f}" onkeyup="check_equal(this.form);" />
<div id="d_${param.f}" class=""><pmfn:message key="pm.converter.password_converter.repeat"/></div>
<script type="text/javascript" charset="utf-8">
    function check_equal(form){
        var p = form.f_${param.f};
        var r = form.r_${param.f};
        var d = document.getElementById("d_${param.f}");
        var sub = form.${entity.id}_submit
        if(p.value != r.value){
            d.className="red";
            sub.disabled=true;
        }else{
            d.className="";
            sub.disabled=false;
        }
    }
</script>