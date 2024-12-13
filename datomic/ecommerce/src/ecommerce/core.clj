(ns ecommerce.core
  (:use clojure.pprint)
  (:require [ecommerce.db :as db]
            [schema.core :as s]
            [ecommerce.category :as category]
            [ecommerce.product :as product]))

(s/set-fn-validation! true)

(db/delete-database!)

(def conn (db/open-connection!))

(db/create-scheme! conn)
(db/seed! "200.123.124.145")


(def electronics (category/new "Electronics"))
(def macbook
  (product/new "Macbook M1"
               "/macbook_m1"
               16000.00M))

(pprint (s/validate product/Product (assoc macbook :product/category electronics)))
(pprint (s/validate category/Category electronics))