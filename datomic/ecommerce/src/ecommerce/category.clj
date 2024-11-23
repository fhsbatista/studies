(ns ecommerce.category)

(defn uuid []
  (java.util.UUID/randomUUID))

(defn new [name]
  {:category/id (uuid)
   :category/name name})
