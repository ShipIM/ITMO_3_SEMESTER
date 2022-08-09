<?php
    require_once('Resultable.php');

    class CachedResultable implements Resultable {
        private $cache;
        private $resultable;

        public function __construct(Resultable $resultable) {
            $this->resultable = $resultable;
        }

        public function result() {
            if(empty($this->cache)) {
                $this->cache = $this->resultable->result();
            }

            return $this->cache;
        }
    }
?>