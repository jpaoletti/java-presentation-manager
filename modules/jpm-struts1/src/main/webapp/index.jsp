<%@include file="inc/tag-libs.jsp" %>
<script type="text/javascript" charset="utf-8">
    function loadPage(url){
        var mf = window.frames["mainframe"];
        mf.location = url;
        $(mf).focus();
    }
</script>
<logic:present name="pm">
    <pm:page title="titles.index" >
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
            $(document).ready(function(){
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
                <div id="full_header">
                    <pm:header />
                    <jsp:include page="pages/menu.jsp" />
                </div>
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
                    <logic:notPresent name="pmsession">
                        <iframe id="mainframe" name="mainframe" frameborder="0"  width="100%" height="75%" src="${es.context_path}/pages/login.jsp" >
                        </iframe>
                    </logic:notPresent>

                    <logic:present name="pmsession">
                        <iframe id="mainframe" name="mainframe" frameborder="0"  width="100%" height="75%" src="${es.context_path}/${es.welcomePage}">
                        </iframe>
                    </logic:present>
                </div>
            </div>
            <div class="index_layout_footer">
                <pm:footer />
            </div>
        </div>
    </pm:page>
</logic:present>

<logic:notPresent name="pm">
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
        <bean:message key="pm.not.present"/>
    </div>
</logic:notPresent>