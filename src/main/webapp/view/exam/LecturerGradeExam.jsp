

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enter Scores</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        input[type="text"] {
            width: 60px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            text-align: center;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div style="text-align: left"><a href="../../view/ulti/Home.jsp">Home</a></div>
    <h1>Enter Scores</h1>
    <form action="update" method="POST">
        <table>
            <thead>
                <tr>
                    <th></th>
                    <c:forEach items="${requestScope.exams}" var="e">
                        <th>${e.assessment.category} (${e.assessment.weight}) <br/> ${e.from}</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.students}" var="s">
                    <tr>
                        <td>${s.name}</td>
                        <c:forEach items="${requestScope.exams}" var="e">
                            <td>
                                <input type="text" name="score${s.id}_${e.id}"
                                       <c:forEach items="${requestScope.grades}" var="g">
                                           <c:if test="${e.id eq g.exam.id and s.id eq g.student.id}">
                                               <c:choose>
                                                   <c:when test="${g.score >= 0}"> value="${g.score}"</c:when>
                                                   <c:otherwise>value=""</c:otherwise>
                                               </c:choose>
                                           </c:if>
                                       </c:forEach>
                                />
                                <input type="hidden" name="gradeid" value="${s.id}_${e.id}"/>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="hidden" name="cid" value="${param.cid}" />
        <input type="submit" value="Save"/>
    </form>
</body>
</html>