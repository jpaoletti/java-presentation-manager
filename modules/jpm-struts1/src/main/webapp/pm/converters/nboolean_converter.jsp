<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${param.checked=='true'}">
    <input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" checked /><pmfn:message key="pm.converter.boolean_converter.yes" />
    <input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /> <pmfn:message key="pm.converter.boolean_converter.no" />
    <input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" /> <pmfn:message key="pm.converter.boolean_converter.null" />
</c:if>
<c:if test="${param.checked=='false'}">
    <input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><pmfn:message key="pm.converter.boolean_converter.yes" />
    <input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" checked /><pmfn:message key="pm.converter.boolean_converter.no" />
    <input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" /> <pmfn:message key="pm.converter.boolean_converter.null" />
</c:if>
<c:if test="${param.checked=='null'}">
    <input type="radio" value="true"  id="f_${param.f}" name="f_${param.f}" /><pmfn:message key="pm.converter.boolean_converter.yes" />
    <input type="radio" value="false" id="f_${param.f}" name="f_${param.f}" /><pmfn:message key="pm.converter.boolean_converter.no" />
    <input type="radio" value="null"  id="f_${param.f}" name="f_${param.f}" checked /> <pmfn:message key="pm.converter.boolean_converter.null" />
</c:if>
