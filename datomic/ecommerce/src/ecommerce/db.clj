(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database []
  (d/delete-database db-uri))

(def scheme [;Products
             {:db/ident       :product/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity
              :db/doc         "Product's uuid"}
             {:db/ident       :product/name
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
              :db/doc         "Product's price with monetary precision"}
             {:db/ident       :product/category
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/one}

             ;Categories
             {:db/ident       :category/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :category/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(defn create-scheme [conn]
  (d/transact conn scheme))

(defn snapshot []
  (d/db (open-connection)))

(defn find-product-by-id [id]
  (d/pull (snapshot) '[*] id))

(defn find-product-by-uuid [uuid]
  (d/pull (snapshot) '[*] [:product/id uuid]))

(defn find-products []
  (d/q '[:find ?e
         :keys product/id
         :where [?e :product/name]] (snapshot)))

(defn find-products-with-pull []
  (d/q '[:find (pull ?e [:product/name :product/price :product/slug])
         :where [?e :product/name]] (snapshot)))

(defn find-products-with-pull-all-attrs []
  (d/q '[:find (pull ?e [*])
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

(defn find-by-min-price [min-price]
  (d/q '[:find ?name ?price
         :keys product/name product/price
         :in $ ?min-price
         :where
         [?e :product/price ?price]
         ;it is a good idea to set more restrictive filters first so that there are less datoms to find next
         [(> ?price ?min-price)]
         [?e :product/name ?name]
         ] (snapshot) min-price))

(defn find-categories []
  (d/q '[:find (pull ?e [*])
         :where [?e :category/id]] (snapshot)))

(defn find-category-by-uuid [id]
  (d/pull (snapshot) '[*] [:category/id id]))

