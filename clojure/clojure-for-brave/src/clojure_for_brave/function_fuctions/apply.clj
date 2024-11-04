(ns clojure-for-brave.function-fuctions.apply)

(defn example-max []
  (println "Using apply to get max in seq items as they were individual arguments")
  (println "(apply max [0 1 2 3]) => 3")
  (println (apply max [0 1 2 3]))) ;=> 3

(defn example-sum []
  (println "Using apply sum items from a seq")
  (println "(apply + [0 1 2 3]) => 6")
  (println (apply + [0 1 2 3]))) ;=> 6

(defn -main []
  (println "---------------------------")
  (println "(apply) examples")
  (example-max)
  (example-sum))