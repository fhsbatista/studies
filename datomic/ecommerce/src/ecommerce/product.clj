(ns ecommerce.product
  (:require [schema.core :as s]
            [ecommerce.category :as category]
            [ecommerce.variant :as variant])
  (:import (java.util UUID)))

(defn uuid []
  (UUID/randomUUID))

(def Product
  {:product/id                        UUID
   :product/name                      s/Str
   :product/slug                      s/Str
   :product/price                     BigDecimal
   :product/stock                     s/Int
   :product/digital?                  s/Bool
   (s/optional-key :product/category) category/Category
   (s/optional-key :product/variants) [variant/Variant]})

(defn new-product
  ([name slug price category]
   (new-product (uuid) name slug price category false))
  ([name slug price category digital?]
   (new-product (uuid) name slug price category digital?))
  ([uuid name slug price category digital?]
   {:product/id       uuid
    :product/name     name
    :product/slug     slug
    :product/price    price
    :product/stock    0
    :product/category category
    :product/digital? digital?}))