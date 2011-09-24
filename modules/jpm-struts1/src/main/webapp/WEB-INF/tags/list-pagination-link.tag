<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name="i" required="true" type="java.lang.Integer"%>
<c:if test="${PMLIST.page==i}"><a class="pagination ui-state-active" >${i}</a></c:if>
<c:if test="${PMLIST.page!=i}"><a class="pagination" href="javascript:paginate('${i}')">${i}</a></c:if>