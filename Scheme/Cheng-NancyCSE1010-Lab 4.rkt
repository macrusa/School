;1)
(define (zeno n) (/ 1 (expt 2 n)))
(define (sum f n)
  (if (= n 1)
      (f 1)
      (+ (f n) (sum f (- n 1)))))
(sum zeno 5)

;2.a) 
(define (g-sum f i n)
  (if (= i n)
      (f i)
      (+ (f n) (g-sum f i (- n 1)))))

;2.b.i)
(g-sum zeno 1 5)

;2.b.ii)
(define (geometric f i n) (g-sum f i n))
(geometric zeno 0 5)

;3.a) (define (name function/sequence test number) ()
;index is a counter in the sequence function
; doing found + 1 = n b/c test satisfies so got to number add 1
(define (find sequence test n)
  (define (helper seq-num found)
    (cond ((and (test (sequence seq-num)) (= n (+ found 1)))
           (sequence seq-num))
          ;found so print (function's sequence term)
          ((test (sequence seq-num))
           (helper (+ seq-num 1) (+ found 1)))
          ;found but not correct n so keep going - add one to index to continue in sequence and add one to found
          (else
           (helper (+ seq-num 1) found))))
          ;did not find so continue in sequence - add one to sequence term
  (helper 1 0))

;3.b)
(define (natural-seq x) x)
(define (divides a b) (= 0 (modulo b a)))
(define (even n)
  (divides 2 n))
(define (odd n)
  (not (divides 2 n)))

(find natural-seq even 3)

;3.c)
(define (Fibonacci n)
  (cond ((= n 0.0) 1.0)
        ((= n 1.0) 1.0)
        ((> n 1.0) (+ (Fibonacci (- n 1.0)) (Fibonacci (- n 2.0))))))

;only composite if anything between that number can divide it
(define (divides? n k)
  (if (<= k 2)
      #f
      (if (even n)
          #t
          (divides? n (- k 1)))))
(define (prime n) (not (divides? n (- n 1))))
;n/(n-1) if anything between there divides evenly, it is composite
(prime 700)

(find Fibonacci prime 5)

;4)
#lang racket
(require plot)
(plot-new-window? #t)
(define (f x) (+ (* (sin x) (- 3 x)) 1))
(plot (function f (- pi) pi #:label "sine x")
      #:x-min -5 #:x-max 5 #:y-min -5 #:y-max 5)