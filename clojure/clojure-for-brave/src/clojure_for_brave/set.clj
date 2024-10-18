(ns clojure-for-brave.set)

(defn add-set [x y]
  (println(str "Merge of set " x "with " y "is: " (into x y))))

(defn -main []
  (add-set (set [:cat :dog]) (set [:tiger :dog])))