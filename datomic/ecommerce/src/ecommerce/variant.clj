(ns ecommerce.variant
  (:require [schema.core :as s]))

(s/def Variant
  {:variant/id java.util.UUID
   :variant/name s/Str
   :variant/price BigDecimal})
