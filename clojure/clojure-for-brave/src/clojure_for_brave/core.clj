(ns clojure-for-brave.core
  (:require [clojure-for-brave.vector :as vector]
            [clojure-for-brave.destructuring :as descructuring]
            [clojure-for-brave.anonymous :as anonymous]
            [clojure-for-brave.closure :as closure]
            [clojure-for-brave.let :as let]
            [clojure-for-brave.set :as set]
            [clojure-for-brave.loops :as loop]
            [clojure-for-brave.regex :as regex]
            [clojure-for-brave.sequences :as sequences]))

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
  (sequences/-main))
