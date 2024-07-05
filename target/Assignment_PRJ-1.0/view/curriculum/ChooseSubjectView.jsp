<%-- 
    Document   : ChooseSubject.jsp
    Created on : Jul 5, 2024, 9:49:16 AM
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
        <h1>Enter the code name or the name of the subject</h1>
        <form action="choose" method="POST">
            <select name="option">
                <option value="code">Code</option>
                <option value="name">Name</option>
            </select>
            <input type="text" name="searchtext">
            <input type ="submit" value="Search">
        </form>
        <c:if test="${requestScope.subjects ne null}">
            <table border="1px">
                <tr>
                    <td>Subject name</td>
                    <td>Subject code</td>
                    <td>Default term</td>
                    <td>Credit</td>
                    <td>Assessment</td>
                </tr>
                <c:forEach items="${requestScope.subjects}" var="s">
                    <tr>
                        <td>${s.name}</td>
                        <td>${s.codename}</td>
                        <td>${s.defterm}</td>
                        <td>${s.credit}</td>
                        <td><a href="view?subid=${s.id}">View</a></td>
                    </tr>
                </c:forEach>
            </table>
            
        </c:if>
    </body>
        
</html>
