(ns clojure-for-brave.core
  (:require [clojure-for-brave.destructuring :as descructuring]
            [clojure-for-brave.anonymous :as anonymous]))

(defn -main
  "Examples"
  [& args]
  (descructuring/-main)
  (anonymous/-main))
