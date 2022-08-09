<?php
    require_once('Containable.php');
    require_once('WrongInputException.php');

    class RadioContainer implements Containable {
        private $containable;
        private $array;

        public function __construct(Containable $containable, array $array) {
            $this->containable = $containable;
            $this->array = $array;
        }

        public function content() {
            $content = $this->containable->content();

            if(in_array($content, $this->array)) {
                return $content;
            }

            throw new WrongInputException();
        }
    }
?>