<%-- 
    Document   : EnterStudentIdToViewGrade
    Created on : Jul 14, 2024, 10:35:53 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Student ID to View Grade</title>
        <script>
            function changeURLParam() {
                var sid = document.getElementsByName('sid')[0].value;
                var anchor = document.getElementById('viewGradeLink');
                anchor.href = "view?sid=" + sid;
            }
        </script>
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
                padding: 10px;
                border: 1px solid #ccc;
            }
            input[type="text"] {
                padding: 5px;
                font-size: 16px;
            }
            a.button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                margin-top: 10px;
                cursor: pointer;
                font-size: 16px;
                text-align: center;
            }
            a.button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <h1>Enter Student ID to View Grade</h1>
        <form action="view" method="get">
            <table>
                <tr>
                    <td>
                        <input type="text" name="sid" value="" onchange="changeURLParam()">
                    </td>
                </tr>
            </table>
            <a id="viewGradeLink" href="#" class="button">View Grade</a>
        </form>
    </body>
</html>