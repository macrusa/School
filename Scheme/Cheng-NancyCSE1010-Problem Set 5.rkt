;1)
(define (function-max pair)
  (lambda (x)
       (if (> ((car pair) x) ((cdr pair) x))
          ((car pair) x)
          ((cdr pair) x))))

(define (func-max pair)
  (define (funcs f g)
    (lambda (x)
      (if (< (f x) (g x))
          (g x)
          (f x))))
  (funcs (car pair) (cdr pair)))

(function-max (cons (lambda (x) x) (lambda (x) (* -1 x))))
((function-max (cons (lambda (x) x) (lambda (x) (* -1 x)))) 2)

;2)
(define (zip l1 l2)
  (if (or (null? l1) (null? l2))
      '()
      (cons (cons (car l1) (car l2)) 
            (zip (cdr l1) (cdr l2)))))
(zip (list 1 2) (list 4 5))

;3)
(define (unzip lst)
  (if (null? lst)
      '('() '())
      (cons (cons (car (car lst)) (unzip (car (cdr lst))))
            (cons (cdr (car lst)) (unzip (cdr (cdr lst)))))))
(unzip (list (cons 3 4) (cons 6 9)))
       
(cons (list 1 2 3) (list 2 5 6))

;4)
;Doing a system of (use only 1 penny --> 11 pennies)
;If the number is in the list, it is possible (e.i) to use 6 pennies and 1 nickel
(define (occurrence x lst)
       (if (null? lst)
           0
            (if (equal? x (car lst))
                (+ 1 (occurrence x (cdr lst)))
                (occurrence x (cdr lst)))))

(define (change k l)
  (+ (occurrence k (list (cdr l)))
  (if (= k 0)
      1
     (+ (occurrence (- k (car l)) l) (change (- k (car l)) l)))))
(change 11 (list 1 5 10 25))

;5.a)
(define (ecode cons)
  (define (helper x y)
    (+ (* (/ 1 2) (+ x y) (+ x y 1)) y))
  (helper (car cons) (cdr cons)))
(ecode (cons 5 1))

;other method is using let
(define (ecode2 pair)
  (let ((x (car pair))
        (y (cdr pair)))
    (+
     (* 0.5
        (+ x y)
        (+ x y 1))
     y)))
(ecode2 (cons 2 4))
        
;5.b)
(define (decode z)
  (let* ((w (floor (/ (- (sqrt (+ (* 8 z) 1)) 1) 2)))
         (t (/ (+ (* w w) w) 2))
         (y (- z t))
         (x (- w y)))
    (cons x y)))
(decode 25)