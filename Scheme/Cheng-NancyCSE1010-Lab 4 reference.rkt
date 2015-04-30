(define (output-function) (define (f x) x) f)
(define test (output-function))
(test 10)

((lambda (x) (* x x)) 3)

#lang racket
(require plot)
(plot-new-window? #t)
(define (f x) (+ (* (sin x) (- 3 x)) 1))
(plot (function f (- pi) pi #:label "sine x")
      #:x-min -5 #:x-max 5 #:y-min -5 #:y-max 5)

;4)
#lang racket
(require plot)
(plot-new-window? #t)
(define (f x) (+ (* (sin x) (- 3 x)) 1))
(plot (function f (- pi) pi #:label "sine x")
      #:x-min -5 #:x-max 5 #:y-min -5 #:y-max 5)