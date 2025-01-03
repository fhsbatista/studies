(ns ecommerce.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [schema.core :as s]
            [clojure.walk :as walk]
            [ecommerce.product :as product]
            [ecommerce.category :as category]))

(def db-uri "datomic:dev://localhost:4334/ecommerce")

(defn open-connection! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database! []
  (d/delete-database db-uri))

(def scheme [;Transactions
             {:db/ident       :tx-data/ip
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             ;Products
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
             {:db/ident       :product/stock
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}
             {:db/ident       :product/digital?
              :db/valueType   :db.type/boolean
              :db/cardinality :db.cardinality/one}
             {:db/ident       :product/category
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/one}
             {:db/ident       :product/variants
              :db/valueType   :db.type/ref
              :db/isComponent true
              :db/cardinality :db.cardinality/many}

             ;Variants
             {:db/ident       :variant/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :variant/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}
             {:db/ident       :variant/price
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one}

             ;Categories
             {:db/ident       :category/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}
             {:db/ident       :category/name
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}])

(defn create-scheme! [conn]
  (d/transact conn scheme))

(defn snapshot []
  (d/db (open-connection!)))

(defn dissoc-db-id [item]
  (if (map? item)
    (dissoc item :db/id)
    item))

(defn datomic-to-schema [entities]
  (walk/prewalk #(dissoc-db-id %) entities))

(s/defn add-products! [products :- [product/Product] ip]
  (let [ip-db-add [:db/add "datomic.tx" :tx-data/ip ip]
        db-adds (conj products ip-db-add)]
    (d/transact (open-connection!) db-adds)))

(s/defn add-categories! [categories :- [category/Category]]
  (d/transact (open-connection!) categories))

(s/defn add-variant! [product-id :- java.util.UUID
                      name :- s/Str
                      price :- BigDecimal]
  (d/transact (open-connection!) [{:variant/id    (product/uuid)
                                   :variant/name  name
                                   :variant/price price
                                   ;this id exist only during transaction, so it can be used in other db/adds to refer this db/add
                                   :db/id         "transaction-temp-id"}
                                  {:product/id       product-id
                                   :product/variants "transaction-temp-id"}]))

(defn seed! [ip]
  (let [books (category/new "Books")
        keyboards (category/new "Keyboards")
        electronics (category/new "Electronics")
        games (category/new "Games")
        apps (category/new "Apps")
        macbook (product/new-product "Macbook M1" "/macbook_m1" 16000.00M electronics)
        iphone2 (product/new-product "Iphone 16" "/iphone_15" 7500.00M electronics)
        iphone (product/new-product "Iphone 15" "/iphone_16" 7500.00M electronics)
        mxkeys (product/new-product "Logitech MX Keys" "/mxkeys" 7500.00M keyboards)
        fifa15 (product/new-product "Game Fifa 15" "/fifa15" 75.00M games true)
        avast (product/new-product "Avast antivirus" "/avast" 79.00M apps true)
        clojure-brave (product/new-product "Clojure for the true and brave" "/clojure_brave" 7500.00M books true)]
    (add-categories! [books keyboards electronics])
    (add-products! [macbook iphone2 iphone mxkeys clojure-brave fifa15 avast] ip)))

(s/defn find-product-by-uuid :- (s/maybe product/Product) [uuid :- java.util.UUID]
  (let [result (d/pull (snapshot) '[* {:product/category [*]}] [:product/id uuid])
        product (datomic-to-schema result)]
    (if (:product/id result)
      product
      nil)))

(s/defn find-product-by-uuid! :- product/Product [uuid :- java.util.UUID]
  (let [product (find-product-by-uuid uuid)]
    (if product
      product
      (throw (ex-info "Product not found"
                      {:type :errors/not-found, :id uuid})))))

(defn find-all-products-ids []
  (d/q '[:find ?e
         :keys product/id
         :where [?e :product/name]] (snapshot)))

(defn find-products-with-pull []
  (d/q '[:find (pull ?e [:product/name :product/price :product/slug])
         :where [?e :product/name]] (snapshot)))

(s/defn find-all-products :- [product/Product] []
  (datomic-to-schema (d/q '[:find [(pull ?e [* {:product/category [*]}]) ...]
                            :where [?e :product/name]] (snapshot))))

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

(s/defn find-categories :- [category/Category] []
  (datomic-to-schema (d/q '[:find [(pull ?e [*]) ...]
                            :where [?e :category/id]] (snapshot))))

(defn find-category-by-name [name]
  (d/q '[:find (pull ?e [*])
         :in $ ?name
         :where [?e :category/name ?name]] (snapshot) name))

