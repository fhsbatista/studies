(ns basic-bank.account-test
  (:require [clojure.test :refer :all]
            [basic-bank.account :refer :all]
            [clojure.java.io :as io]))

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
