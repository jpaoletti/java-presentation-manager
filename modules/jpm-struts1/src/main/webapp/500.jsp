<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="inc/tag-libs.jsp" %>
<%@ page isErrorPage="true"  %>
<%@page import="java.io.PrintWriter" import="jpaoletti.jpm.core.*" import="java.io.StringWriter" %>
<div class="leftpane" align="center">
    <p align="left">
        <i><pmfn:message key="errors.500" /></i>
    </p>
    <p align="right">
        <b><pmfn:message key="webmaster" /></b>
    </p><br/>
    <%-- Exception Handler --%>
    <font color="red">
        ${exception}<br>
    </font>
    <style type="text/css" >
        .exception{
            font-size: small;
            text-align: left;
        }
    </style>
    <pre class="exception">
        <%
        if(exception!=null){
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                exception.printStackTrace(pw);
                out.print(sw);
                sw.close();
                pw.close();
                if(PresentationManager.getPm()!=null) PresentationManager.getPm().error(exception);
        }
        %>
    </pre>
</div>