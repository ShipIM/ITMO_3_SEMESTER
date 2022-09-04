<?php
    require_once('WrongInputException.php');
    require_once('Containable.php');

    class NumberContainer implements Containable {
        private $content;

        public function __construct($content) {
            $this->content = $content;
        }

        public function content() {
            $tmp = substr($this->content, 0, 8);
            $tmp = str_replace(',', '.', $tmp);
            $tmp = preg_replace('/^\s+/', '', $tmp);
            $tmp = preg_replace('/\s+$/', '', $tmp);
            
            if(preg_match('/(^-?\d+(\.\d+)?)$/', $tmp)) {
                return (float) $tmp;
            }

            throw new WrongInputException(); 
        }
    }
?>