<?php
    require('Hit.php');
    require('Row.php');

    if($_SERVER['REQUEST_METHOD'] == 'GET') {
        session_start();
        $time = microtime();

        $hit = new Hit((float) $_GET['x'], (float) $_GET['y'], (float) $_GET['r']);
        $row = new Row($_GET['x'], $_GET['y'], $_GET['r'], $hit->result() ? 'true' : 'false', $_GET['time'], number_format(microtime() - $time, 7, '.', '.'));
        
        empty($_SESSION['rows']) ? $_SESSION['rows'] = array($row) : array_push($_SESSION['rows'], $row);

        $table = '';
        foreach($_SESSION['rows'] as $value) {
            $table .= $value->data();
        }

        echo $table; 
    }
 ?>