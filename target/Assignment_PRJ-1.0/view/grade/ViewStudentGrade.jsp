<%-- 
    Document   : ViewStudentGrade
    Created on : Jul 10, 2024, 10:33:04 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>TERM</th>
                    <th>COURSE for ${requestScope.semester.season}${requestScope.semester.year}</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <div id="term">
                            <table>
                                <tbody>
                                    <c:forEach items="${requestScope.student.semesters}" var="sem">
                                        <tr><td><a href="?semid=${sem.id}">${sem.season}${sem.year}</a></td></tr>
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
                                        <tr><td><a href="?semid=${requestScope.semester.id}&cid=${course.id}">${course.subject.name}(${course.subject.codename})</a>(${course.name}, from ${requestScope.semester.from} to ${requestScope.semester.to})</td></tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
