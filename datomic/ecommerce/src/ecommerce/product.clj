(ns ecommerce.product)

(defn uuid []
  (java.util.UUID/randomUUID))

(defn new
  ([name slug price]
   {
    :product/id    (uuid)
    :product/name  name
    :product/slug  slug
    :product/price price
    })
  ([name slug price category-id]
   {:product/id       (uuid)
    :product/name     name
    :product/slug     slug
    :product/price    price
    :product/category [:category/id category-id]}))
