(ns clojure-for-brave.destructuring
  (:require [clojure.string :as str]))

(defn list-only-first
  [[first-thing]]
  (println first-thing))

(defn list-two-firsts
  [[first second & others]]
  (println (str "First is: " first))
  (println (str "Second is: " second))
  (println "And others are:" (str/join ", " others)))

(defn map-only-first
  [{first :first second :second}]
  (println first))

(defn map-two-firsts
  [{cat :cat dog :dog}]
  (println (str "Cat's name is: " cat))
  (println (str "Dog's name is: " dog)))

(defn map-two-firsts-b
  [{:keys [cat dog] :as animals}] ;alias can be omitted.
  (println (str "Cat's name is: " cat))
  (println (str "Dog's name is: " dog)))

(defn -main
  "Destructuring examples"
  [& args]
  (println "Examples of destructuring")
  (list-only-first ["bread" "oven" "butter"])
  (list-two-firsts ["Mug" "Dog" "House" "Hair"])
  (map-only-first {:first "Cat"})
  (map-two-firsts {:cat "Garfield" :dog "Amendoim"})
  (map-two-firsts-b {:cat "Garfield" :dog "Amendoim"}))