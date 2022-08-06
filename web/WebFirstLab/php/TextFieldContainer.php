<?php
    require_once('Container.php');
    require_once('WrongInputException.php');

    class TextFieldContainer implements Containable {
        private $container;
        private $lowerBound;
        private $upperBound;

        public function __construct(Container $container, $lowerBound, $upperBound) {
            $this->container = $container;
            $this->lowerBound = $lowerBound;
            $this->upperBound = $upperBound;
        }

        public function content() {
            $content = $this->container->content();

            if($this->lowerBound <= $content && $content <= $this->upperBound) {
                return $content;
            }

            throw new WrongInputException();
        }
    }
?>