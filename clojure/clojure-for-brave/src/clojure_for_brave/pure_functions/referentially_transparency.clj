(ns clojure-for-brave.pure-functions.referentially-transparency)

(defn sum-numbers [& numbers]
  (reduce + numbers))

(defn subtract-numbers [& numbers]
  (let [value (reduce - numbers)]
    (println value)
    value))



(defn -main []
  ; This is referentially-transparent because for the same input it will always return the same output
  ; And also it doen't have any side-effect
  ; Therefore, it is a pure function
  (sum-numbers 1 2 3)
  ;This below, despite being referentially transparent, it generetates side effects due to println call
  ;Therefore, is not a pure function
  (subtract-numbers 1 2 3))