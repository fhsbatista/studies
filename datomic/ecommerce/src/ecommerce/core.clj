(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.product :as product]))

(db/delete-database)

(def conn (db/open-connection))

(db/create-scheme conn)

(def db (d/db conn))

(d/q '[:find ?product
       :where [?product :product/name]] db)

(let [macbook (product/new "Macbook M1" "macbook_m1" 6000.00M)]
  (d/transact conn [macbook]))

(let [iphone (product/new "Iphone 15" "/iphone_15" 7500.00M)
      result @(d/transact conn [iphone])
      product-id (first (vals (:tempids result)))]
  (pprint result)
  (pprint @(d/transact conn [[:db/add product-id :product/price 7800.00M]])))


