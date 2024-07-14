<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            max-width: 800px;
            border-collapse: collapse;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        a {
            text-decoration: none;
            color: #4CAF50;
            display: block;
            transition: color 0.3s;
        }
        a:hover {
            color: #45a049;
        }
        .category {
            font-weight: bold;
            color: #333;
        }
    </style>
</head>
<body>
    <div>
        <h1>Home Page</h1>
        <table>
            <tr>
                <th>Category</th>
                <th>Links</th>
            </tr>
            <c:if test="${sessionScope.user.student!=null}">
                <tr>
                    <td class="category">Student</td>
                    <td><a href="../../student/detail?sid=${sessionScope.user.student.id}">View Student Details</a></td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.user.student==null}">
                <tr>
                    <td class="category">Student</td>
                    <td><a href="../../student/detail">View Student Details</a></td>
                </tr>
            </c:if>
            <tr>
                <td class="category">Assessment</td>
                <td><a href="../../assessment/view">View Assessments</a></td>
            </tr>
            <tr>
                <td class="category"></td>
                <td><a href="../../assessment/choose">Choose Assessment</a></td>
            </tr>
            <c:if test="${sessionScope.user.student eq null}">
                <tr>
                    <td class="category">Exam</td>
                    <td><a href="../../lecturer/exam/create">Create Exam</a></td>
                </tr>
                <tr>
                    <td class="category"></td>
                    <td><a href="../../lecturer/exam/choose">Choose Exam</a></td>
                </tr>
                <tr>
                    <td class="category"></td>
                    <td><a href="../../lecturer/exam/take">Take Exam</a></td>
                </tr>
            </c:if>
            <tr>
                <td class="category">Grade</td>
                <td><a href="../../grade/student/view">View Student Grades</a></td>
            </tr>
            <c:if test="${sessionScope.user.student eq null}">
                <tr>
                    <td class="category"></td>
                    <td><a href="../../grade/course/view">View Course Grades</a></td>
                </tr>
                <tr>
                    <td class="category"></td>
                    <td><a href="../../grade/subject/view">View Subject Grades</a></td>
                </tr>
            </c:if>
            
        </table>
    </div>
</body>
</html>