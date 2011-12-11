<%@include file="inc/tag-libs.jsp" %>
<c:if test="${not empty pm}">
    <pm:page title="titles.index" bodyClass="outer-index" loading="false">
        <script type="text/javascript" src="js/expand.js"></script>
        <script type="text/javascript" src="js/jquery.cookie.js"></script>
        <script type="text/javascript">
            PM_register(function(){
                $(window.frames["mainframe"]).focus();
            });
        </script>
        <div class="index_layout" id="page-container">
            <div class="index_layout_header">
                <div id="full_header"><pm:header /><pmfn:menu pmsession="${pmsession}" /></div>
            </div>
            <div class="index_layout_content">
                <c:if test="${empty pmsession}">
                    <iframe id="mainframe" name="mainframe" src="${es.context_path}/pages/login.jsp"></iframe>
                </c:if>
                <c:if test="${not empty pmsession}">
                    <iframe id="mainframe" name="mainframe" src="${es.context_path}/${es.welcomePage}"></iframe>
                </c:if>
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