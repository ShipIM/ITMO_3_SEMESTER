<?php
    class WrongInputException extends Exception {
        public function __construct() {
            $this->message = "Ошибка, связанная с введённым значением.";
        }
    }
?>