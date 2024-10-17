(ns clojure-for-brave.closure)

(defn increment
  [value]
  #(+ % value))

(def add-3-to
  (increment 3))

(defn -main
  []
  (println (add-3-to 10) " -> calculated via closure")) ;13