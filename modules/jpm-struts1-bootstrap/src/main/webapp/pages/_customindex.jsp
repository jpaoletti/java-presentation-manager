<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="titles.welcome">
    <div class="">
        <div class="hero-unit">
            <h1>Welcome <i>${pmsession.user.name}</i></h1>
            <p>
                <img alt="welcome" src="${es.templatePath}/images/welcome.jpg" /><br/>
                Java Presentation Manager is a CRUD system with extended tools 
                to fast build an administrative and monitoring web site based in xml 
                definition files, with data source and interface independece so you can 
                define your model in xml terms and choose the data sources and 
                visualization that you like most.
            </p>
        </div>
    </div>
</pm:page>
