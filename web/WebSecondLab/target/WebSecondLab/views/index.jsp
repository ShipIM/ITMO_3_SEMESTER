<%@ page import="app.entities.classes.RowSession" %>
<%@ page import="app.entities.classes.Row" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="rus">
<head>
    <meta charset="UTF-8">
    <title>FirstLab</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/views/js/send.js"></script>
    <script src="${pageContext.request.contextPath}/views/js/check.js"></script>
    <script src="${pageContext.request.contextPath}/views/js/picture.js"></script>
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
            width: 60%;
            height: auto;
            text-align: justify;
        }

        #header {
            color: black;
            font-size: medium;
            font-family: serif;
            text-align: center;
        }

        .label {
            padding-left: 1.5%;
        }

        .input tr {
            height: 35px;
        }

        .input td {
            padding-right: 1%;
        }

        td p {
            padding-left: 3%;
            padding-right: 3%;
        }

        ul, ol {
            padding-right: 3%;
            padding-left: 7%;
        }

        ol ul {
            padding-left: 5%;
        }

        li {
            padding-top: 1%;
        }

        img {
            display: block;
            margin: 1% auto 1%;
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
            margin-top: 0.5%;
            margin-bottom: 0.5%;
        }

        #content_table_container > table td, #content_table_container > table th {
            border: 2px solid black;
        }

        .error {
            color: red;
            font-size: medium;
            padding-left: 3%;
            padding-right: 3%;
            font-family: 'Times New Roman', Times, serif;
            text-align: center;
        }

        tr, td, th {
            border-radius: 12px;
            background-color: wheat;
        }

        td object {
            padding: 2% 2% 2% 2%;
            width: 70%;
            height: 70%;
            display: block;
            margin: auto;
        }
    </style>
</head>
<body>
<table id="frame">
    <tr>
        <th id="header"><b>ФИО: Шипунов Илья Михайлович. Группа: P32111 Вариант: 1145</b></th>
    </tr>
    <tr>
        <td>
            <p>Разработать веб-приложение на базе сервлетов и JSP, определяющее попадание точки на координатной
                плоскости в заданную область.</p>
            <p>Приложение должно быть реализовано в соответствии с шаблоном MVC и состоять из следующих элементов:</p>
            <ul>
                <li><span><b>ControllerServlet</b>, определяющий тип запроса, и, в зависимости от того, содержит ли запрос информацию о координатах точки и радиусе, делегирующий его обработку одному из перечисленных ниже компонентов. Все запросы внутри приложения должны передаваться этому сервлету (по методу GET или POST в зависимости от варианта задания), остальные сервлеты с веб-страниц напрямую вызываться не должны.</span>
                </li>
                <li><span><b>AreaCheckServlet</b>, осуществляющий проверку попадания точки в область на координатной плоскости и формирующий HTML-страницу с результатами проверки. Должен обрабатывать все запросы, содержащие сведения о координатах точки и радиусе области.</span>
                </li>
                <li><span><b>Страница JSP</b>, формирующая HTML-страницу с веб-формой. Должна обрабатывать все запросы, не содержащие сведений о координатах точки и радиусе области.</span>
                </li>
            </ul>
            <p><b>Разработанная страница JSP должна содержать:</b></p>
            <ol>
                <li><span>"Шапку", содержащую ФИО студента, номер группы и номер варианта.</span></li>
                <li><span>Форму, отправляющую данные на сервер.</span></li>
                <li>
                    <span>Набор полей для задания координат точки и радиуса области в соответствии с вариантом задания.</span>
                </li>
                <li><span>Сценарий на языке JavaScript, осуществляющий валидацию значений, вводимых пользователем в поля формы.</span>
                </li>
                <li>
                    <span>Интерактивный элемент, содержащий изображение области на координатной плоскости (в соответствии с вариантом задания) и реализующий следующую функциональность:</span>
                    <ul>
                        <li><span>Если радиус области установлен, клик курсором мыши по изображению должен обрабатываться JavaScript-функцией, определяющей координаты точки, по которой кликнул пользователь и отправляющей полученные координаты на сервер для проверки факта попадания.</span>
                        </li>
                        <li><span>В противном случае, после клика по картинке должно выводиться сообщение о невозможности определения координат точки.</span>
                        </li>
                        <li><span>После проверки факта попадания точки в область изображение должно быть обновлено с учётом результатов этой проверки (т.е., на нём должна появиться новая точка).</span>
                        </li>
                    </ul>
                </li>
                <li><span>Таблицу с результатами предыдущих проверок. Список результатов должен браться из контекста приложения, HTTP-сессии или Bean-компонента в зависимости от варианта.</span>
                </li>
            </ol>
            <p><b>Страница, возвращаемая AreaCheckServlet, должна содержать:</b></p>
            <ol>
                <li><span>Таблицу, содержащую полученные параметры.</span></li>
                <li><span>Результат вычислений - факт попадания или непопадания точки в область.</span></li>
                <li><span>Ссылку на страницу с веб-формой для формирования нового запроса.</span></li>
            </ol>
            <p>Разработанное веб-приложение необходимо развернуть на сервере WildFly. Сервер должен быть запущен в
                standalone-конфигурации, порты должны быть настроены в соответствии с выданным portbase, доступ к http
                listener'у должен быть открыт для всех IP.</p>
        </td>
    </tr>
    <tr>
        <td>
            <form id="picture_form" action="${pageContext.request.contextPath}/controller" method="post"></form>
            <form id="fields_form" action="${pageContext.request.contextPath}/controller" method="post">
                <table class="input">

                    <colgroup>
                        <col width="22%">
                        <col width="35%">
                    </colgroup>

                    <tr>
                        <td class="label"><span><b>Выберите значение R:</b></span></td>
                        <td id="buttonArray">
                            <button type="button" name="r" class="r" value="1">1</button>
                            <button type="button" name="r" class="r" value="2">2</button>
                            <button type="button" name="r" class="r" value="3">3</button>
                            <button type="button" name="r" class="r" value="4">4</button>
                            <button type="button" name="r" class="r" value="5">5</button>
                        </td>
                        <td rowspan="3">
                            <img id="picture" width="70%" height="70%" src=""
                                 alt="Здесь могло быть ваше изображение">
                        </td>
                    </tr>
                    <tr>
                        <td class="label"><span><b>Выберите значение X:</b></span></td>
                        <td id="checkBox">
                            <label><input type="checkbox" name="x" value="-5">-5</label>
                            <label><input type="checkbox" name="x" value="-4">-4</label>
                            <label><input type="checkbox" name="x" value="-3">-3</label>
                            <label><input type="checkbox" name="x" value="-2">-2</label>
                            <label><input type="checkbox" name="x" value="-1">-1</label>
                            <label><input type="checkbox" name="x" value="0">0</label>
                            <label><input type="checkbox" name="x" value="1">1</label>
                            <label><input type="checkbox" name="x" value="2">2</label>
                            <label><input type="checkbox" name="x" value="3">3</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="label"><label for="y_input"><b>Введите значение Y:</b></label></td>
                        <td id="textField">
                            <input type="text" name="y" placeholder=" -3 ... 5" id="y_input" maxlength="8">
                        </td>
                    </tr>
                    <tr>
                        <td class="label" colspan="2"><input type="submit" disabled value="Отправить" id="send">
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
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
                    RowSession rowSession = new RowSession(request.getSession());

                    for (String row : rowSession.getRows("rows")) {
                        out.print(row);
                    }
                %>
            </table>
        </td>
    </tr>
</table>
</body>
</html>