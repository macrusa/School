(define (first str) (car str))
(define (rest str) ((cdr str)))

;1)
(define (triangle-stream n c)
  (cons (+ n c) (lambda () (triangle-stream  (+ c n) (+ c 1)))))
(define t (triangle-stream 0 1))
t
(rest t)
(rest (rest t))
(rest (rest (rest t)))
(newline)

;2)
(define (square-stream c)
  (cons (* (+ c 1) (+ c 1)) (lambda () (square-stream (+ c 1)))))
(define s (square-stream 0))
s
(rest s)
(rest (rest s))
(rest (rest (rest s)))
(newline)

;3)
(define (geometric-stream a r c)
  (cons (* a (expt r c)) (lambda () (geometric-stream a r (+ c 1)))))
(define g (geometric-stream 3 2 0))
g
(rest g)
(rest (rest g))
(rest (rest (rest g)))
(newline)

;4)
(define (pizza-stream c)
  (cons (/ (+ (* c c) c 2) 2) (lambda () (pizza-stream (+ c 1)))))
(define p (pizza-stream 0))
p
(rest p)
(rest (rest p))
(rest (rest (rest p)))