<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../inc/tag-libs.jsp" %>
<pm:page title="titles.welcome">
    <h2 class="title"><pmfn:message key="index.custom.welcome" arg0="${pmsession.user.name}"/></h2>
    <img alt="welcome" src="${es.templatePath}/images/welcome.jpg" />
    <p style="width: 600px; font-size: 1.3em">
        Java Presentation Manager is a CRUD system with extended tools 
        to fast build an administrative and monitoring web site based in xml 
        definition files, with data source and interface independece so you can 
        define your model in xml terms and choose the data sources and 
        visualization that you like most.
    </p>
</pm:page>