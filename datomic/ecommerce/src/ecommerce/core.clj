(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.product :as product]))

(db/delete-database)

(def conn (db/open-connection))

(db/create-scheme conn)

(let [macbook (product/new "Macbook M1" "/macbook_m1" 6000.00M)
      iphone2 (product/new "Iphone 16" "/iphone_15" 7500.00M)
      iphone (product/new "Iphone 15" "/iphone_16" 7500.00M)
      watch {:product/name "Apple watch"}]
  (d/transact conn [macbook iphone iphone2 watch]))


(let [mug (product/new "Black Mug" "/black_mug" 12.00M)
      result @(d/transact conn [mug])
      product-id (first (vals (:tempids result)))]
  (d/transact conn [[:db/add product-id :product/price 13.00M]]))

(pprint (db/find-product-by-id 17592186045423))
(pprint (db/find-product-by-uuid #uuid "b2a2e6ec-553b-4420-851b-73f67d4468f9"))
(pprint (db/find-products))
(pprint (db/find-products-with-pull))
(pprint (db/find-products-with-pull-all-attrs))
(pprint (db/find-products-by-slug "/macbook_m1"))
(pprint (db/find-all-slugs))
(pprint (db/find-by-price 7500.00M))
(pprint (db/find-by-min-price 7000))


