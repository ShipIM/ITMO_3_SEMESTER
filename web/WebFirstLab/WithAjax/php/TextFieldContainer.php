<?php
    require_once('Containable.php');
    require_once('WrongInputException.php');

    class TextFieldContainer implements Containable {
        private $containable;
        private $lowerBound;
        private $upperBound;

        public function __construct(Containable $containable, $lowerBound, $upperBound) {
            $this->containable = $containable;
            $this->lowerBound = $lowerBound;
            $this->upperBound = $upperBound;
        }

        public function content() {
            $content = $this->containable->content();

            if($this->lowerBound <= $content && $content <= $this->upperBound) {
                return $content;
            }

            throw new WrongInputException();
        }
    }
?>