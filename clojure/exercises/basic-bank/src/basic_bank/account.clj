(ns basic-bank.account
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]
            [basic-bank.utils :as utils]))

(defn- account-filename [document]
  (str "accounts/" document ".json"))

(defn- read-account-json [document]
  (with-open [reader (io/reader (account-filename document))]
    (json/parse-stream reader true)))

(defn- write-account-json [document json]
  (with-open [writer (io/writer (account-filename document))]
    (.write writer json)))

(defn- add-transaction [date value account]
  (let [transaction {:date date,
                     :value value}
        updated-transactions (conj (account :transactions) transaction)]
    (assoc account
           :transactions updated-transactions)))

(defn- update-account [document update-fn]
  (let [account (read-account-json document)
        updated-account (update-fn account)
        json (json/generate-string updated-account {:pretty true})]
    (write-account-json document json)
    updated-account))

(defn- transact [document value]
  (update-account document
                  (fn [account] (add-transaction (utils/current-date) value account))))

(defn create-account [document name]
  (let [data {:document document
              :name name
              :created-at (utils/current-date)
              :transactions []}
        json (json/generate-string data {:pretty true})]
    (write-account-json document json)))

(defn get-account [document]
  (let [content (read-account-json document)
        filename (account-filename document)
        {:keys [name
                created-at
                transactions]} content]
    {:document document
     :name name
     :created-at created-at
     :transactions transactions
     :filename filename}))

(defn deposit [document value]
  (transact document value))

(defn withdraw [document value]
  (transact document (- value)))

(defn balance [document]
  (let [account (get-account document)]
    (double (reduce + (map :value (account :transactions))))))