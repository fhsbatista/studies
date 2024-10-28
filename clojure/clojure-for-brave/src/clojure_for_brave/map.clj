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


(def sum #(reduce + %))

(def avg #(/ (sum %) (count %)))

(defn stats [numbers]
  ;sum and avg are params being passed as functions
  (map #(% numbers) [sum avg]))

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(defn -main []
  (println (map inc [1 2 3])) ;=> [2 3 4]
  ;Passing multiple collections to map, all collections will be interated at once.
  (println (map + [1 2 3] [1 2 3])) ;=> [2 4 6] cuz (1+1 2+2 3+3)
  (calc-sales)
  (let [numbers [2 3 5 7 11]]
    (println (str "Sum: " numbers " = "(sum numbers)))
    (println (str "Avg: " numbers " = "(avg numbers)))
    (println (str "Sum/Avg: " numbers " = "(stats numbers)))
    )
  ;map can be used to evaluate a key of items in a hash map
  (println (map :real identities)))