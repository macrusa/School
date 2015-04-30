;1)
(define (harmonic n) (/ 1 n))
(define (sum f n)
  (if (= n 0)
      0
      (+ (f n) (sum f (- n 1)))))
(sum harmonic 3)

;2.a)
;the formula is (f(x+h) - f(x)) / h
(define (function-f x) (* x x))

(define (der f h)
  (lambda (x)
  (/ (- (f (+ x h)) (f x)) h)))
(der function-f 1)
((der function-f 0.01) 2)
;this is only a general function (der (f h) x) is when we want to apply it. The f is the function, h is the value, and x refers to the lambda x

;2.b)
(define (function-f x) (* x x x x))
(define (function f n h)
   (der (der f h) h))

;a procedure
(function function-f 1 2)
;vs. a value b/c x has a value (x = 3)
((function function-f 2 0.00001) 2)
;if n = 0 that means there is no derivative but we want the function b/c we want a procedure
;if n not equal to 0 means that there is a derivative multiple times
;function (der f h)--> this is a equation   (- n 1) --> this is a continuing derivative --> h --> number

;2.c) The derivative of sin(x) is cos(x) because derivative of sin(0.5) = cos(0.5) = 0.88
(define (sin-function x) (sin x))
(function sin-function 1 0.5)

;#lang racket
;(require plot)
;(plot-new-window? #t)
;(define (sin-function h) (der sin h))
;(plot (function (sin-function 0.5) (- pi) pi #:label "sine x")
;        #:x-min -5 #:x-max 5 #:y-min -5 #:y-max 5)

;2.d)Since the fourth derivative of sin x is sin x, the graph should return a sin x graph but it did not. This is because h = 0.5. The definition of the derivative is that it is the h --> 0. 
;#lang racket
;(require plot)
;(plot-new-window? #t)
;(define (sin-function-fourth h) (function sin 4 0.5))
;(plot (function (sin-function-fourth 0.5) (- pi) pi #:label "sine x")
;        #:x-min -5 #:x-max 5 #:y-min -5 #:y-max 5)
;(function sin-function-fourth 4 0.5)

;3.a)
(define (func-f x) (- (* x x) x 1))

(define (newton-method f x n)
  (if (= n 0)
      x
     (newton-method f
                    (- x (/ (f x) ((der f 0.01) x)))
                    (- n 1))))
(newton-method func-f 2 0)
(newton-method func-f 2 1)
(newton-method func-f 2 2)

;3.b)If I make the n higher, it is closer to the golden ratio of 1.61803.
(newton-method func-f 2 100)
(newton-method func-f 2 1000)

;4)
(define (sqrt-newt c)
  (define (p x) (- (* x x) c))
  (newton-method p 1 40))
(sqrt-newt 612)


;5)(h / 3) * (y_0 + y_n + 4 * (y_1 + y_3 + ... + y_n-1) + 2 * (y_2 + y_4 + ... + y_n-2))
(define (function-1 x) x)
(define (function-2 x) (* x x))
(define (function-4 x) (* x x x x))
(define (divides a b) (= 0 (modulo b a)))
(define (even n) (divides 2 n))

(define (function-f x) (+ x 2))
(define (sum f x n)
  (if (= n 0)
      (f x)
      (if (even (- n 1))
      (+ (* 2 (f (- x 1))) (sum f (- x 1) (- n 1)))
      (+ (* 4 (f (- x 1))) (sum f (- x 1) (- n 1))))))
(sum function-f 2 2)

(define (simpson-rule f a b n)
  (let ((h (/ (- b a) n)))
    (define (yk k) (f (+ a (* k h))))
    (* (/ h 3) (+  (yk n)
                  (sum yk n n)))))

(simpson-rule function-1 0 1 2)
(simpson-rule function-2 0 1 2)
(simpson-rule function-4 0 1 2.0)

;both took a long time to figure out so I ended up making 2
(define (function-1 x) x)
(define (function-2 x) (* x x))
(define (function-4 x) (* x x x x))

(define (sum term a next b)
  (define (iter a result)
    (if (> a b) 
        result
        (iter (next a) (+ (term a) result)))
    )
  (iter a 0))

(define (simpson-rule f a b n)
  (let ((h (/ (- b a) n)))
    (define (yk k) (f (+ a (* k h))))
    (define (constant n) (yk n))
    (define (next n) (+ n 1))
    (* (/ h 3)
       (+ (yk 0)
          ;odd constants
          (* 4 (sum constant 1 next (- n 1)))
          ;even constants
          (* 2 (sum constant 2 next (- n 2)))
          (yk n)))))

(simpson-rule function-1 0 1 2)
(simpson-rule function-2 0 1 2)
(simpson-rule function-4 0 1 2.0)

