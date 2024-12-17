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
   :product/stock                     s/Int
   (s/optional-key :product/category) category/Category})

(defn new-product
  ([name slug price category]
   (new-product (uuid) name slug price category))
  ([uuid name slug price category]
   {:product/id       uuid
    :product/name     name
    :product/slug     slug
    :product/price    price
    :product/stock    0
    :product/category category}))