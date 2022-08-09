<?php
    require_once('WrongInputException.php');
    require_once('Containable.php');

    class Container implements Containable {
        private $content;

        public function __construct($content) {
            $this->content = $content;
        }

        public function content() {
            if(preg_match("/(^-?\d+([,.]\d+)?)$/", $this->content)) {
                return (float) str_replace(',', '.', $this->content);
            }

            throw new WrongInputException(); 
        }
    }
?>