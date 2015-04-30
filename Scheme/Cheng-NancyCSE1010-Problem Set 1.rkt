;1)
(* (+ 22 42) (* 54 99))
(* (* (+ 22 42) 54) 99)
(+ (* 64 102)
   (* 16 
   (/ 44 22)))
;2.a) The expressions evaluate to the same number because of PERMDAS. We follow these rules but the computer does not. That is why we have to use parenthesis to tell the computer which rules to do first. We have to isolate the operations by using parenthesis such as in (* (+ 22 42) (* 54 99)), we isolate the addition multiplication first because they are in parenthesis. Also, SCHEME is written in prefix notation meaning that the expression will have to go first before the argument. So, we have to tell the computer which numbers to add, multiple, subtract, or divide first. In the second expression, we have to do (* (* (+ 22 42) 54) 99) because we only have to isolate the parenthesis then multiply. The placement of the parenthesis matter because it tells the computer to compute which set or group of numbers first.
;2.b) Rules of precedence are necessary because SCHEME uses prefix notation meaning the function will always be before the arguments. If we enter 3 + 4 * 5, we have end up with 3 #<procedure:+> 4 #<procedure:*> 5. That's because SCHEME evaluates any number or function as itself. 3 is a number, + is a procedure, 4 is a number, * is a procedure and 5 is a number again. 
3 + 4 * 5
;3.a)
(define (inc x) (+ x 1))
(inc 7)
;3.b)
(define (inc2 x) (inc (inc x)))
(inc2 9)
;3.c)
(define (fourth x) (* x x x x))
(fourth 2)
;3.d)
(define (p x) (* (+ (* x x x x x) 
                    (* 16 x x x x) 
                    (* 22 x x x) 
                    x 
                    9)
                 (+ (* x x x x x)
                    (* 16 x x x x)
                    (* 22 x x x)
                    x
                    9)))
(p 1) 
;3.e)
(define (sixteenth x) (fourth (fourth x)))
(sixteenth 4)
;((#^4)^4)
;3.f)See in the comments boxes.
;above we defined fourth, sixteenthand evaluated sixteenth
;/-----------------------\
;| /-------------------\ |
;| |/----------------\ | |
;| |  fourth --> x^4 | | |
;| ||                | | |
;| |\----------------/ | |
;| |sixteenth-->(x^4)^4| |
;| \-------------------/ |
;|sixteenth-->(4^4)^4=4^16|
;\-----------------------/
;3.g) 
;define a new variable called twofivesix because sixteenth x was x^16,(x^16)^16 = x^256. Multiplying exponnets will be ((x^16)^16)
(define (twofivesix x) (sixteenth (sixteenth x)))
(twofivesix 2)
;Remark
(define (sixteenth x) (* x x x x x x x x x x x x x x x x))
(define (twofivesix x) (* x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x
                       x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x))
(twofivesix 2)
;4)The difficulty of defining x^256 using only "*" is that we would have to type "x" 256 times. For example in the remark, (define (twofivesix x) (* x x ... x256)). We can use (define (twofivesix x) (sixteenth (sixteenth x))) and find x^256 much faster. As shown in problem 3.d) it was time consuming process to write multiple x's and high chance of error if we miss one "x". ((x^16)^16)


