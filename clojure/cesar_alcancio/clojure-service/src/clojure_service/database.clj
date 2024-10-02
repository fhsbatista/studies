(ns clojure-service.database
  (:require [com.stuartsierra.component :as component]))


(defrecord Database []
  component/Lifecycle

  (start [this]
    (println "Starting database")
    (assoc this :store (atom {})))

  (stop [this]
    (println "Stopping database")
    (assoc this :store nil)))


(defn new-database []
  (->Database))