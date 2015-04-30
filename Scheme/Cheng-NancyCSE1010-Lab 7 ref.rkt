;1)
(define (book name number left right)
  (list (cons name number) left right))
(book "macrusa" 3582 '() '())

;2)
(define (get-name book)
  (car (car book)))
(get-name (book "macrusa" 3582 '() '()))

(define (get-number book)
  (cdr (car book)))

(define (get-smaller book)
  (car (cdr book)))

(define (get-larger book)
  (car (cdr (cdr book))))

;3)
(define (insert tree name number)
  (cond ((null? tree)
         (book name number '() '()))
        ((string=? name (get-name tree)) tree)
        ((string>? name (get-name tree))
         (book (get-name tree) (get-number tree) (get-smaller tree)
               (insert (get-larger book) name number)))
        ((string<? name (get-name tree))
         (book (get-name tree) (get-number tree)
               (insert (get-smaller book) name number) (get-larger tree)))))
(insert (book "macrusa" 358 '() '()) "laludia" 798)