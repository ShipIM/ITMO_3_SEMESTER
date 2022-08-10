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