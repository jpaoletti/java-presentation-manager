<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="login">
    <c:if test="${not empty pmsession}">
        <script type="text/javascript" charset="utf-8">
            parent.location = "${es.context_path}";
        </script>
    </c:if>
    <c:if test="${not empty reload}">
        <script type="text/javascript" charset="utf-8">
            parent.location = "${es.context_path}";
        </script>
    </c:if>
    <c:if test="${empty pmsession}">
        <div id="login" class="boxed">
            <h2 class="title"><pmfn:message key="login"/> </h2>
            <div class="content">
                <form action="${es.context_path}/login.do" method="POST">
                    <fieldset>
                        <c:if test="${pm.loginRequired}">
                            <legend><pmfn:message key="login.sign.in" /></legend>
                            <label for="username"><pmfn:message key="login.username" /></label>
                            <input name="username" id="username" />
                            <label for="password"><pmfn:message key="login.password" /></label>
                            <input type="password" name="password" id="password" value="" />
                        </c:if>
                        <input type="submit" value="<pmfn:message key="login.sign.in" />" />
                    </fieldset>
                </form>
            </div>
            <div class="message_container">&nbsp;</div>
        </div>
    </c:if>
</pm:page>