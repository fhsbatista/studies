(ns basic-bank.account
  (:require [cheshire.core :as json]
            [basic-bank.utils :as utils]
            [basic-bank.io :as io]))

(defn create-account [document name]
  (let [data {:document document
              :name name
              :created-at (utils/current-date)
              :transactions []}
        json (json/generate-string data {:pretty true})]
    (io/write-account-json (utils/account-filename document) json)))

(defn get-account [document]
  (let [content (io/read-account-json (utils/account-filename document))
        {:keys [name
                created-at
                transactions]} content]
    {:document document
     :name name
     :created-at created-at
     :transactions transactions}))

(defn balance [document]
  (let [account (get-account document)]
    (double (reduce + (map :value (account :transactions))))))