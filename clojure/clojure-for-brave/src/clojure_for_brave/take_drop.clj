(ns clojure-for-brave.take-drop
  (:require [clojure-for-brave.take-drop :as take-drop]))

(defn example-take-drop []
  (println "-----------------------")
  (println "Take and drop examples")
  (println (take 3 [1 2 3 4 5])) ;=> [1 2 3] returns first n elements
  (println (drop 3 [1 2 3 4 5]))) ;=> [4 5] removes first n elements and return the sequence)

(defn example-take-while []
  (println "-----------------------")
  (println "Take while example")
  (println "Take keeps taking elements until predicate is true")
  (let [numbers [1 2 3 4 5]
        taked (take-while #(< % 3) numbers)]
    (println taked) ;=> [1 2]
    ))

(defn example-drop-while []
  (println "-----------------------")
  (println "Drop while example")
  (println "Drop keeps droping elements until predicate is true")
  (let [numbers [1 2 3 4 5]
        taked (drop-while #(< % 3) numbers)]
    (println taked) ;=> [3 4 5]
    ))

(defn -main []
  (example-take-drop)
  (example-take-while)
  (example-drop-while))