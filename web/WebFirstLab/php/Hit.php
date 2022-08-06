<?php
    require_once('Containable.php');
    require_once('Resultable.php');

    class Hit implements Resultable {
        private $x;
        private $y;
        private $r;

        public function __construct(Containable $x, Containable $y, Containable $r) {
            $this->x = $x;
            $this->y = $y;
            $this->r = $r;
        }

        public function result() {
            try {
                if($this->x->content() > 0) {
                    if($this->y->content() > 0) {
                        return $this->firstQuarter();
                    } 

                    return $this->fourthQuarter();
                } 

                if($this->y->content() > 0) {
                    return $this->secondQuarter();
                }

                return $this->thirdQuarter();
            } catch(Exception $e) {
                return 'error';
            }
        }

        private function firstQuarter() {
            return 'false';
        }

        private function secondQuarter() {
            if((pow($this->x->content(), 2) + pow($this->y->content(), 2)) <= pow($this->r->content(), 2)) return 'true';
            
            return 'false';
        }

        private function thirdQuarter() {
            if((abs($this->x->content()) <= $this->r->content()/2) && (abs($this->y->content()) <= $this->r->content())) return 'true';

            return 'false';
        }

        private function fourthQuarter() {
            if($this->x->content() - $this->r->content() <= $this->y->content()) return 'true';

            return 'false';
        }
    }
?>