(ns ecommerce.product
  (:require [schema.core :as s]
            [ecommerce.category :as category])
  (:import (java.util UUID)))

(defn uuid []
  (UUID/randomUUID))

(def Product
  {:product/id                        UUID
   :product/name                      s/Str
   :product/slug                      s/Str
   :product/price                     BigDecimal
   (s/optional-key :product/category) category/Category})

(defn new
  ([name slug price]
   {
    :product/id    (uuid)
    :product/name  name
    :product/slug  slug
    :product/price price
    })
  ([name slug price category]
   {:product/id       (uuid)
    :product/name     name
    :product/slug     slug
    :product/price    price
    :product/category category}))
