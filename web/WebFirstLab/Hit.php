<?php
    class Hit {
        private $x;
        private $y;
        private $r;

        public function __construct($x, $y, $r) {
            $this->x = $x;
            $this->y = $y;
            $this->r = $r;
        }

        public function result() {
            if($this->x > 0) {
                if($this->y > 0) {
                    return $this->firstQuarter();
                } 

                return $this->fourthQuarter();
            } 

            if($this->y > 0) {
                return $this->secondQuarter();
            }

            return $this->thirdQuarter();
        }

        private function firstQuarter() {
            return false;
        }

        private function secondQuarter() {
            if((pow($this->x, 2) + pow($this->y, 2)) <= pow($this->r, 2)) return true;
            
            return false;
        }

        private function thirdQuarter() {
            if((abs($this->x) <= $this->r/2) && (abs($this->y) <= $this->r)) return true;

            return false;
        }

        private function fourthQuarter() {
            if($this->x - $this->r <= $this->y) return true;

            return false;
        }
    }
?>