#lang racket
(require plot)

(define X '(1 3 3 5))
(define Y '(12 12 11 7))

;1.a)
(define (sum-list lst)
  (if (null? lst)
      0
      (+ (car lst) (sum-list (cdr lst)))))

;recursive
(define (sum-recursive lst)
  (define (helper lst counter)
    (if (null? lst) 
        counter
        (helper (cdr lst) (+ (car lst) counter))))
  (helper lst 0))
(sum-list X)
(sum-list Y)

;1.b)
;accumulator style
(define (average lst)
  (define (sum-list lst)
    (define (helper lst accumulator)
      (if (null? lst) 
          accumulator
          (helper (cdr lst) (+ accumulator (car lst)))))
    (helper lst 0))
  (define (leng lst)
    (define (helper lst accumulator)
      (if (null? lst) 
          accumulator
          (helper (cdr lst) (+ accumulator 1))))
    (helper lst 0))
  (/ (sum-list lst) (leng lst)))
(average X)

;recurssive style
(define (recurs-average lst)
  (define (sum-list lst)
    (if (null? lst)
        0
        (+ (car lst) (sum-list (cdr lst)))))
  (define (leng lst)
    (if (null? lst)
        0
        (+ 1 (leng (cdr lst)))))
  (/ (sum-list lst) (leng lst)))

(recurs-average (list 8 3 4))

;1.c)
(define (square lst)
  (map (lambda (x) (expt (- x (average lst)) 2)) lst))
(square X)
(square Y)

;1.d)
(define (standard-deviation lst)
  (sqrt (average (square lst))))
(standard-deviation X)
(standard-deviation Y)

;1.e)
(define (f x y) (+ (* x x) (* y y)))
(define (map2 f lstx lsty)
  (if (or (null? lstx) (null? lsty))
      (list)
      (cons (f (car lstx) (car lsty))
            (map2 f (cdr lstx) (cdr lsty)))))
(map2 f '(2 6 6) '(1 2 5))

;1.f)
(define (covariance x y)
  (define (helper x y avg-x avg-y)
    (if (null? x)
        '()
        (cons (* (- (car x) avg-x)
                 (- (car y) avg-y))
              (helper (cdr x) (cdr y) avg-x avg-y))))
  (helper x y (average x) (average y)))

(define (covariance x y)
  (if (or (null? x) (null? y))
      '()
      (cons (* (- (car x) (average x))
               (- (car y) (average y)))
            (covariance (cdr x) (cdr y)))))
(covariance X Y)

;1.g)
(define (pearson x y)
  (/ (average (covariance x y))
     (* (standard-deviation x) (standard-deviation y))))
(pearson X X)
(pearson Y Y)

;2.a)
(define x '(160 180 200 220 240 260 280))
(define y '(126 103 82 75 78 40 20))

(define (func x y)
  (define b (* (pearson x y)
               (/ (standard-deviation y)
                  (standard-deviation x))))
  (define a (- (average y) (* b (average x))))
  (cons a b))
(func x y)

;2.b)
(define a (car (func x y)))
(define b (cdr (func x y)))
;returns y
(define (fitline x)
    (+ (* b x) a))

;2.c)
(define (g x y) (vector x y))
(define (zip2 lst1 lst2)
  (map2 g lst1 lst2))

(require plot)
(plot (list (axes)
            (function fitline 140 300)
            (points (zip2 x y))))

;3)
(define (merge lst1 lst2)
  (cond ((null? lst1) lst2)
        ((null? lst2) lst1)
        (else (if (> (car lst1) (car lst2))
         (cons (car lst2) (merge lst1 (cdr lst2)))
         (cons (car lst1) (merge (cdr lst1) lst2))))))
(merge '(5 8 9) '(1 2 3))
(merge '() '(5 6 7))