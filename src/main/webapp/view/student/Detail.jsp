
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile of ${requestScope.student.name}</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
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
        a {
            display: block;
            margin-bottom: 20px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            text-align: center;
        }
        a:hover {
            color: #45a049;
        }
    </style>
</head>
<body>
    <div><a href="../view/ulti/Home.jsp">Home</a></div>
    <div class="container">
        <h1>Profile of ${requestScope.student.name}</h1>
        <table>
            <tr>
                <th>Name</th>
                <td>${requestScope.student.name}</td>
            </tr>
            <tr>
                <th>ID</th>
                <td>${requestScope.student.id}</td>
            </tr>
            <tr>
                <th>Major</th>
                <td>${requestScope.student.major.name}</td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td>${requestScope.student.dob}</td>
            </tr>
            <tr>
                <th>Gender</th>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.student.gender eq true}">Male</c:when>
                        <c:otherwise>Female</c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${requestScope.student.email}</td>
            </tr>
            <tr>
                <th>Phone Number</th>
                <td>${requestScope.student.phonenumber}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${requestScope.student.address}</td>
            </tr>
            <tr>
                <th>Current Term</th>
                <td>${requestScope.student.currterm}</td>
            </tr>
        </table>
    </div>
</body>
</html>

