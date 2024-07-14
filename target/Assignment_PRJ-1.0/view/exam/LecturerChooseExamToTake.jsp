<%-- 
    Document   : LecturerChooseExamToTake
    Created on : Jul 8, 2024, 5:52:21 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose Exam to Take</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin-left: auto;
            margin-right: auto;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }
        input[type="checkbox"] {
            margin-right: 10px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div style="text-align: left"><a href="../../view/ulti/Home.jsp">Home</a></div>
    <div class="container">
        <h1>Select Exams to Take</h1>
        <form action="update" method="GET">
            <input type="hidden" name="cid" value="${requestScope.cid}"/>
            <c:forEach items="${requestScope.exams}" var="e">
                <label>
                    <input type="checkbox" name="eid" value="${e.id}" />
                    ${e.assessment.category} - (${e.from}: ${e.assessment.weight}%)
                </label>
            </c:forEach>
            <input type="submit" value="Take"/>
        </form>
    </div>
</body>
</html>
