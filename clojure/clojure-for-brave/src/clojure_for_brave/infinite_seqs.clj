(ns clojure-for-brave.infinite-seqs)

(defn -main []
  (println "Infinite seqs examples")
  (println (take 10 (repeat "bla")))
  (println (take 10 (repeatedly (fn [] (rand-int 100)))))
  (println (take 10 (iterate #(+ 2 %) 0))))