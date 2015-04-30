(define (surface-area-of-sphere r)
  (let ((pi 3.14159))
(* 4 pi r r)))
(surface-area-of-sphere 2)

(define (volume-of-ball r)
  (let ((pi 3.14159))
(* (/ 1 3) (surface-area-of-sphere r) r)))
(volume-of-ball 2)

(define (energy-from-mass m)
  (let ((c 299792458))
(* m c c)))
(energy-from-mass 2)

(define (quadratic a b c)
  (let ((root (- (* b b) (* 4 a c))))
    (cond ((> root 0) 2)
          ((= root 0) 1)
          ((< root 0) 0)))) 
(quadratic 1 2 1)
(quadratic 3 0 1)
(quadratic 2 3 1)

(define (dot2 a1 a2 b1 b2) (+ (* a1 b1) (* a2 b2)))
(define (dot3 a1 a2 a3 b1 b2 b3) (+ (* a1 b1) (* a2 b2) (* a3 b3)))

(define (detM a b c d) (- (* a d) (* b c)))
(define (detA a1 a2 b1 b2 c1 c2 d1 d2) 
  (detM (dot2 a1 b1 a2 c2)
        (dot2 a1 b1 b2 d2) 
        (dot2 c1 d1 a2 c2)
        (dot2 c1 d1 b2 d2)))
(detA 3 -2 5 1 4 -6 -7 3)

;(define (detA a1 a2 b1 b2 c1 c2 d1 d2)
;    (- (* (dot2 a1 b1 a2 c2)
;        (dot2 c1 d2 b2 d2))
;     (* (dot2 a1 b1 b2 d2) 
;        (dot2 c1 d1 a2 c2))))
;Other method but did not use detM

(define (perpendicular? a1 a2 a3 b1 b2 b3)
  (if (= (dot3 a1 a2 a3 b1 b2 b3) 0)
      ("perpendicular")
      "not perpendicular"))
(dot3 1 2 3 1 2 3)
(perpendicular? 1 2 3 1 2 3)

;optional questions 4, 5
