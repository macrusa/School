(define (multiplyBy n) (lambda (x) (* n x)))
(multiplyBy 5)
((multiplyBy 5) 2)


(define (make-complex a b) (cons a b))
(define (real-complex pair) (car pair))
(define (imag-complex pair) (cdr pair))
(define (adding-complex pair1 pair2)
  (make-complex (+ (real-complex pair1) (real-complex pair2))
                (+ (imag-complex pair1) (imag-complex pair2))))
(adding-complex (cons 2 2) (cons 5 1))

(define (multiply-complex pair1 pair2)
  (make-complex (* (real-complex pair1) (real-complex pair2))
                (* (imag-complex pair1) (imag-complex pair2))))
(multiply-complex (cons 2 6) (cons 8 5))