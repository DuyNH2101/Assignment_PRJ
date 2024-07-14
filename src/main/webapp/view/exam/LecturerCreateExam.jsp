<%-- 
    Document   : LecturerCreateExam
    Created on : Jul 6, 2024, 5:12:36 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            form {
                max-width: 600px;
                margin: auto;
            }
            select, input[type="datetime-local"], input[type="number"], input[type="submit"] {
                display: block;
                width: 100%;
                margin-bottom: 10px;
                padding: 10px;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <div style="text-align: left"><a href="../../view/ulti/Home.jsp">Home</a></div>
        <h1>Create Exam for Subject: ${subject.name}</h1>
        
        <form action="create" method="POST">
            <input type="hidden" name="subjectId" value="${subject.id}">

            <label for="assessment">Choose Assessment:</label>
            <select name="assessment" id="assessment">
                <option value="">Select an Assessment</option>
                <c:forEach items="${subject.assessments}" var="assessment">
                    <option value="${assessment.id}">${assessment.category} - ${assessment.type}</option>
                </c:forEach>
            </select>
            <label for="datetime">Exam Date and Time:</label>
            <input type="datetime-local" id="datetime" name="datetime" required>

            <label for="duration">Exam Duration (in minutes):</label>
            <input type="number" id="duration" name="duration" min="1" required>
            <input type="submit" value="Create Exam">
        </form>
    </body>
</html>