(defn find-category-by-uuid [id]
  (d/pull (snapshot) '[*] [:category/id id]))

(defn list-products-and-categories []
  (d/q '[:find ?product-name ?category-name
         :keys product category
         :where
         [?product :product/name ?product-name]
         [?product :product/category ?category-id]
         [?category-id :category/name ?category-name]] (snapshot)))

(defn set-category-on-products! [products category]
  (let [conn (open-connection!)
        to-add (reduce (fn [db-adds product] (conj db-adds [:db/add
                                                            [:product/id (:product/id product)]
                                                            :product/category
                                                            [:category/id (:category/id category)]]))
                       []
                       products)]
    (d/transact conn to-add)))


; finding products using forward navigation
(defn find-products-by-category-forward [category-name]
  (d/q '[:find (pull ?product [:product/name {:product/category [:category/name]}])
         :in $ ?category-name
         :where
         [?category-id :category/name ?category-name]
         [?product :product/category ?category-id]], (snapshot) category-name))

; finding products using backwards navigation
(defn find-products-by-category-backwards [category-name]
  ; "_" is for backward navigation
  (d/q '[:find (pull ?category [:category/name {:product/_category [:product/name]}])
         :in $ ?category-name
         :where
         [?category :category/name ?category-name]], (snapshot) category-name))


(defn find-products-added-by-ip [ip]
  (d/q '[:find (pull ?product [*])
         :in $ ?ip
         :where
         [?transaction :tx-data/ip ?ip]
         [?product :product/id _ ?transaction]] (snapshot) ip))

(defn summary []
  (let [data (d/q '[:find (max ?price) (min ?price) (count ?price)
                    :with ?product
                    :where [?product :product/price ?price]] (snapshot))
        [max min count] (first data)]
    {:max   max
     :min   min
     :count count}))


(defn summary-by-category []
  (d/q '[:find ?category-name (max ?price) (min ?price) (count ?price) (sum ?price)
         :keys category max min count sum
         :with ?product
         :where
         [?product :product/price ?price]
         [?product :product/category ?category]
         [?category :category/name ?category-name]] (snapshot)))

(defn most-expensive-product []
  (d/q '[:find (pull ?product [*])
         :where
         [(q '[:find (max ?price)
               :where [_ :product/price ?price]
               ] $) [[?price]]]
         [?product :product/price ?price]] (snapshot)))

(defn most-cheap-product []
  (d/q '[:find (pull ?product [*])
         :where
         [(q '[:find (min ?price)
               :where [_ :product/price ?price]
               ] $) [[?price]]]
         [?product :product/price ?price]] (snapshot)))

(defn add-stock [uuid quantity]
  (let [product (find-product-by-uuid uuid)
        current-stock (:product/stock product)
        new-stock (+ current-stock quantity)
        updated-product (assoc product :product/stock new-stock)]
    (d/transact (open-connection!) [updated-product])))

(def rules
  '[[(stock ?product ?stock)
     [?product :product/stock ?stock]]
    [(stock ?product ?stock)
     [?product :product/digital? true]
     ;if digital, assume stock is 1
     [(ground 1) ?stock]]
    [(can-sell? ?product)
     (stock ?product ?stock)
     [(> ?stock 0)]]
    [(products-in-category ?product ?category-name)
     [?category :category/name ?category-name]
     [?product :product/category ?category]]])

(s/defn available-products :- [product/Product] []
  (datomic-to-schema (d/q '[:find [(pull ?product [* {:product/category [*]}]) ...]
                            :in $ %                         ;% is a shortcut to pass "rules", as $ pass the database
                            :where (can-sell? ?product)]
                          (snapshot) rules)))

(s/defn available-product? :- (s/maybe product/Product) [uuid]
  (let [query '[:find (pull ?product [* {:product/category [*]}]) .
                :in $ % ?uuid
                :where (can-sell? ?product)]
        result (d/q query (snapshot) rules uuid)
        product (datomic-to-schema result)]
    (if product
      product
      nil)))

(s/defn find-products-in-categories :- [product/Product] [categories :- [s/Str] digital? :- s/Bool]
  (datomic-to-schema (d/q '[:find [(pull ?product [* {:product/category [*]}]) ...]
                            :in $ % [?category-name ...] ?digital? ; "[<input> ...]"  -> [...] indicates the input is a sequence instead of single value
                            :where
                            (products-in-category ?product ?category-name)
                            [?product :product/digital? ?digital?]
                            ] (snapshot) rules categories digital?)))

(s/defn update-price [uuid :- java.util.UUID
                      current-price :- BigDecimal
                      new-price :- BigDecimal]
  (d/transact (open-connection!) [[;db/cas is for updating values only if the current value is what we expect.
                                   ;this avoids the problem of updating an already out-of-date value, which might happen in scenarios of racing conditions
                                   :db/cas [:product/id uuid]
                                   :product/price
                                   current-price
                                   new-price]]))
