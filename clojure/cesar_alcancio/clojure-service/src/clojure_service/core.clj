(ns clojure-service.core
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]))


(defn hello-function [request]
  {:status 200 :body (str "Hello world " (get-in request [:query-params :name] "everyone"))})

(def routes (route/expand-routes
              #{["/hello" :get hello-function :route-name :hello-world]}))

(def service-map {::http/routes routes
                  ::http/port   9999
                  ::http/type   :jetty
                  ::http/join?  false})

(http/start (http/create-server service-map))
(println "Started server http")