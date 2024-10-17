(ns clojure-for-brave.core
  (:require [clojure-for-brave.destructuring :as descructuring]
            [clojure-for-brave.anonymous :as anonymous]
            [clojure-for-brave.closure :as closure]))

(defn -main
  "Examples"
  [& args]
  (descructuring/-main)
  (anonymous/-main)
  (closure/-main))
