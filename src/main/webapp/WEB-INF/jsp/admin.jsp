<%-- 
    Document   : admin
    Created on : Nov 27, 2015, 4:53:33 PM
    Author     : Igor Salnikov  <igor.salnikov@stoloto.ru>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>admin</h1>
        User <%=request.getRemoteUser()%> has been logged out.
    </body>
</html>
