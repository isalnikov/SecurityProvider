<%-- 
    Document   : index
    Created on : Nov 24, 2015, 10:51:32 AM
    Author     : Igor Salnikov  
--%>

<%@page import="java.util.Date"%>
<%@page import="java.time.LocalTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="all" href="/media/css/user.css">

        <title>JSP Page</title>
    </head>
    <body>
        <h1>index</h1>
        User <%=request.getRemoteUser()%> has been logged out.<br/>
        
        
        <a href="/admin">admin</a>
        <a href="/user">user</a>
        <a href="/about">about</a>
        <a href="/csrf">csrf</a>
        <a href="/logout">logout</a>
        <!--<a href="/j_spring_security_logout">logout</a>-->

        <p></p>
        
        getMaxInactiveInterval=<%=session.getMaxInactiveInterval()%>  s <br/>
        getCreationTime=<%=new Date(session.getCreationTime())%> <br/>
        getId=<%=session.getId()%> <br/>
        getLastAccessedTime=<%=new Date(session.getLastAccessedTime())%> <br/>
        
        <script src="/media/jquery/jquery.js"/>
    </body>
</html>
