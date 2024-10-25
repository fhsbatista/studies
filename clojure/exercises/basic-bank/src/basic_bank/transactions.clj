(ns basic-bank.transactions
  (:require [basic-bank.io :as io]
            [basic-bank.utils :as utils]
            [cheshire.core :as json]))

(defn- add-transaction [date value account]
  (let [transaction {:date date,
                     :value value}
        updated-transactions (conj (account :transactions) transaction)]
    (assoc account
           :transactions updated-transactions)))

(defn- update-account [document update-fn]
  (let [account (io/read-account-json (utils/account-filename document))
        updated-account (update-fn account)
        json (json/generate-string updated-account {:pretty true})]
    (io/write-account-json (utils/account-filename document) json)
    updated-account))

(defn- transact [document value]
  (update-account document
                  (fn [account] (add-transaction (utils/current-date) value account))))

(defn deposit [document value]
  (transact document value))

(defn withdraw [document value]
  (transact document (- value)))
