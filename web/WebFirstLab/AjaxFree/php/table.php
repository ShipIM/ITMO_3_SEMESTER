<?php
    require_once('Hit.php');
    require_once('CachedResultable.php');
    require_once('Row.php');
    require_once('NumberContainer.php');
    require_once('Timer.php');
    require_once('RadioContainer.php');
    require_once('TextFieldContainer.php');
    require_once('CachedContainable.php');

    session_start();
 ?>
 <!DOCTYPE html>
<html lang="rus">
<head>
    <meta charset="UTF-8">
    <title>FirstLab</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="js/check.js"></script>
    <script src="js/ajax.js"></script>
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
            width: 50%;
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
            padding-left: 2%;
            padding-right: 2%;
        }

        ul {
            padding-right: 3%;
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
        }

        tr, td, th {
            border-radius: 12px;
            background-color: wheat;
        }
    </style>
</head>
<body>
    <table id="frame">
        <tr>
            <th id="header"><b>ФИО: Шипунов Илья Михайлович. Группа: P3118 Вариант: 1818</b></th>
        </tr>
        <tr>
            <td>
                <p>Разработать PHP-скрипт, определяющий попадание точки на координатной плоскости в заданную область, и создать HTML-страницу, которая формирует данные для отправки их на обработку этому скрипту.</p>
                <p>Параметр R и координаты точки должны передаваться скрипту посредством HTTP-запроса. Скрипт должен выполнять валидацию данных и возвращать HTML-страницу с таблицей, содержащей полученные параметры и результат вычислений - факт попадания или непопадания точки в область. Предыдущие результаты должны сохраняться между запросами и отображаться в таблице.</p>
                <p>Кроме того, ответ должен содержать данные о текущем времени и времени работы скрипта.</p>
                <ul>
                    <li><span>Для расположения текстовых и графических элементов необходимо использовать табличную верстку.</span></li>
                    <li><span>Данные формы должны передаваться на обработку посредством GET-запроса.</span></li>
                    <li><span>Таблицы стилей должны располагаться в самом веб-документе.</span></li>
                    <li><span>При работе с CSS должно быть продемонстрировано использование селекторов идентификаторов, селекторов дочерних элементов, селекторов классов, селекторов потомств а также такие свойства стилей CSS, как наследование и каскадирование.</span></li>
                    <li><span>HTML-страница должна иметь "шапку", содержащую ФИО студента, номер группы и номер варианта. При оформлении шапки необходимо явным образом задать шрифт (serif), его цвет и размер в каскадной таблице стилей.</span></li>
                    <li><span>Отступы элементов ввода должны задаваться в процентах.</span></li>
                    <li><span>Страница должна содержать сценарий на языке JavaScript, осуществляющий валидацию значений, вводимых пользователем в поля формы. Любые некорректные значения (например, буквы в координатах точки или отрицательный радиус) должны блокироваться.</span></li>
                </ul>
                <img src="images/task.PNG" class="border"/>
            </td>
        </tr>
        <tr>
            <form action="php/table.php" method="get">
                <td>
                    <table class="input">
                        <col width="22%">
                        <tr>
                            <td class="label"><label for="first_in_a_row"><b>Выберите значение X:</b></label></td>
                            <td>
                                <input type="radio" name = "x" value="-2" id="first_in_a_row">-2</input>
                                <input type="radio" name = "x" value="-1.5">-1.5</input>
                                <input type="radio" name = "x" value="-1">-1</input>
                                <input type="radio" name = "x" value="-0.5">-0.5</input>
                                <input type="radio" name = "x" value="0">0</input>
                                <input type="radio" name = "x" value="0.5">0.5</input>
                                <input type="radio" name = "x" value="1">1</input>
                                <input type="radio" name = "x" value="1.5">1.5</input>
                                <input type="radio" name = "x" value="2">2</input>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><label for="y_input"><b>Введите значение Y:</b></label></td>
                            <td><input type="text" name="y" placeholder=" -3 ... 3" id="y_input"></td>
                        </tr>
                        <tr>
                            <td class="label"><label for="r_input"><b>Введите значение R:</b></label></td>
                            <td><input type="text" name="r" placeholder=" 1 ... 4" id="r_input"></td>
                        </tr>
                        <tr>
                            <td class="label" colspan="2"><input type="submit" disabled value="Отправить"></td>
                        </tr>
                    </table>
                </td>
            </form>
        </tr>
        <tr>
            <td id="content_table_container">
                <table>
                    <tr>
                        <th>Данные для проверки</th>
                        <th>Результат</th>
                        <th>Время отправки запроса</th>
                        <th>Время исполнения скрипта</th>
                    </tr>
                    <?php
                        if($_SERVER['REQUEST_METHOD'] == 'GET') {
                            $x = new Container($_GET['x']);
                            $y = new Container($_GET['y']);
                            $r = new Container($_GET['r']);

                            $x = new RadioContainer($x, array(-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2));
                            $y = new TextFieldContainer($y, -3, 3);
                            $r = new TextFieldContainer($r, 1, 4);

                            $x = new CachedContainable($x);
                            $y = new CachedContainable($y);
                            $r = new CachedContainable($r);

                            $hit = new Hit($x, $y, $r);
                            $cachedHit = new CachedResultable($hit);
                            $timer = new Timer($cachedHit);

                            $time = $timer->time();

                            $row = new Row($_GET['x'], $_GET['y'], $_GET['r'], $cachedHit->result(), $_GET['time'], $time);

                            empty($_SESSION['rows']) ? $_SESSION['rows'] = array($row) : array_push($_SESSION['rows'], $row);

                            $table = '';
                            foreach($_SESSION['rows'] as $value) {
                                $table .= $value->data();
                            }

                            echo $table; 
                        }
                    ?>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>