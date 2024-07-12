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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>The assessments of subject ${requestScope.subject.name}(${requestScope.subject.codename})</h1>
        <c:if test="${requestScope.subject ne null}">
            <table border="1px">
                <tr>
                    <td>Category</td>
                    <td>Type</td>
                    <td>Part</td>
                    <td>Weight</td>
                    <td>Completion Criteria</td>
                    <td>Duration</td>
                    <td>CLO</td>
                    <td>Question Type</td>
                    <td>No Question</td>
                    <td>Knowledge and Skill</td>
                    <td>Grading Guide</td>
                    <td>Note</td>
                </tr>
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
            </table>
        </c:if>
    </body>
</html>
