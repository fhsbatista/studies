(ns ecommerce.core
  (:use clojure.pprint)
  (:require [ecommerce.db :as db]))

(def conn (db/open-connection))
(pprint conn)

