<%-- 
    Document   : LecturerChooseExamToTake
    Created on : Jul 8, 2024, 5:52:21 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="update" method="GET">
            <input type="hidden" name="cid" value="${requestScope.cid}"/>
            <c:forEach items="${requestScope.exams}" var="e">
                <input type="checkbox" name="eid" value="${e.id}" /> 
                ${e.assessment.category}-(${e.from}:${e.assessment.weight}%) <br/>
            </c:forEach>
                <input type="submit" value="take"/>
        </form>
    </body>
</html>
