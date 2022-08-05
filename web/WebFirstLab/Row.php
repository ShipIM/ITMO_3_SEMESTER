<?php
    class Row {
        private $x;
        private $y;
        private $r;
        private $result;
        private $currentTime;
        private $resultTime;

        public function __construct($x, $y, $r, $result, $currentTime, $resultTime) {
            $this->x = $x;
            $this->y = $y;
            $this->r = $r;
            $this->result = $result;
            $this->currentTime = $currentTime;
            $this->resultTime = $resultTime;
        }

        public function data() {
            return '<tr><td>' . $this->x . ' ' . $this->y . ' ' . $this->r . '</td>'
                . '<td>' . $this->result . '</td>'
                . '<td>' . $this->currentTime . '</td>'
                . '<td>' . $this->resultTime . '</td></tr>';
        }
    }
?>