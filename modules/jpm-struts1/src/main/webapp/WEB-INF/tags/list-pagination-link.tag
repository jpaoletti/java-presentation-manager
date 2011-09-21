<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pm" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name="i" required="true" type="java.lang.Integer"%>
<c:if test="${PMLIST.page==i}"><a>${i}</a></c:if>
<c:if test="${PMLIST.page!=i}"><a href="javascript:paginate('${i}')">${i}</a></c:if> |