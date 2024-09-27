(ns clojure-service.server
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]))


(defn hello-function [request]
  {:status 200 :body (str "Hello world " (get-in request [:query-params :name] "everyone"))})

(def routes (route/expand-routes
              #{["/hello" :get hello-function :route-name :hello-world]}))

(def service-map {::http/routes routes
                  ::http/port   9999
                  ::http/type   :jetty
                  ::http/join?  false})

(def server (atom nil))

(defn start-server []
  (reset! server (http/stop (http/create-server service-map)))
  )

(start-server)
(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(test-request :get "/hello?name=fernando")