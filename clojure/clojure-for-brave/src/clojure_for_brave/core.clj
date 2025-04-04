(ns clojure-for-brave.core
  (:require [clojure-for-brave.vector :as vector]
            [clojure-for-brave.destructuring :as descructuring]
            [clojure-for-brave.anonymous :as anonymous]
            [clojure-for-brave.closure :as closure]
            [clojure-for-brave.let :as let]
            [clojure-for-brave.set :as set]
            [clojure-for-brave.loops :as loop]
            [clojure-for-brave.regex :as regex]
            [clojure-for-brave.sequences.sequences :as sequences]
            [clojure-for-brave.map :as map]
            [clojure-for-brave.sequences.reduce :as reduce]
            [clojure-for-brave.sequences.take-drop :as take-drop]
            [clojure-for-brave.sequences.filter-some :as filter-some]
            [clojure-for-brave.sequences.sort-sortby :as sort-sortby]
            [clojure-for-brave.concat :as concat]
            [clojure-for-brave.sequences.lazy-seqs :as lazy-seqs]
            [clojure-for-brave.sequences.infinite-seqs :as infinite-seqs]
            [clojure-for-brave.collections.into :as into]
            [clojure-for-brave.function-fuctions.apply :as apply]
            [clojure-for-brave.function-fuctions.partial :as partial]
            [clojure-for-brave.function-fuctions.complement :as complement]
            [clojure-for-brave.pure-functions.referentially-transparency :as referentially-transparency]
            [clojure-for-brave.pure-functions.avoiding-side-effects.recursions-instead-of-while-loop :as avoiding-side-effects]))

(defn -main
  "Examples"
  [& _]
  (vector/-main)
  (descructuring/-main)
  (anonymous/-main)
  (closure/-main)
  (let/-main)
  (set/-main)
  (loop/-main)
  (regex/-main)
  (sequences/-main)
  (map/-main)
  (reduce/-main)
  (take-drop/-main)
  (filter-some/-main)
  (sort-sortby/-main)
  (concat/-main)
  (lazy-seqs/-main)
  (infinite-seqs/-main)
  (into/-main)
  (apply/-main)
  (partial/-main)
  (complement/-main)
  (referentially-transparency/-main)
  (avoiding-side-effects/-main))
