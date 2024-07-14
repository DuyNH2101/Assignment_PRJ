<%-- 
    Document   : ChooseSubject.jsp
    Created on : Jul 5, 2024, 9:49:16 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose Subject</title>
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
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }
        select, input[type="text"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <div style="text-align: left"><a href="../view/ulti/Home.jsp">Home</a></div>
    <div class="container">
        <h1>Search Subjects</h1>
        <form action="choose" method="POST">
            <select name="option">
                <option value="code">Code</option>
                <option value="name">Name</option>
            </select>
            <input type="text" name="searchtext" placeholder="Enter subject code or name" required>
            <input type="submit" value="Search">
        </form>
        <c:if test="${requestScope.subjects ne null}">
            <table>
                <thead>
                    <tr>
                        <th>Subject Name</th>
                        <th>Subject Code</th>
                        <th>Default Term</th>
                        <th>Credit</th>
                        <th>Assessment</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.subjects}" var="s">
                        <tr>
                            <td>${s.name}</td>
                            <td>${s.codename}</td>
                            <td>${s.defterm}</td>
                            <td>${s.credit}</td>
                            <td><a href="view?subid=${s.id}">View</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>