(ns clojure-for-brave.vector)

(defn merge-vectors [merged merging]
  (let [final-vector (into merged merging)] 
       (println (str merged " + " merging " = " final-vector))))

(defn -main 
  []
  (merge-vectors [1 2 3] [4]))