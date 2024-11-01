(ns clojure-for-brave.sort-sortby)

(defn example-sort []
  (println "-----------------------")
  (println "(sort) example")
  (println "(sort) sorts in ascending order")
  (let [numbers [3 1 2]
        sorted (sort numbers)]
    (println (str "Sorting " numbers ": " sorted))))

(defn example-sort-by []
  (println "-----------------------")
  (println "(sort-by) example")
  (println "(sort-by) sorts in ascending order and allows you to pass a function that determines the order")
  (let [numbers ["aaa" "bb" "c"]
        sorted (sort-by count numbers)] ; Sorting from the shortest word to the longest.
    (println (str "Sorting from shortest to longest " numbers ": " sorted)))) ;=> ["c" "bb" "aaa"]


(defn -main []
  (example-sort)
  (example-sort-by))