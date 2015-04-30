(define (make-tree value left right) (list value left right))
(define (value tree) (car tree))
(define (left tree) (cadr tree))
(define (right tree) (caddr tree))
;1)
(define (tree-equal? t1 t2)
  (cond ((and (null? t1) (null? t2)) #t)
        ((null? t1) #f)
        ((and (= (value t1) (value t2))
              (tree-equal? (left t1) (left t2))
              (tree-equal? (right t1) (right t2)))
          #t)
        (else #f)))
(tree-equal? '() (make-tree 5 '() '()))
(tree-equal? (make-tree 4 (list 1 (list 2 '() '()) '()) (list 5 '() '()))
             (make-tree 4 (list 1 (list 2 '() '()) '()) (list 5 '() '())))

;2.a)
(define (insert-list L T)
(define (insert n T)
  (cond ((null? T)
         (make-tree n '() '()))
        ((= n (value T)) T)
        ((> n (value T))
         (make-tree (value T) (left T) (insert n (right T))))
        ((< n (value T))
         (make-tree (value T) (insert n (left T)) (right T)))))
  (cond ((null? L) T)
        (else (insert-list (cdr L) (insert (car L) T)))))
(insert-list (list 3 5 9) (make-tree 2 '() '()))

;2.b)
(define (sort-extract T)
  (cond ((null? T) '())
        (else (append (sort-extract (left T))
                (list (value T))
                (sort-extract (right T))))))
(sort-extract (make-tree 2 (list 1 '() '()) (list 4 '() '())))

;2)
(define (tree-sort L)
  (sort-extract (insert-list L '())))
(tree-sort (list 9 2 0 3 5 1))

;3)
(define (delete T v)
  (define (last-element l)
    (cond ((null? (cdr l)) (car l))
          (else (last-element (cdr l)))))
  (define (remove-last t)
    (cond ((null? t) '())
          ((null? (right t)) (left t))
          (else (make-tree (value t) (left t) (remove-last (right t))))))
  (cond ((null? T) '())
        ((= v (value T))
         (cond ((and (null? (left T)) (null? (right T))) '())
               ((null? (left T)) (right T))
               ((null? (right T)) (left T))       
               (else (make-tree 
                      (last-element (sort-extract (left T)))
                      (remove-last (left T))
                      (right T)))))
        ((> v (value T)) (make-tree (value T) (left T) (delete (right T) v)))
        ((< v (value T)) (make-tree (value T) (delete (left T) v) (right T)) v)))
(delete (make-tree 5 (list 3 (list 1 '() '()) (list 4 '() '()))
                   (list 6 '() (list 8 '() '()))) 5)

;4)
(define (g x) (* x x))
(define (tree-map T f)
  (cond ((null? T) '())
        (else (make-tree (f (value T))
                         (tree-map (left T) f)
                         (tree-map (right T) f)))))
(tree-map (make-tree 2 (list 1 '() '()) (list 9 '() '())) g)

;5)
(define example (list #\+ (list #\*
                                (list 4 '() '())
                                (list 5 '() '()))
                      (list #\+
                            (list #\/ (list 6 '() '()) '())
                            (list 7 '() '()))))

(define (parse T)
  (cond ((null? T) '())
        ((and (null? (left T)) (null? (right T)))
         (value T))
        ((and (not (null? (left T))) (not (null? (right T))) (equal? (value T) #\+))
         (+ (parse (left T)) (parse (right T))))
        ((and (not (null? (left T))) (not (null? (right T))) (equal? (value T) #\*))
         (* (parse (left T)) (parse (right T))))
        ((and (null? (right T)) (equal? (value T) #\-))
         (- (parse (left T))))
        ((and (null? (right T)) (equal? (value T) #\/))
         (/ 1 (parse (left T))))))

(parse example)        
(parse (list #\+ (list #\* (list 2 '() '()) (list 3 '() '()))
             (list #\* (list #\+ (list 4 '() '()) (list #\- (list 5 '() '()) '()))
                   (list #\/ (list 6 '() '()) '()))))

;6.a)
(define example2 (list #\+
                       (list #\* (list 2 '() '()) (list 3 '() '()))
                       (list #\* (list #\+ (list 4 '() '()) (list #\- (list 5 '() '()) '()))
                             (list #\/ (list 6 '() '()) '())))) 
(define (prepare x)
  (cond ((number? x) (number->string x))
        ((char? x) (string x))))

(define (prefix t)
  (define (all-strings t)
    (tree-map t prepare))
  (define (tree-->list t)
    (if (null? t)
        '()
        (append (string->list (value t))
                (tree-->list (left t))
                (tree-->list (right t)))))
 (list->string (tree-->list (all-strings t))))
(prefix example2)

;6.b)
(define (post-order t)
  (define (all-strings t)
    (tree-map t prepare))
  (define (tree-->list2 t)
    (if (null? t)
        '()
        (append (tree-->list2 (left t))
                (tree-->list2 (right t))
                (string->list (value t)))))
  (list->string (tree-->list2 (all-strings t))))
(post-order example2)