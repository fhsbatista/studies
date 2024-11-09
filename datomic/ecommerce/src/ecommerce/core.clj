(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.product :as product]))

(def conn (db/open-connection))
(pprint conn)

(db/create-scheme conn)

(let [macbook (product/new "Macbook M1" "macbook_m1" 6000.00M)]
  (d/transact conn [macbook]))