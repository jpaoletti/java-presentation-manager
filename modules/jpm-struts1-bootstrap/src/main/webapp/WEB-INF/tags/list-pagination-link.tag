<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@attribute name="i" required="true" type="java.lang.Integer"%>
<li class="${(PMLIST.page==i)?'active disabled':''}" ><a href="javascript:paginate('${i}')">${i}</a></li>
