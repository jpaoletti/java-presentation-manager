<%@include file="../inc/tag-libs.jsp" %>
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
    <div id="login">
        <h2 class="title"><pmfn:message key="login"/></h2>
        <div class="content ui-widget">
            <form class="ui-widget-header" action="${es.context_path}/login.do" method="POST">
                <fieldset>
                    <c:if test="${pm.loginRequired}">
                        <p>
                            <label for="username"><pmfn:message key="login.username" /><br />
                                <input type="text"
                                       name="username"
                                       id="username"
                                       class="inputFields"
                                       value=""
                                       size="20"
                                       tabindex="1" />
                            </label>
                        </p>
                        <p>
                            <label for="password"><pmfn:message key="login.password" /><br />
                                <input type="password"
                                       name="password"
                                       id="password"
                                       class="inputFields"
                                       value=""
                                       size="20"
                                       tabindex="2" />
                            </label>
                        </p>
                    </c:if><br/>
                    <button class="submit"><pmfn:message key="login.sign.in" /></button>
                </fieldset>
            </form>
        </div>
        <div class="message_container">&nbsp;</div>
    </div>
</c:if>
<script type="text/javascript" charset="utf-8">
    PM_register(function(){
        $("#username").keypress(function(e){
            if (e.keyCode == '13') {
                e.preventDefault();
                $("#password").focus();
            }
        });
        $("#username").focus();
    });
</script>
