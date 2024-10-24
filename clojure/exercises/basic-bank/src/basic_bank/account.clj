(ns basic-bank.account
  (:require [cheshire.core :as json]
            [clojure.java.io :as io])
  (:import [java.time LocalDateTime]
           [java.time.format DateTimeFormatter]))

(defn current-date []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")]
    (.format now formatter)))

(defn account-filename [document]
  (str "accounts/" document ".json"))

(defn read-account-json [document]
  (with-open [reader (io/reader (account-filename document))]
    (json/parse-stream reader true)))

(defn write-account-json [document json]
  (with-open [writer (io/writer (account-filename document))]
    (.write writer json)))

(defn create-account [document name]
  (let [data {:document document
              :name name
              :balance 0.0
              :created-at (current-date)
              :transactions []}
        json (json/generate-string data {:pretty true})]
    (write-account-json document json)))

(defn get-account [document]
  (let [content (read-account-json document)
        filename (account-filename document)
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
        transaction {:date date,
                     :value value}
        updated-transactions (conj (account :transactions) transaction)]
    (assoc account
           :transactions updated-transactions
           :balance updated-balance)))

(defn update-account [document update-fn]
  (let [account (read-account-json document)
        updated-account (update-fn account)
        json (json/generate-string updated-account {:pretty true})]
    (write-account-json document json)
    updated-account))

(defn transact [document value]
  (update-account document
                  (fn [account] (add-transaction (current-date) value account))))

(defn deposit [document value]
  (transact document value))

(defn withdraw [document value]
  (transact document (- value)))

(defn balance [document]
  (let [json (read-account-json document)]
    (json :balance)))