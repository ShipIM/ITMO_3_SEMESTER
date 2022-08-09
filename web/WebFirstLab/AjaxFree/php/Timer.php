<?php
    require_once('Resultable.php');

    class Timer {
        private $resultable;

        public function __construct(Resultable $resultable) {
            $this->resultable = $resultable;
        }

        public function time() {
            $time = microtime();
            $this->resultable->result();
            
            return number_format(microtime() - $time, 7, '.', '.');
        }   
    }
?>