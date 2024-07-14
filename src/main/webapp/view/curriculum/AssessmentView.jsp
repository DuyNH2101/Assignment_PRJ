<%-- 
    Document   : Assessment
    Created on : Jul 4, 2024, 5:15:54 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assessments of ${requestScope.subject.name} (${requestScope.subject.codename})</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
                overflow-x: hidden; /* Prevent horizontal scrolling */
            }
            h1 {
                text-align: center;
                color: #333;
                margin-top: 20px;
            }
            .container {
                width: 90%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                table-layout: fixed; /* Ensure the table width doesn't exceed the container */
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
                max-width: 150px; /* Maximum width for each cell */
                word-wrap: break-word; /* Wrap long words to fit within the cell */
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
        </style>
    </head>
    <body>
        <div style="text-align: left"><a href="../view/ulti/Home.jsp">Home</a></div>
        <div class="container">
            <h1>The assessments of subject ${requestScope.subject.name} (${requestScope.subject.codename})</h1>
            <c:if test="${requestScope.subject ne null}">
                <table>
                    <thead>
                        <tr>
                            <th>Category</th>
                            <th>Type</th>
                            <th>Part</th>
                            <th>Weight</th>
                            <th>Completion Criteria</th>
                            <th>Duration</th>
                            <th>CLO</th>
                            <th>Question Type</th>
                            <th>No Question</th>
                            <th>Knowledge and Skill</th>
                            <th>Grading Guide</th>
                            <th>Note</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.subject.assessments}" var="a">
                            <tr>
                                <td>${a.category}</td>
                                <td>${a.type}</td>
                                <td>${a.part}</td>
                                <td><fmt:formatNumber value="${a.weight}" type="number" minFractionDigits="2" />%</td>
                                <td>&gt;<fmt:formatNumber value="${a.completionCriteria}" type="number" /></td>
                                <td>${a.duration}</td>
                                <td>${a.clo}</td>
                                <td>${a.questionType}</td>
                                <td>${a.noQuestion}</td>
                                <td>${a.knowledgeAndSkill}</td>
                                <td>${a.gradingGuide}</td>
                                <td>${a.note}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>