<%-- 
    Document   : ChooseCourse
    Created on : Jul 6, 2024, 4:45:35 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                max-width: 300px;
                margin: auto;
            }
            select, input[type="submit"] {
                display: block;
                width: 100%;
                margin-bottom: 10px;
                padding: 10px;
                font-size: 16px;
            }
        </style>
        <script>
            function updateSubjectId() {
                var select = document.getElementById("cname");
                var selectedOption = select.options[select.selectedIndex];
                var subjectId = selectedOption.getAttribute("data-subject-id");
                document.getElementById("subjectId").value = subjectId;
            }
            window.onload = function() {
                updateSubjectId();
            }
        </script>
    </head>
    <body>
        <h1>Choose a Course</h1>
        <form action="choose" method="POST">
            <label for="cname">Select Course:</label>
            <select name="cname" id="cname" onchange="updateSubjectId()">
                <c:forEach items="${courses}" var="c">
                    <option value="${c.id}" data-subject-id="${c.subject.id}">${c.name}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="subjectId" id="subjectId" value="">
            <input type="submit" value="Choose">
        </form>
    </body>
</html>
