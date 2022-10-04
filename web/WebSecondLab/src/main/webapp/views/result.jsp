<%@ page import="app.entities.classes.Row" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="rus">
<head>
    <meta charset="UTF-8">
    <title>FirstLab</title>
    <style>
        body {
            background-color: #00467F;
            font-size: large;
        }

        table {
            margin: auto;
            width: 100%;
        }

        #frame {
            width: 35%;
            height: auto;
            text-align: justify;
        }

        .border {
            border: thin solid black;
        }

        #content_table_container > table {
            font-size: medium;
            border-collapse: collapse;
            border: 2px solid black;
            width: 80%;
            height: auto;
            text-align: center;
            margin-top: 2%;
            margin-bottom: 2%;
        }

        #content_table_container > table td, #content_table_container > table th {
            border: 2px solid black;
        }

        #success {
            padding: 2% 5%;
            text-align: center;
            font-size: xx-large;
            font-family: 'Times New Roman', Times, serif;
            color: green;
            word-break: break-all;
        }

        #miss {
            padding-top: 2%;
            padding-bottom: 2%;
            text-align: center;
            font-size: xx-large;
            font-family: 'Times New Roman', Times, serif;
            color: red;
            word-break: break-all;
        }

        td input {
            display: block;
            margin: 2% auto;
            padding-top: 0.3%;
            padding-bottom: 0.3%;
            word-break: break-all;
        }

        tr, td, th {
            border-radius: 12px;
            background-color: wheat;
            font-size: large;
        }
    </style>
</head>
<body>
<table id="frame">
    <tr>
        <td id="content_table_container">
            <table>

                <colgroup>
                    <col width="10%">
                    <col width="10%">
                    <col width="10%">
                </colgroup>

                <tr>
                    <th colspan="3">Данные для проверки</th>
                    <th>Результат</th>
                    <th>Время отправки запроса</th>
                    <th>Время исполнения скрипта</th>
                </tr>

                <%
                    String row = (String) request.getAttribute("row");

                    out.print(row);
                %>

            </table>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="submit" value="Вернуться на страницу с веб-формой">
            </form>
        </td>
    </tr>
</table>
</body>
</html>