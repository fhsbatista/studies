(ns basic-bank.io
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(defn read-account-json [filename]
  (with-open [reader (io/reader filename)]
    (json/parse-stream reader true)))

(defn write-account-json [filename json]
  (with-open [writer (io/writer filename)]
    (.write writer json)))