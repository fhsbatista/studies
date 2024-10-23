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
  (let [filename (str "accounts/" document ".json")
        data-as-json (json/generate-string data {:pretty true})]
    (with-open [writer (io/writer filename)]
      (.write writer data-as-json))))

(defn create-account [document name]
  (let [balance 0.0
        data {:document document
              :name name
              :balance balance
              :created-at (current-date)
              :transactions []}]
    (write-account-json document data)))

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

(defn add-transaction [date value account]
  (let [updated-balance (+ (account :balance) value)
        current-json (read-account-json (account :document))
        transaction-json {:date date,
                          :value value}
        updated-transactions (conj (current-json :transactions) transaction-json)
        data (assoc current-json
                    :transactions updated-transactions
                    :balance updated-balance)]
    (write-account-json (account :document) data)))


(defn deposit [document value]
  (let [account (get-account document)]
    (add-transaction (current-date) value account)))

(defn withdraw [document value]
  (let [account (get-account document)]
    (add-transaction (current-date) (- value) account)))

(defn balance [document]
  (let [json (read-account-json document)]
    (json :balance)))
