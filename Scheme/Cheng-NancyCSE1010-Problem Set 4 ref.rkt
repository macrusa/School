
;5)
(define (sum term a b)
  (if (> a b)
      0
      (+ (term a) (term (+ a 1))))) 
(define (function-1 x) (x))
(define (function-2 x) (* x x))
(define (function-4 x) (* x x x x))
(sum function-4 0 1)


;EXAMPLES
(define (derivative f dx)
  (lambda (x)
    (/ (- (f (+ x dx)) (f x))
       dx))) 
((derivative function-f 0.0000001) 3)

(display "Hello, World!")

(lambda (x) 3)
;the function that takes the argument (x) and returns from the body 3
;(lambda (argument) body)

(define (twice f) 
  (lambda (x) (f (f x))))
(define (inc x) (+ x 1))
((twice inc) 0)

(define (function x) (+ x 1))
(define (series f x) (* 2
  (if (= x 0)
      (f 0)
      (f x))))
(series function 2)