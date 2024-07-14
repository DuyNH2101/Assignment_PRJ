<%-- 
    Document   : ViewStatisticOfCourse
    Created on : Jul 14, 2024, 10:18:18 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistic of course ${requestScope.courseName}</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                cursor: pointer;
            }
            th:hover {
                background-color: #ddd;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .table-container {
                display: flex;
                justify-content: space-between;
            }
            .small-table-container {
                width: 48%;
            }
        </style>
        <script>
            function sortTable(n, tableId) {
                var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                table = document.getElementById(tableId);
                switching = true;
                dir = "asc"; 
                while (switching) {
                    switching = false;
                    rows = table.rows;
                    for (i = 1; i < (rows.length - 1); i++) {
                        shouldSwitch = false;
                        x = rows[i].getElementsByTagName("TD")[n];
                        y = rows[i + 1].getElementsByTagName("TD")[n];
                        if (dir == "asc") {
                            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        } else if (dir == "desc") {
                            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                shouldSwitch = true;
                                break;
                            }
                        }
                    }
                    if (shouldSwitch) {
                        rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                        switching = true;
                        switchcount++;
                    } else {
                        if (switchcount == 0 && dir == "asc") {
                            dir = "desc";
                            switching = true;
                        }
                    }
                }
            }
        </script>
    </head>
    <body>
        <div style="text-align: left"><a href="../../view/ulti/Home.jsp">Home</a></div>
        <div class="container">
            <h2>Statistic of subject ${requestScope.subjectName} in ${requestScope.semesterSeason}, ${requestScope.semesterYear}</h2>
            <div class="table-container">
                <div class="small-table-container">
                    <table id="leftTable">
                        <thead>
                            <tr>
                                <th onclick="sortTable(0, 'leftTable')">Student name &#x25B2;&#x25BC;</th>
                                <th onclick="sortTable(1, 'leftTable')">Student average score &#x25B2;&#x25BC;</th>
                            </tr> 
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.grades}" var="g">
                                <tr>
                                    <td>${g.student.name}</td>
                                    <td>${g.score}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="small-table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Summary</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Total number of students</td>
                                <td>${fn:length(requestScope.grades)}</td>
                            </tr>
                            <tr>
                                <td>Average score</td>
                                <td>
                                    <c:set var="totalScore" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:set var="totalScore" value="${totalScore + g.score}" />
                                    </c:forEach>
                                    ${totalScore / fn:length(requestScope.grades)}
                                </td>
                            </tr>
                            <tr>
                                <td>Not passed</td>
                                <td>
                                    <c:set var="countLessThan4" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score < 4}">
                                            <c:set var="countLessThan4" value="${countLessThan4 + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${countLessThan4}
                                </td>
                            </tr>
                            <tr>
                                <td>Below average</td>
                                <td>
                                    <c:set var="count4To65" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score >= 4 && g.score < 6.5}">
                                            <c:set var="count4To65" value="${count4To65 + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${count4To65}
                                </td>
                            </tr>
                            <tr>
                                <td>Average</td>
                                <td>
                                    <c:set var="count65To8" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score >= 6.5 && g.score <= 8}">
                                            <c:set var="count65To8" value="${count65To8 + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${count65To8}
                                </td>
                            </tr>
                            <tr>
                                <td>Good</td>
                                <td>
                                    <c:set var="count8To9" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score >= 8 && g.score <= 9}">
                                            <c:set var="count8To9" value="${count8To9 + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${count8To9}
                                </td>
                            </tr>
                            <tr>
                                <td>Excellent</td>
                                <td>
                                    <c:set var="countGreaterThan9" value="0" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score >= 9}">
                                            <c:set var="countGreaterThan9" value="${countGreaterThan9 + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    ${countGreaterThan9}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
