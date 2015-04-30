;1)
(define (h-function n) 
  (if (= n 1)
     1
      (+ (/ 1 n) (h-function (- n 1)))))
(define (euler-constant n)
  (abs (- (h-function n) (log n))))
(euler-constant 1000)

;2.a)
(define (Lucas n)
  (cond ((= n 0.0) 2.0)
        ((= n 1.0) 1.0)
        ((> n 1.0) (+ (Lucas (- n 1.0)) (Lucas (- n 2.0))))))
(Lucas 1)
(Lucas 2)
(Lucas 3)

(define (Fibonacci n)
  (cond ((= n 0.0) 1.0)
        ((= n 1.0) 1.0)
        ((> n 1.0) (+ (Fibonacci (- n 1.0)) (Fibonacci (- n 2.0))))))
(Fibonacci 1)
(Fibonacci 2)
(Fibonacci 3)

;2.b) Lucas-ratio 20, 21 and 22 are roughly the same. The Lucas-ratio and the Fibonacci-ratio are roughly the same too.
(define (Lucas-ratio n) (/ (Lucas n) (Lucas (- n 1))))
(Lucas-ratio 20)
(Lucas-ratio 21)
(Lucas-ratio 22)

(define (Fibonacci-ratio n) (/ (Fibonacci n) (Fibonacci (- n 1))))
(Fibonacci-ratio 20)
(Fibonacci-ratio 21)
(Fibonacci-ratio 22)

;2.c) It took a long time computing each Lucas function. It got even longer as the number increased. I think Lucas 50 will take even longer to compute than Lucas 30, Lucas 35 and Lucas 40.
(define (fast-Lucas-help n k luc-a luc-b) 
  (if (= n k)
      luc-a
      (fast-Lucas-help n (+ k 1) (+ luc-a luc-b) luc-a)))
(define (fast-Lucas n) (fast-Lucas-help n 1 1 2))
(fast-Lucas 1)
(fast-Lucas 2)
(fast-Lucas 3)

;k = 1      0            0
;k = 2      2            1
;k = 3      4            2
;k = 4      6            3
;k = 5      8            4
;k = 6      10           5

;3.a)
(define (golden-ratio n) 
  (cond ((= n 1.0) 2.0)
        ((> n 1.0) (+ 1.0 (/ 1.0 (golden-ratio (- n 1.0)))))))
(golden-ratio 1000)

;3.b)
(define (root-golden-ratio n)
  (cond ((= n 0) 1)
        (else (sqrt (+ 1 (root-golden-ratio (- n 1)))))))
(root-golden-ratio 1000)

;4)The expression (- (* 2 (random)) 1) works because since (random) generates a floating point value between 0 and 1, multiplying both sides by 2 will give the bounds 0 to 2. When we subtract by 1, the new bounds will become -1 to 1.
(#%require (only racket/base random))
(define (random-x-coor) (- (* 2 (random)) 1))
(define (random-y-coor) (- (* 2 (random)) 1))
(define (pi n)  
  (define (darts n counter)
    (if (= n 0.0)
        counter
        (let  ((x (expt (random-x-coor) 2))
               (y (expt (random-y-coor) 2)))
               (if (> (+ x y) 1.0)
               (darts (- n 1.0) counter)
               (darts (- n 1.0) (+ counter 1))))))
(* (/ (darts n 0) n) 4.0))
(pi 10000)