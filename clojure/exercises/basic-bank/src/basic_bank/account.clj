(ns basic-bank.account
  (:require [cheshire.core :as json]
            [clojure.java.io :as io])
  (:import [java.time LocalDateTime]
           [java.time.format DateTimeFormatter]))

(defn current-date []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")]
    (.format now formatter)))

(defn read-account-json [document]
  (let [filename (str "accounts/" document ".json")]
    (with-open [reader (io/reader filename)]
      (json/parse-stream reader true))))

(defn write-account-json [document data]
  (let [filename (str "accounts/" document ".json")]
    (with-open [writer (io/writer filename)]
      (.write writer data))))

(defn create-account [document name]
  (let [filename (str  "accounts/" document ".json")
        balance 0.0
        initial-data {:document document
                      :name name
                      :balance balance
                      :created-at (current-date)
                      :transactions []}
        json-output (json/generate-string initial-data {:pretty true})]
    (write-account-json document json-output)
    {:document document
     :name name
     :balance balance
     :filename filename}))

(defn get-account [document]
  (let [content (read-account-json document)
        filename (str "accounts/" document ".json")
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
        current-json (read-account-json document)
        deposit-json {:date (current-date)
                      :value (+ value)}
        updated-transactions (conj (current-json :transactions) deposit-json)
        updated-json (assoc current-json
                            :transactions updated-transactions
                            :balance updated-balance)
        final-json (json/generate-string updated-json {:pretty true})]
    (write-account-json document final-json)
    (assoc account :balance updated-balance)))

(defn withdraw [document value]
  (let [account (get-account document)
        updated-balance (- (account :balance) value)
        current-json (read-account-json document)
        withdraw-json {:date (current-date)
                       :value (- value)}
        updated-transactions (conj (current-json :transactions) withdraw-json)
        updated-json (assoc current-json
                            :transactions updated-transactions
                            :balance updated-balance)
        final-json (json/generate-string updated-json {:pretty true})]
    (write-account-json document final-json)
    (assoc account :balance updated-balance)))

(defn balance [document]
  (let [json (read-account-json document)]
    (json :balance)))
