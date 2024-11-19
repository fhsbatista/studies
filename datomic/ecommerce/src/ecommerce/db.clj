(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/hello")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database []
  (d/delete-database db-uri))

(def scheme [{:db/ident       :product/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Product's name"}
             {:db/ident       :product/slug
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Product's path to access via http"}
             {:db/ident       :product/price
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "Product's price with monetary precision"}])

(defn create-scheme [conn]
  (d/transact conn scheme))

(defn snapshot []
  (d/db (open-connection)))

(defn find-products []
  (d/q '[:find ?e
         :keys product/name
         :where [?e :product/name]] (snapshot)))

(defn find-products-by-slug [slug]
  (d/q '[:find ?e
         :keys product/slug
         :in $ ?slug
         :where [?e :product/slug ?slug]] (snapshot) slug)
  )

(defn find-all-slugs []
  (d/q '[:find ?slug
         :keys product/slug
         :where [_ :product/slug ?slug]] (snapshot)))

(defn find-by-price [price]
  (d/q '[:find ?name ?price
         :keys product/name product/price
         :where
         [?e :product/price ?price]
         [?e :product/name ?name]
         ] (snapshot)))

