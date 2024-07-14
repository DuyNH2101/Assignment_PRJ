<%-- 
    Document   : ChooseSubjectAndSemesterToViewStatistic
    Created on : Jul 15, 2024, 5:00:40 AM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Subject and Semester</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .form-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 300px;
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
            select, input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
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
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1>View Statistics</h1>
            <form action="view" method="POST">
                <label for="semester">Select Semester:</label>
                <select id="semesterId" name="semesterId" onchange="updateSemesterHiddenInputs()">
                    <c:forEach items="${requestScope.semesters}" var="semester">
                        <option value="${semester.id}" data-season="${semester.season}" data-year="${semester.year}">
                            ${semester.season} ${semester.year}
                        </option>
                    </c:forEach>
                </select>
                <input type="hidden" id="semesterSeason" name="semesterSeason" value="">
                <input type="hidden" id="semesterYear" name="semesterYear" value="">

                <label for="subject">Select Subject:</label>
                <select id="subjectId" name="subjectId" onchange="updateSubjectHiddenInputs()">
                    <c:forEach items="${requestScope.subjects}" var="subject">
                        <option value="${subject.id}" data-name="${subject.name}">
                            ${subject.name} (${subject.codename})
                        </option>
                    </c:forEach>
                </select>
                <input type="hidden" id="subjectName" name="subjectName" value="">

                <input type="submit" value="View">
            </form>
        </div>
        <script>
            function updateSemesterHiddenInputs() {
                const semesterSelect = document.getElementById('semesterId');
                const selectedOption = semesterSelect.options[semesterSelect.selectedIndex];
                document.getElementById('semesterSeason').value = selectedOption.getAttribute('data-season');
                document.getElementById('semesterYear').value = selectedOption.getAttribute('data-year');
            }

            function updateSubjectHiddenInputs() {
                const subjectSelect = document.getElementById('subjectId');
                const selectedOption = subjectSelect.options[subjectSelect.selectedIndex];
                document.getElementById('subjectName').value = selectedOption.getAttribute('data-name');
            }

            // Initialize hidden inputs on page load
            window.onload = function() {
                updateSemesterHiddenInputs();
                updateSubjectHiddenInputs();
            };
        </script>
    </body>
</html>