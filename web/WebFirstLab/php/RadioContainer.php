<?php
    require_once('Container.php');
    require_once('WrongInputException.php');

    class RadioContainer implements Containable {
        private $container;
        private $array;

        public function __construct(Container $container, array $array) {
            $this->container = $container;
            $this->array = $array;
        }

        public function content() {
            $content = $this->container->content();

            if(in_array($content, $this->array)) {
                return $content;
            }

            throw new WrongInputException();
        }
    }
?>