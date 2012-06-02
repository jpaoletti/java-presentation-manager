<%@ tag description="This tag builds a select with filter possible operations" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/pmfn.tld" prefix="pmfn" %>
<%@attribute name = "field_id" required="true"  %>
<%@attribute name = "filter" required="true"  type="jpaoletti.jpm.core.EntityFilter" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<bean:define id="selected" value="<%= filter.getFilterOperation(field_id).toString() %>" />
<select id="filter_oper_f_${field_id}" name="filter_oper_f_${field_id}" class="filter-operation span1">
    <option value="2" ${selected=='LIKE' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.like" /></option>
    <option value="0" ${selected=='EQ' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.eq" /></option>
    <option value="1" ${selected=='NE' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.ne" /></option>
    <option value="3" ${selected=='GT' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.gt" /></option>
    <option value="4" ${selected=='GE' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.ge" /></option>
    <option value="5" ${selected=='LT' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.lt" /></option>
    <option value="6" ${selected=='LE' ? 'selected' : ''} ><pmfn:message key="pm.struts.filter.le" /></option>
</select>
