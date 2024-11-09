(ns ecommerce.product)

(defn new [name slug price]
  {:product/name  name
   :product/slug  slug
   :product/price price})
