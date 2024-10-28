(ns clojure-for-brave.map)

(defn sale-info [item value]
  (str "Product: " item " Total: $" value))

(defn calc-sales []
  (let [items [:apples :batteries :breads]
        prices [1.0 2.25 0.5]
        quantities [5 1 10]
        sales-value (map * prices quantities)
        receipt (map sale-info items sales-value)]
    (doseq [item receipt]
      (println item))))

(defn -main []
  (println (map inc [1 2 3])) ;=> [2 3 4]
  ;Passing multiple collections to map, all collections will be interated at once.
  (println (map + [1 2 3] [1 2 3])) ;=> [2 4 6] cuz (1+1 2+2 3+3)
  (calc-sales))