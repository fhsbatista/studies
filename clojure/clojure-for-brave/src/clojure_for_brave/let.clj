(ns clojure-for-brave.let
  (:require [clojure.string :as str]))

(defn simple-example
  [x]
  (let [value x] ;binds x to "value"
    (println (str "Binded in 'value' is: " value))))

(defn bit-more-complicated-example
  [& names]
  (let [first-2-names (take 2 names)]
    (println (str "First 2 names are: " (str/join ", " first-2-names)))))

(defn let-with-rest-example
  [& names]
  (let [[first & others] names]
    (println (str "First name is: " first))
    (println (str "Other names are: " (str/join ", " others)))))

(defn -main
  []
  (simple-example 5)
  (bit-more-complicated-example "Cacau" "Mel" "Meg")
  (let-with-rest-example "Cacau" "Mel" "Meg"))