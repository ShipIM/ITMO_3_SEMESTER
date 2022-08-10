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
                    return $this->y->content() > 0 ? $this->firstQuarter() : $this->fourthQuarter();
                } else { 
                    return $this->y->content() >= 0 ? $this->secondQuarter() : $this->thirdQuarter();
                }
            } catch(Exception $e) {
                return 'error';
            }
        }

        private function firstQuarter() {
            return 'false';
        }

        private function secondQuarter() {
            return (pow($this->x->content(), 2) + pow($this->y->content(), 2)) <= pow($this->r->content(), 2) ? 'true' : 'false';
        }

        private function thirdQuarter() {
            return (abs($this->x->content()) <= $this->r->content()/2) && (abs($this->y->content()) <= $this->r->content()) ? 'true' : 'false';
        }

        private function fourthQuarter() {
            return $this->x->content() - $this->r->content() <= $this->y->content() ? 'true' : 'false';
        }
    }
?>