(ns clojure-service.server
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]))

(def store (atom {}))

(defn hello-function [request]
  {:status 200 :body (str "Hello world " (get-in request [:query-params :name] "everyone"))})

(defn create-task-map [uuid name status]
  {:id uuid :name name :status status})

(defn create-task [request]
  (let [uuid (java.util.UUID/randomUUID)
        name (get-in request [:query-params :name])
        status (get-in request [:query-params :status])
        task (create-task-map uuid name status)]
    (swap! store assoc uuid task)
    {:status 200 :body {:message "Task created"
                        :task    task}}
    ))

(defn list-tasks [request]
  {:status 200 :body @store})

(def routes (route/expand-routes
              #{["/hello" :get hello-function :route-name :hello-world]
                ["/task" :post create-task :route-name :create-task]
                ["/list-tasks" :get list-tasks :route-name :list-tasks]}))

(def service-map {::http/routes routes
                  ::http/port   9999
                  ::http/type   :jetty
                  ::http/join?  false})

(def server (atom nil))

(defn start-server []
  (reset! server (http/stop (http/create-server service-map)))
  )

(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(start-server)
(test-request :get "/hello?name=fernando")
(test-request :post "/task?name=run&status=pending")

(test-request :get "/list-tasks")