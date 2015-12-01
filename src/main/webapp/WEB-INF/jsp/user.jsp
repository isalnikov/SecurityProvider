<%-- 
    Document   : about
    Created on : Aug 20, 2015, 5:55:38 PM
    Author     : Igor Salnikov  <isalnikov1@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="all" href="/media/css/user.css">
        
        <title>user</title>
    </head>
    <body>
        <h1>user</h1>
         User <%=request.getRemoteUser()%> has been logged out.
         
         <script src="/media/jquery/jquery.js"/>
    </body>
</html>
