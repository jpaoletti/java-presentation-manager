<%@include file="inc/tag-libs.jsp" %>
<c:if test="${not empty pm}">
    <pm:page title="titles.index" bodyClass="outer-index">
        <script type="text/javascript" src="js/expand.js"></script>
        <script type="text/javascript" src="js/jquery.cookie.js"></script>
        <script type="text/javascript">
            var cookie_name='header_expanded';
            function setCookie(b){
                jQuery.cookie(cookie_name, b, {expires: 100});
            }
            function hideHeader(){
                $("#full_header").slideUp('slow','linear');
                $("#hide_context").hide();
                $("#show_context").show();
                setCookie(false);
            }
            function showHeader(){
                $("#full_header").slideDown('slow','linear');
                $("#hide_context").show();
                $("#show_context").hide();
                setCookie(true);
            }
            function initBtn(){
                if(jQuery.cookie(cookie_name) == null ){
                    setCookie(true);
                };
                if(jQuery.cookie(cookie_name) == 'true'){
                    showHeader();
                }else{
                    hideHeader();
                }
            }
            PM_register(function(){
                $(window.frames["mainframe"]).focus();
                <c:if test="${pm.hideableHeader}">
                    initBtn();
                    $(".show_hide_btn").offset({left: $("#topmenu").offset().left });
                    $("#hide_context").click( hideHeader );
                    $("#show_context").click( showHeader );
                </c:if>
                });
        </script>
        <div class="index_layout" id="page-container">
            <div class="index_layout_header">
                <div id="full_header"><pm:header /><pmfn:menu pmsession="${pmsession}" /></div>
            </div>
            <div class="index_layout_content">
                <div id="content">
                    <c:if test="${pm.hideableHeader}">
                        <div id="hide_context" class="show_hide_btn">
                            <img alt="^" src="${es.context_path}/templates/${pm.template}/img/arrow-up.gif" />
                        </div>
                        <div id="show_context" class="show_hide_btn">
                            <img alt="v" src="${es.context_path}/templates/${pm.template}/img/arrow-down.gif" />
                        </div>
                    </c:if>
                    <c:if test="${empty pmsession}">
                        <iframe id="mainframe" name="mainframe" frameborder="0"  src="${es.context_path}/pages/login.jsp"></iframe>
                    </c:if>

                    <c:if test="${not empty pmsession}">
                        <iframe id="mainframe" name="mainframe" frameborder="0"  src="${es.context_path}/${es.welcomePage}"></iframe>
                    </c:if>
                </div>
            </div>
            <div class="index_layout_footer">
                <div id="footer">
                    <div id="appversion">v${pm.appversion}</div>
                    <div id="poweredby"><a href="http://jpaoletti.github.com/java-presentation-manager/" target="_blank">Powered by jPM</a></div>
                    <p id="legal"><pmfn:message key="footer.copyright.pre" /><fmt:formatDate pattern="yyyy"  value="<%=new java.util.Date()%>" />&nbsp;${pm.copyright}&nbsp;<pmfn:message key="footer.copyright.post" /></p>
                </div>
            </div>
        </div>
    </pm:page>
</c:if>

<c:if test="${empty pm}">
    <style type="text/css" >
        #pm_error_div{
            margin: 70px;
            padding: 40px;
            border-color: black;
            border-style: solid;
            border-width: 1px;
            background-color: red;
            font-size: large;
            font-weight: bold;
            width:400px;
        }
        #error_img{
            vertical-align:middle;
            float:left;
            width:100px;
            margin: 0px 20px 0px 0px;
        }
    </style>
    <div id="pm_error_div">
        <img alt="error" src="error.png" id="error_img">
        Presentation Manager detected some errors on initialization and was not
        able to start. Please check the logs .
    </div>
</c:if>