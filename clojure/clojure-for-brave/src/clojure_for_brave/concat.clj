(ns clojure-for-brave.concat)

(defn -main []
  (println (concat [1 2] [3 4]))) ;=> (1 2 3 4)