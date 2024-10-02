(ns clojure-service.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [io.pedestal.interceptor :as i]
            [clojure-service.database :as database]
            [clojure-service.routes :as routes]))

(defn assoc-store [context]
  (update context :request assoc :store database/store))

(def db-interceptor
  {:name  :db-interceptor
   :enter assoc-store})

(def service-map-base {::http/routes routes/routes
                       ::http/port   9999
                       ::http/type   :jetty
                       ::http/join?  false})

(def service-map (-> service-map-base
                     (http/default-interceptors)
                     (update ::http/interceptors conj (i/interceptor db-interceptor))))

(defonce server (atom nil))

(defn start-server []
  (reset! server (http/stop (http/create-server service-map))))

(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(defn stop-server []
  (http/stop @server))

(defn restart-server []
  (stop-server)
  (start-server))


(defn clean-store []
  (reset! database/store {}))

(defn start []
  (start-server)
  (restart-server))