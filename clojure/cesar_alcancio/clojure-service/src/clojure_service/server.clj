(ns clojure-service.server
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [clojure-service.database :as database]))


(defn assoc-store [context]
  (update context :request assoc :store database/store))

(def db-interceptor
  {:name  :db-interceptor
   :enter assoc-store})

(defn hello-function [request]
  {:status 200 :body (str "Hello world " (get-in request [:query-params :name] "everyone"))})

(defn create-task-map [uuid name status]
  {:id uuid :name name :status status})

(defn create-task [request]
  (let [uuid (java.util.UUID/randomUUID)
        name (get-in request [:query-params :name])
        status (get-in request [:query-params :status])
        task (create-task-map uuid name status)
        store (:store request)]
    (swap! store assoc uuid task)
    {:status 200 :body {:message "Task created"
                        :task    task}}
    ))

(defn list-tasks [request]
  {:status 200 :body @(:store request)})

(def routes (route/expand-routes
              #{["/hello" :get hello-function :route-name :hello-world]
                ["/task" :post [db-interceptor create-task] :route-name :create-task]
                ["/list-tasks" :get [db-interceptor list-tasks] :route-name :list-tasks]}))

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

(defn stop-server []
  (http/stop @server))

(defn restart-server []
  (stop-server)
  (start-server))

(start-server)
;(restart-server)

(test-request :get "/hello?name=fernando")
(test-request :post "/task?name=eat&status=pending")

(test-request :get "/list-tasks")