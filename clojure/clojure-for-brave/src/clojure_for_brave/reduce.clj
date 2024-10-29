(ns clojure-for-brave.reduce)

(defn sum [values]
  (reduce + values))

(defn to-fahrenheit [hash-celsius]
  (let [convertion-fn (fn [new-map [key celsius]]
                        (assoc new-map key (+ 32 (* celsius 1.8))))
        hash-fahrenheit (reduce convertion-fn {} hash-celsius)]
    hash-fahrenheit))

(defn filter-evens [hash]
  (reduce (fn [new-map [key value]]
            (if (even? value)
              (assoc new-map key value)
              new-map))
          {}
          hash))

(defn -main []
  (println "---------------------------")
  (println "Reduce examples")
  (let [values [2 3 5 7 11]
        total (sum values)]
    (println (str "Sum of: " values " = " total)))
  (println "Transforming hash with reduce")
  (let [temperatures {:brasilia 30 :saopaulo 25 :campinas 31}
        temperatures-fahrenheit (to-fahrenheit temperatures)]
    (println (str "Temperatures in celsius: " temperatures))
    (println (str "Temperatures in fahrenheit: " temperatures-fahrenheit)))
  (println "Filtering hash with reduce")
  (let [hash {:a 4 :b 3 :c 10 :d 2}
        evens (filter-evens hash)]
    (println (str "Original hash: " hash))
    (println (str "Even numbers filtered: " evens))))