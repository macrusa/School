;1.a)
(define (number-sum n)
  (if (= n 0)
      0
      (+ (* n n) (number-sum (- n 1)))))
(number-sum 4)

;1.b)
(define (number-sum n)
  (if (= n 0)
      0
      (+ (* 2 n) (number-sum (- n 1))))) 
(number-sum 4)
(number-sum 1)
(number-sum 2)
(number-sum 3)
(number-sum 4)
(number-sum 5)
(number-sum 6)
(number-sum 7)

;2)
(define (frac-mult n)
  (if (= n 1)
      1
      (* (- 1 (/ 1 n)) (frac-mult (- n 1)))))
(frac-mult 4)

;3)
(define (divides a b) (= 0 (modulo b a)))
(define (divisors-upto n k)
  (cond ((= k 0) 0)
        ((= n 0) 0) 
        ((= k 1) 1)
        ((divides k n) (+ 1(divisors-upto n (- k 1))))
        ((not (divides k n)) (divisors-upto n (- k 1)))))
(define (divisors n) (divisors-upto n n))
(divisors 4) 


;4)
(define (sum-frac x)  
  (if (= x 0)
      0
      (+ (/ (* 4.0 (expt -1 (+ x 1)))
        (- (* 2 x) 1))
     (sum-frac (- x 1)))))
(sum-frac 100) 
(sum-frac 100000)

;5.a) Expt was called 300 times.The program kept calling the expt and computed either a -1 or +1 depending wheather it was an even or odd term. It was called until x = 0.
;5.b) I'm redefining sum-frac x and making a new variable to alter the -1 and +1.
(define (neg x)(if (= 0 (modulo x 2))
                    -1
                    1))
(define (sum-frac x)  
  (if (= x 0)
      0
      (+ (/ (* 4.0 (neg x))
            (- (* 2 x) 1))
         (sum-frac (- x 1)))))
(sum-frac 100)
(sum-frac 100000)

;6) When I changed the "if" to a "new-if," the program ran out of memory because it went into a infinite recursion. The "new-if" is a procedure not a special function like "if" meaning that the arguments were evaluated first (procedure, then-clause, and else-clause). That is why for the (new-if (= 0 0) 4 5) it worked (the arguments could be evaluated). The interpreter evaluated the procedure and its operands. However, for the factorial, it tried to evaluate the arguments which resulted in a infinite recursion because it was unable to evaluate the infinite else clause.
(define (new-if predicate then-clause else-clause)
       (if predicate then-clause else-clause))
(new-if (= 0 0) 4 5)
(new-if (= 0 1) 4 5)

(define (factorial n)
  (new-if (= n 0)
      1
      (* n (factorial (- n 1)))))
(factorial 0)

;7) factorial was defined above
(define (factorial x) (if (= x 0)
                          1
                          (* x (factorial (- x 1)))))
(define (new-sin x n)
  (if (= n 0)
      x
      (+ (/ (* (expt -1 n) 
            (expt x (+ (* 2 n) 1)))
            (factorial (+ (* 2 n) 1)))
         (new-sin x (- n 1)))))
(new-sin 2 3) 