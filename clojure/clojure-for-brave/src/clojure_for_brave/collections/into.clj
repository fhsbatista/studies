(ns clojure-for-brave.collections.into)

(defn converting-seq-examples []
  (println "")
  (println "Examples of using (into) to CONVERT sequences")
  (println "Can be used to force \"sequence\" functions return something you want instead of seq")
  (println "Forcing \"(map)\" to return a hash-map")
  (println "(into [] (map #(* 2 %) [1 2 3 4])) ;=> [2 4 6 8]")
  (println (into [] (map #(* 2 %) [1 2 3 4]))) ;=> [2 4 6 8]
  (println "If we don't use (into), a seq will be returned:")
  (println "(map #(* 2 %) [1 2 3 4]) ;=> (2 4 6 8)")
  (println (map #(* 2 %) [1 2 3 4]))) ;=> (2 4 6 8)

(defn merging-examples []
  (println "")
  (println "Examples of using (into) to MERGE sequences")
  (println "Merging two vectors")
  (println "(into [1 2] [3 4]): ;=> [1 2 3 4]")
  (println (into [1 2] [3 4])) ;=> [1 2 3 4]
  (println "We can merge two different kinds of sequences")
  (println "(into [1 2] '(3 4)) ;=> [1 2 3 4]")
  (println (into [1 2] '(3 4)))) ;=> [1 2 3 4]

(defn -main []
  (println "----------------------------------")
  (println "Collection method examples: (into)")
  (converting-seq-examples)
  (merging-examples))