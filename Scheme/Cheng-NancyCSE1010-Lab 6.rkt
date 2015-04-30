;1)
(define (sum-square lst)
  (define (helper lst counter)
    (if (null? lst)
        counter
        (helper (cdr lst) (+ counter (* (car lst) (car lst))))))
  (helper lst 0))
(sum-square (list 1 2 5 4))

;2)
(define (num-zeros lst)
  (define (helper lst counter)
    (if (null? lst)
        counter
        (if (= (car lst) 0)
            (helper (cdr lst) (+ counter 1))
            (helper (cdr lst) counter))))
  (helper lst 0))
(num-zeros (list 1 5 0 0 0))

;3)
(define (balanced? lst-pair)
  (define (helper lst-pair counter)
    (if (null? lst-pair)
        counter
        (helper (cdr lst-pair) (+ counter (* (car (car lst-pair))
                                             (cdr (car lst-pair)))))))
  (if (= 0 (helper lst-pair 0))
      #t
      #f))
(balanced? (list (cons -2 4) (cons 4 2)))
(balanced? (list (cons 2 6) (cons 1 6)))

;4.a)
(define (swap-first-two lst)
  (cons (car (cdr lst)) (cons (car lst) (cdr (cdr lst)))))
(swap-first-two (list 1 5 2 6))

;4.b)
(define (swap-first-two-modified lst)
  (if (or (null? lst) (null? (cdr lst)))
      lst
      (cons (car (cdr lst)) (cons (car lst) (cdr (cdr lst))))))
(swap-first-two-modified (list 1 5 2 6))
(swap-first-two-modified (list 1))
(swap-first-two-modified (list))

;4.c)
(define (bubble-up lst n)
   (cond ((or (null? lst) (null? (cdr lst))) lst)
         ((if (= n 0)
           lst
   (if (> (car lst) (car (cdr lst)))
       (cons (car (swap-first-two-modified lst)) (bubble-up (cdr (swap-first-two-modified lst)) (- n 1)))
       (cons (car lst) (bubble-up (cdr lst) (- n 1))))))))
 (bubble-up '() 0)
 (bubble-up '(6 3 7 1) 4)

;4.d)
;can use elements to represent length or just length
(define (bubble-sort-aux lst n)
   (if (= n 0)
      lst
      (bubble-sort-aux (bubble-up lst n) (- n 1))))
(bubble-sort-aux '(9 8 7 6 5 4) 6)

;4.e)
(define (bubble-sort lst)
  (bubble-sort-aux lst (length lst)))
(bubble-sort '(6 3 7 1 0))