<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<c:if test="${PMLIST.paginable}">
    <div class="pagination-values pull-left">
        <pmfn:message key='pm.struts.list.rpp' />
        <select name="rowsPerPage" class="input-mini" onchange="this.form.submit();">
            <option ${(PMLIST.rowsPerPage == 5)?'selected':''} value="5">5</option>
            <option ${(PMLIST.rowsPerPage == 10)?'selected':''} value="10">10</option>
            <option ${(PMLIST.rowsPerPage == 20)?'selected':''} value="20">20</option>
            <option ${(PMLIST.rowsPerPage == 50)?'selected':''} value="50">50</option>
            <option ${(PMLIST.rowsPerPage == 100)?'selected':''} value="100">100</option>
        </select>
        <pmfn:message key="pm.struts.list.of" />
        <c:if test="${PMLIST.total != null}">${PMLIST.total}</c:if>
        <c:if test="${PMLIST.total == null}">? &nbsp;</c:if>
    </div>
    <div class="pagination pull-left">
        <ul>
            <li ${(PMLIST.page > 1)?'':"class='disabled'"}><a href="javascript:paginate('${PMLIST.page-1}')">&laquo; <pmfn:message key="pm.struts.list.prev"/></a></li>
            <c:if test="${PMLIST.total != null}">
                <c:if test="${PMLIST.pages > 20}">
                    <pm:list-pagination-link i="${1}" />
                    <input name="page" value="${PMLIST.page}" id="page" size="5" style="width:25px;" /> |
                    <pm:list-pagination-link i="${PMLIST.pages}" />
                </c:if>
                <c:if test="${PMLIST.pages <= 20}">
                    <input type="hidden" value="${PMLIST.page}" id="page" name="page"/>
                    <c:forEach var="i" items="${PMLIST.pageRange}" >
                        <pm:list-pagination-link i="${i}" />
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${empty PMLIST.total}">
                <pm:list-pagination-link i="${1}" />
                <input name="page" value="${PMLIST.page}" id="page" size="5" style="width:25px;" /> |
                <input type="hidden" value="${PMLIST.page}" id="page" name="page"/>
            </c:if>
            <li ${(empty PMLIST.total || PMLIST.page < PMLIST.pages)?'':"class='disabled'"}>
                <a href="javascript:paginate('${PMLIST.page+1}')"><pmfn:message key="pm.struts.list.next"/> &raquo;</a>
            </li>
        </ul>
        <script type="text/javascript"> 
            PM_register(function(){
                
            })
        </script>
    </div>
</c:if>