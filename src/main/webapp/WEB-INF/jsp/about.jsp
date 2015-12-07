<%-- 
    Document   : about
    Created on : Aug 20, 2015, 5:55:38 PM
    Author     : Igor Salnikov  <isalnikov1@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="all" href="/media/css/user.css">
        <title>about</title>
    </head>
    <body>
        <h1>about</h1>

        ${pageContext.response.locale}</p>
        <spring:message code="hello" /></p>

User <%=request.getRemoteUser()%> has been logged out.
<script src="/media/jquery/jquery.js"/>
</body>
</html>
