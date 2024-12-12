(ns ecommerce.category
  (:require [schema.core :as s]))

(defn uuid []
  (java.util.UUID/randomUUID))

(def Category
  {:category/id   java.util.UUID
   :category/name s/Str})

(defn new [name]
  {:category/id   (uuid)
   :category/name name})
