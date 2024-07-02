<%-- 
    Document   : login
    Created on : Jun 25, 2024, 9:47:22 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login page</h1>
        <form action = "login" method="POST">
            Username:<input type="text" name ="username"><br>
            Password:<input type="text" name ="password"><br> 
            <input type="submit" value="Login">
        </form>
    </body>
</html>
