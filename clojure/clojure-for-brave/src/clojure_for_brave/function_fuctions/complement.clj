(ns clojure-for-brave.function-fuctions.complement)

(def odd?
  (complement even?))

(defn positive? [x]
  (>= x 0))

(def negative? (complement positive?))

(defn example-filter []
  (let [numbers [-1 0 5 10 -2 -3]]
    (println "Positive numbers: " (filter positive? numbers))
    (println "Negative numbers: " (filter negative? numbers))))

(defn -main []
  (println "---------------------------")
  (println "(complement) examples")
  (println "(complement) to invert boolean functions")
  (println (str "3 is even? " (even? 3)))
  (println (str "3 is odd? " (odd? 3)))
  (example-filter))