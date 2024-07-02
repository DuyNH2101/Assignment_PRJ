<%-- 
    Document   : Home
    Created on : Jul 2, 2024, 9:10:58 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.user.username}</h1>
        <h1>Your role is ${sessionScope.user.role.rolename}</h1>
        <c:if test="${sessionScope.user.role eq null}">
            <h1>There is a bug</h1>
        </c:if>
    </body>
</html>
