;1.a)
(define (ancestors n) 
  (if (= n 1)
      2
      (* 2 (ancestors (- n 1)))))
(ancestors 4) 

;1.b)Sn = Sn-1 + an
(define (num-ancestors n)
  (if (= n 1)
      2
  (+ (num-ancestors (- n 1))
     (ancestors n))
  ))
(num-ancestors 4)

;2.a) Pn
(define (pell-num n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        (else (+ (* 2 (pell-num (- n 1))) (pell-num (- n 2))))))
(pell-num 3)

;2.b) Qn
(define (com-pell-num n)
  (cond ((= n 0) 2)
        ((= n 1) 2)
        (else (+ (* 2 (com-pell-num (- n 1))) (com-pell-num (- n 2))))))
(com-pell-num 2)

;2.c) (Qn/2)/Pn
(define (rational-approx n) 
  (/ (/ (com-pell-num n) 2) (pell-num n)))
(rational-approx 6)

;3.a)
(define (power base exp)
  (cond ((= exp 0) 1)
        (#t (* base (power base (- exp 1))))))
(power 10 10)
(define (square x) (* x x))
(define (divides a b) (= 0 (modulo b a)))
(define (fastexp b e)
  (cond ((= e 0) 1)
        ((divides e 2)(square (expt b (/ e 2))))
        ((not (divides e 2)) (* b (expt b (- e 1))))))
(fastexp 3 2)

;3.b)e = 2^k since the computer writes exponents into powers of 2 as shown in e = 2^k, fastexp is faster because it makes fewer calls to the function by dividing by two. Since e is in the form 2^k, it is faster for the computer to compute the result rather than having it do the computation directly. From the slower function which is (2^k --> 2^k - 1 --> 2^k -2) it takes more steps while the fastexp is (2^k --> 2^(k-1) --> 2^(k-2)) Doing the exp with exponnets of 2 is faster.

;4) Built in (expt base exponent)
;When x=9 and n=9 it is roughly two but the square root of 9 is 3. When x=9 and n=100, the answer approaches 3 but is not qutie there yet. So the larger n gets, the closer x gets to the square root.
(define (new-sqrt x n)
  (if (= n 1)
      1
      (/ (- x 1) (+ 2 (new-sqrt x (- n 1))))))
(new-sqrt 9 9)
(new-sqrt 9 100)

;5) My simulation approaches about 20%
(#%require (only racket/base random))
(define (rolling-number n)
(define (helper n counter)
    (if (= n 0)
        counter
        (let ((x (+ (random 6) 1))
               (y (+ (random 6) 1)))
               (if (or (= (+ x y) 7)
                    (= (+ x y) 11))
                       (helper (- n 1) (+ 1 counter))
                       (helper (- n 1) counter)))))
(/ (helper n 0) n))
(rolling-number 1000000)


;redo
(#%require (only racket/base random))
(define (rolling-dice-percentage n)
  (define (dice-score n c)
    (if (= n 0)
        c
        (let ((x (+ (random 6) 1))
              (y (+ (random 6) 1)))
              (if (or (= (+ x y) 7) (= (+ x y) 11))
                  (dice-score (- n 1) (+ c 1))
                  (dice-score (- n 1) c)))))
  (/ (dice-score n 0) n))
(rolling-dice-percentage 70)

;(let (defining variables) (function)

