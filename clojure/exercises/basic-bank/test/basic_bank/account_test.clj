(ns basic-bank.account-test
  (:require [clojure.test :refer :all]
            [basic-bank.account :refer :all]
            [clojure.java.io :as io]
            [basic-bank.utils :as utils]))

(def document "12345678900")
(def name "Test User")

(defn cleanup []
  (let [filename (str "accounts/" document ".json")]
    (when (.exists (io/as-file filename))
      (.delete (io/as-file filename)))))

(use-fixtures :each (fn [f]
                      (cleanup)
                      (f)
                      (cleanup)))

(deftest test-create-account
  (create-account document name)
  (let [account (get-account document)]
    (is (= (:document account) document))
    (is (= (:name account) name))
    (is (= (balance document) 0.0))))

(deftest test-balance
  (create-account document name)
  (is (= (balance document) 0.0)))

(deftest test-deposit
  (create-account document name)
  (deposit document 500)
  (is (= (balance document) 500.0)))

(deftest test-withdraw
  (create-account document name)
  (deposit document 500)
  (withdraw document 200)
  (is (= (balance document) 300.0)))

(deftest test-add-transactions
  (create-account document name)
  (let [deposit-amount 500
        withdrawal-amount 200]
    (deposit document deposit-amount)
    (withdraw document withdrawal-amount)
    (let [account (get-account document)
          transactions (account :transactions)]
      (is (= (count transactions) 2))
      (is (= ((nth transactions 0) :value) deposit-amount))
      (is (= ((nth transactions 0) :date) (utils/current-date)))
      (is (= ((nth transactions 1) :value) (- withdrawal-amount)))
      (is (= ((nth transactions 1) :date) (utils/current-date))))))
