<?php
    require_once('Containable.php');

    class CachedContainable implements Containable {
        private $cache;
        private $containable;

        public function __construct(Containable $containable) {
            $this->containable = $containable;
        }

        public function content() {
            if(empty($this->cache)) {
                $this->cache = $this->containable->content();
            }

            return $this->cache;
        }
    }
?>