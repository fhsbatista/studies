(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.product :as product]))

(db/delete-database)

(def conn (db/open-connection))

(db/create-scheme conn)

(let [macbook (product/new "Macbook M1" "macbook_m1" 6000.00M)
      iphone2 (product/new "Iphone 16" "/iphone_15" 7500.00M)
      iphone (product/new "Iphone 15" "/iphone_15" 7500.00M)
      watch {:product/name "Apple watch"}]
  (d/transact conn [macbook iphone watch]))

(pprint (db/find-products))
