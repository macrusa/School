(* 34234567 23123456)
(* 45 321 123 123 123)
(define (de x)(* x 0.75))
    (de 175)
(define (ey x) (* x 132.04))
    (ey 240)
(define (dy x) (ey (de x)))
;(define (dollars to yen variable x) (euros to yen de * 0.75 * 132.04 (dollars to euros x * 0.75))
    (dy 175)
    
;Formula for 2 by 2 matrix
(define (detM a b c d) (- (* a d) (* b c)))
;Matrix M
   (detM 2 -4 -6 12)
   (not (= (detM 2 -4 -6 12) 0))
;Matrix N
   (detM -3 1 2 7)
   (not (= (detM -3 1 2 7) 0))
;Using 8 parameters and the matrix equation on each section and on the entire matrix
;Matrix A 
(define (detA a1 a2 b1 b2 c1 c2 d1 d2) 
  (- (* (+ (* a1 a2) (* b1 c2)) (+ (* c1 b2) (* d1 d2)))
     (* (+ (* a1 b2) (* b1 d2)) (+ (* c1 a2) (* d1 c2)))))
  (detA 2 -3 -4 1 -6 2 12 7)  
  (not (= (detA 2 -3 -4 1 -6 2 12 7) 0)) 

;Formula for 2 by 2 matrix (above)
   (detM 2 -4 -6 12)
   (detM -3 1 2 7)
   (not (= (* (detM 2 -4 -6 12) (detM -3 1 2 7)) 0))

;New Formula for a e by 3 matrix
(define (det3x3 a b c d e f g h i)
(+(-(* a(- (* e f) (* h i)))(* b (-(* d i) (* g f)))) (* c(- (* d h))) (* e g)))
(det3x3 0 5 -6 8 -11 4 5 1 1)