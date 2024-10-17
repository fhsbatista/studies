(ns clojure-for-brave.core
  (:require [clojure-for-brave.vector :as vector]
            [clojure-for-brave.destructuring :as descructuring]
            [clojure-for-brave.anonymous :as anonymous]
            [clojure-for-brave.closure :as closure]
            [clojure-for-brave.let :as let]))

(defn -main
  "Examples"
  [& args]
  (vector/-main)
  (descructuring/-main)
  (anonymous/-main)
  (closure/-main)
  (let/-main))
