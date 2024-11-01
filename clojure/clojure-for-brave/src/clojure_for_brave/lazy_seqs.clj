(ns clojure-for-brave.lazy-seqs)

(def numbers-size 1000)

(defn first-number-lazyly []
  (let [numbers (range 0 numbers-size)
        numbers-as-string (map #(str %) numbers)
        first-number (first numbers-as-string)]
    (println (str "Lazly, First number is: " first-number))))

(defn first-number-not-lazyly []
  (let [numbers (range 0 numbers-size)
        numbers-as-string (doall (map #(str %) numbers))
        first-number (first numbers-as-string)]
    (println (str "Not lazyly, First number is: " first-number))))

(defn -main []
  ;If numbers-size is too big, a not lazy sequence could take forever to be evaluated.
  ;Therefore, lazy seqs are very powerful.
  ;No matter how big the sequence is, making it lazy will take minimum time.
  (time (first-number-lazyly)) ;=> "Elapsed time: 0.211761 msecs"
  (time (first-number-not-lazyly))) ;=> "Elapsed time: 0.942852 msecs"
