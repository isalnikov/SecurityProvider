<%-- 
    Document   : admin
    Created on : Nov 27, 2015, 4:53:33 PM
    Author     : Igor Salnikov  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" media="all" href="/media/css/user.css">
    
        <title>JSP Page</title>
    </head>
    <body>
        <h1>admin</h1>
        User <%=request.getRemoteUser()%> has been logged out.
        
        <script src="/media/jquery/jquery.js"/>
    </body>
</html>
