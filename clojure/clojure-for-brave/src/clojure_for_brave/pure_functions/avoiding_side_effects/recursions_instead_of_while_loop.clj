(ns clojure-for-brave.pure-functions.avoiding-side-effects.recursions-instead-of-while-loop)

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulated]
   (if (empty? vals)
     accumulated
     (sum (rest vals) (+ (first vals) accumulated)))))

(defn -main []
  (sum [1 2 3])
  )


;sum is called recursivelly
;this is what the stack would look like
;see that we don't have a variable to accumulate the sum
;we set the accumalted value as an argument instead to call the sum again recursivelly

;(sum [1 2 3])
;(sum [2 3] 1)
;(sum [3] 3)
;(sum [] 6)
; => 6