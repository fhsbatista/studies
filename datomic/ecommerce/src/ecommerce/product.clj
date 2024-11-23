(ns ecommerce.product)

(defn uuid []
  (java.util.UUID/randomUUID))

(defn new [name slug price]
  {:product/id (uuid)
   :product/name  name
   :product/slug  slug
   :product/price price})
