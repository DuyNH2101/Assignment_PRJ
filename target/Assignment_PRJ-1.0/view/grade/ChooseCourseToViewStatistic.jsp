<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }
            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                max-width: 600px;
                width: 100%;
                text-align: center;
            }
            h1 {
                margin-bottom: 20px;
            }
            select, input[type="submit"] {
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
                width: 100%;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #007bff;
                color: white;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #0056b3;
            }
            table {
                width: 100%;
                margin-top: 20px;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f8f8f8;
            }
        </style>
        <script>
            function showCourseDetails() {
                var selectElement = document.getElementById("courseSelect");
                var selectedOption = selectElement.options[selectElement.selectedIndex];
                
                var courseName = selectedOption.getAttribute("data-name");
                var lecturerName = selectedOption.getAttribute("data-lecturer");
                var semesterSeason = selectedOption.getAttribute("data-season");
                var semesterYear = selectedOption.getAttribute("data-year");
                var semesterFrom = selectedOption.getAttribute("data-from");
                var semesterTo = selectedOption.getAttribute("data-to");
                document.getElementById("courseName").value = courseName;
                if (courseName) {
                    var detailsTable = `
                        <table>
                            <tr><th>Course Name</th><td>` +courseName+ `</td></tr>
                            <tr><th>Lecturer</th><td>`+lecturerName+`</td></tr>
                            <tr><th>Semester</th><td>`+semesterSeason + ` ` + semesterYear+`</td></tr>
                            <tr><th>Duration</th><td>`+ semesterFrom + ` to ` + semesterTo +`</td></tr>
                        </table>
                    `;
                    document.getElementById("courseDetails").innerHTML = detailsTable;
                } else {
                    document.getElementById("courseDetails").innerHTML = "";
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>Course Details</h1>
            <form action="view" method="POST">
                <select id="courseSelect" onchange="showCourseDetails()" name="courseSelect">
                    <option value="">Select a course</option>
                    <c:forEach items="${requestScope.courses}" var="c">
                        <option 
                            value="${c.id}" 
                            data-name="${c.name}" 
                            data-lecturer="${c.lecturer.name}" 
                            data-season="${c.sem.season}" 
                            data-year="${c.sem.year}" 
                            data-from="${c.sem.from}" 
                            data-to="${c.sem.to}">
                            ${c.name} by ${c.lecturer.name}
                        </option>
                    </c:forEach>
                </select>
                <input type="hidden" name="courseName" id="courseName" value="">
                <input type="submit" value="View">
            </form>
            <div id="courseDetails"></div>
        </div>
    </body>
</html>