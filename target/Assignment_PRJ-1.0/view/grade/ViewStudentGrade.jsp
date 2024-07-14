<%-- 
    Document   : ViewStudentGrade
    Created on : Jul 10, 2024, 10:33:04 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Grades</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                background-color: #fff;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f8f8f8;
            }
            a {
                color: #007bff;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            #term, #course {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <table>
            <tbody>
                <tr>
                    <td>
                        <table>
                            <thead>
                                <tr>
                                    <th>TERM</th>
                                    <th>COURSE for ${requestScope.semester.season} ${requestScope.semester.year}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div id="term">
                                            <table>
                                                <tbody>
                                                    <c:forEach items="${requestScope.student.semesters}" var="sem">
                                                        <tr>
                                                            <td><a href="?sid=${param.sid}&semid=${sem.id}">${sem.season} ${sem.year}</a></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                    <td>
                                        <div id="course">
                                            <table>
                                                <tbody>
                                                    <c:forEach items="${requestScope.semester.courses}" var="course">
                                                        <tr>
                                                            <td>
                                                                <a href="?sid=${param.sid}&semid=${requestScope.semester.id}&cid=${course.id}">
                                                                    ${course.subject.name} (${course.subject.codename})
                                                                </a> 
                                                                (${course.name}, from ${requestScope.semester.from} to ${requestScope.semester.to})
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <c:if test="${requestScope.grades != null && not empty requestScope.grades}">
                            <table>
                                <thead>
                                    <tr>
                                        <th>GRADE CATEGORY</th>
                                        <th>Type</th>
                                        <th>WEIGHT</th>
                                        <th>VALUE</th>
                                        <th>COMMENT</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="totalWeightedScore" value="0" />
                                    <c:set var="totalWeight" value="0" />
                                    <c:set var="pass" value="5" />
                                    <c:forEach items="${requestScope.grades}" var="g">
                                        <c:if test="${g.score < 0}"><c:set var="pass" value="2" /></c:if>
                                        <tr>
                                            <td>${g.exam.assessment.category}</td>
                                            <td>${g.exam.assessment.type}</td>
                                            <td>${g.exam.assessment.weight}%</td>
                                            <td><c:if test="${g.score > 0}">${g.score}</c:if></td>
                                            <td></td>
                                            <c:set var="weightedScore" value="${g.score * g.exam.assessment.weight}" />
                                            <c:set var="totalWeightedScore" value="${totalWeightedScore + weightedScore}" />
                                            <c:set var="totalWeight" value="${totalWeight + g.exam.assessment.weight}" />
                                            
                                            <c:if test="${pass != 2 && pass != 0}">
                                                <c:choose>
                                                    <c:when test="${g.score >= g.exam.assessment.completionCriteria}">
                                                        <c:set var="pass" value="1" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set var="pass" value="0" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot>
                                    <c:set var="average" value="${totalWeightedScore / totalWeight}" />
                                    <c:if test="${pass != 2 && pass != 0}">
                                        <c:choose>
                                            <c:when test="${average >= 5}">
                                                <c:set var="pass" value="1" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="pass" value="0" />
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                    
                                    <c:choose>
                                        <c:when test="${pass == 2}">
                                            <c:set var="status" value="STUDYING" />
                                        </c:when>
                                        <c:when test="${pass == 1}">
                                            <c:set var="status" value="PASSED" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="status" value="NOT PASSED" />
                                        </c:otherwise>
                                    </c:choose>
                                    <tr>
                                        <td rowspan="2">COURSE TOTAL</td>
                                        <td>AVERAGE</td>
                                        <td><c:if test="${pass < 2}">${average}</c:if></td>
                                    </tr>
                                    <tr>
                                        <td>STATUS</td>
                                        <td>${status}</td>
                                    </tr>
                                </tfoot>
                            </table>
                        </c:if>
                        <c:if test="${(requestScope.grades == null || empty requestScope.grades) and param.cid != null}">
                            <p>No grades available for this course(<c:forEach items="${requestScope.semester.courses}" var="course"><c:if test="${course.id==param.cid}">${course.subject.name}</c:if></c:forEach>).</p>
                        </c:if>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>