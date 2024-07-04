<%-- 
    Document   : Detail
    Created on : Jul 4, 2024, 4:55:17 PM
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
        <h1>Profile of ${requestScope.student.name}</h1>
        <table border="1px">
            <tr>
                <td>Name</td>
                <td>${requestScope.student.name}</td>
            </tr>
            <tr>
                <td>ID</td>
                <td>${requestScope.student.id}</td>
            </tr>
            <tr>
                <td>Major</td>
                <td>${requestScope.student.major.name}</td>
            </tr>
            <tr>
                <td>Date of birth</td>
                <td>${requestScope.student.dob}</td>
            </tr>
            <tr>
                <td>Gender</td>
                <c:choose>
                    <c:when test="${requestScope.student.gender eq true}">
                        <td>Male</td>
                    </c:when>
                    <c:otherwise>
                        <td>Female</td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td>Email</td>
                <td>${requestScope.student.email}</td>
            </tr>
            <tr>
                <td>Phone number</td>
                <td>${requestScope.student.phonenumber}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>${requestScope.student.address}</td>
            </tr>
            <tr>
                <td>Current term</td>
                <td>${requestScope.student.currterm}</td>
            </tr>
        </table>
    </body>
</html>
