(ns ecommerce.core
  (:use clojure.pprint)
  (:require [ecommerce.db :as db]
            [schema.core :as s]
            [ecommerce.category :as category]
            [ecommerce.product :as product]
            [schema.core :as s]))

(s/set-fn-validation! true)

(db/delete-database!)

(def conn (db/open-connection!))

(db/create-scheme! conn)
(db/seed! "200.123.124.145")


(def electronics (category/new "Electronics"))
(def macbook
  (product/new-product "Macbook M1"
               "/macbook_m1"
               16000.00M
               electronics))

(db/add-products! [macbook] "200.200.121.623")

(pprint (db/find-product-by-uuid (:product/id macbook)))

(db/update-price (:product/id macbook) 16000.00M 17000.00M)

(db/add-variant! (:product/id macbook) "Macbook 32GB" 18000.00M)

(db/find-all-products)
