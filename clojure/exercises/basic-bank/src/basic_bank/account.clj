(ns basic-bank.account
  (:require [cheshire.core :as json]
            [basic-bank.utils :as utils]
            [basic-bank.io :as io]))

(defn- account-filename [document]
  (str "accounts/" document ".json"))

(defn- add-transaction [date value account]
  (let [transaction {:date date,
                     :value value}
        updated-transactions (conj (account :transactions) transaction)]
    (assoc account
           :transactions updated-transactions)))

(defn- update-account [document update-fn]
  (let [account (io/read-account-json (account-filename document))
        updated-account (update-fn account)
        json (json/generate-string updated-account {:pretty true})]
    (io/write-account-json (account-filename document) json)
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
    (io/write-account-json (account-filename document) json)))

(defn get-account [document]
  (let [content (io/read-account-json (account-filename document))
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