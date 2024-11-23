(ns ecommerce.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [ecommerce.db :as db]
            [ecommerce.product :as product]
            [ecommerce.category :as category]))

(db/delete-database)

(def conn (db/open-connection))

(db/create-scheme conn)

(def macbook (product/new "Macbook M1" "/macbook_m1" 6000.00M))
(def iphone2 (product/new "Iphone 16" "/iphone_15" 7500.00M))
(def iphone (product/new "Iphone 15" "/iphone_16" 7500.00M))
(def watch {:product/name "Apple watch"})

(db/add-products [macbook iphone iphone2 watch])

(let [mug (product/new "Black Mug" "/black_mug" 12.00M)
      result @(d/transact conn [mug])
      product-id (first (vals (:tempids result)))]
  (d/transact conn [[:db/add product-id :product/price 13.00M]]))

(def books (category/new "Books"))
(def keyboards (category/new "Keyboards"))
(def electronics (category/new "Electronics"))

(db/add-categories [books keyboards electronics])

(db/set-category-on-products [macbook iphone iphone2] electronics)

;(pprint (db/find-product-by-id 17592186045418))
;(pprint (db/find-product-by-uuid #uuid "25b2d35b-77a7-4903-a62e-b068cd3c7b30"))
;(pprint (db/find-products))
;(pprint (db/find-products-with-pull))
;(pprint (db/find-products-with-pull-all-attrs))
;(pprint (db/find-products-by-slug "/macbook_m1"))
;(pprint (db/find-all-slugs))
;(pprint (db/find-by-price 7500.00M))
;(pprint (db/find-by-min-price 7000))