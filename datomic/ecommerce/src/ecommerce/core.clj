(ns ecommerce.core
  (:use clojure.pprint)
  (:require [ecommerce.db :as db]))

(db/delete-database!)

(def conn (db/open-connection!))

(db/create-scheme! conn)
(db/seed! conn "200.123.124.145")