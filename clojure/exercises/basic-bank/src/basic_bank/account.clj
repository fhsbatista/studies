(ns basic-bank.account
  (:require [cheshire.core :as json]
            [clojure.java.io :as io])
  (:import [java.time LocalDateTime]
           [java.time.format DateTimeFormatter]))

(defn current-date []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")]
    (.format now formatter)))

(defn create-account [document name]
  (let [filename (str  "accounts/" document ".json")
        balance 0.0
        initial-data {:document document
                      :name name
                      :balance balance
                      :created-at (current-date)
                      :transactions []}
        json-output (json/generate-string initial-data {:pretty true})]
    (with-open [writer (io/writer filename)]
      (.write writer json-output)
      {:document document
       :name name
       :balance balance
       :filename filename})))

(defn get-account [document]
  (let [filename (str "accounts/" document ".json")
        content (with-open [reader (io/reader filename)]
                  (json/parse-stream reader true))
        name (content :name)
        balance (content :balance)
        created-at (content :created-at)]
    {:document document
     :name name
     :created-at created-at
     :balance balance
     :filename filename}))

(defn deposit [document value] 
  (let [account (get-account document)
        updated-balance (+ (account :balance) value)
        current-json (with-open [reader (io/reader (account :filename))]
                       (json/parse-stream reader true))
        deposit-json {:date (current-date)
                      :value (+ value)}
        updated-transactions (conj (current-json :transactions) deposit-json)
        updated-json (assoc current-json
                            :transactions updated-transactions
                            :balance updated-balance)]
    (with-open [writer (io/writer (account :filename))]
      (.write writer (json/generate-string updated-json {:pretty true})))
    (assoc account :balance updated-balance)))

(defn withdraw [document value]
  (let [account (get-account document)
        updated-balance (- (account :balance) value)
        current-json (with-open [reader (io/reader (account :filename))]
                       (json/parse-stream reader true))
        withdraw-json {:date (current-date)
                      :value (- value)}
        updated-transactions (conj (current-json :transactions) withdraw-json)
        updated-json (assoc current-json
                            :transactions updated-transactions
                            :balance updated-balance)]
    (with-open [writer (io/writer (account :filename))]
      (.write writer (json/generate-string updated-json {:pretty true})))
    (assoc account :balance updated-balance)))

(defn balance [document]
  (let [account (get-account document)
        json (with-open [reader (io/reader (account :filename))]
               (json/parse-stream reader true))]
    (json :balance)))
