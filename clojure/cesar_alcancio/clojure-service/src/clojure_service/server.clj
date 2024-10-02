(ns clojure-service.server
  (:require [io.pedestal.http.route :as route]
            [io.pedestal.http :as http]
            [io.pedestal.test :as test]
            [clojure-service.database :as database]
            [io.pedestal.interceptor :as i]))


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

(defn delete-task [request]
  (let [store (:store request)
        task-id (get-in request [:path-params :id])
        task-id-uuid (java.util.UUID/fromString task-id)]
    (swap! store dissoc task-id-uuid)
    {:status 200 :body {:message "Removido com sucesso"}}))

(defn update-task [request]
  (let [task-id (get-in request [:path-params :id])
        task-id-uuid (java.util.UUID/fromString task-id)
        name (get-in request [:query-params :name])
        status (get-in request [:query-params :status])
        task (create-task-map task-id-uuid name status)
        store (:store request)]
    (swap! store assoc task-id-uuid task)
    {:status 200 :body {:message "Task update"
                        :task    task}}
    ))

(def routes (route/expand-routes
              #{["/hello" :get hello-function :route-name :hello-world]
                ["/task" :post [create-task] :route-name :create-task]
                ["/list-tasks" :get [list-tasks] :route-name :list-tasks]
                ["/task/:id" :delete [delete-task] :route-name :delete-task]
                ["/task/:id" :patch [update-task] :route-name :update-task]}))

(def service-map-base {::http/routes routes
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



(start-server)
;(restart-server)
;(test-request :get "/hello?name=fernando")
;(test-request :post "/task?name=eat&status=pending")
;(test-request :post "/task?name=run&status=done")
;(test-request :post "/task?name=study&status=done")
;(clojure.edn/read-string (:body (test-request :get "/list-tasks")))
;(test-request :delete "/task/3285c74e-55a7-4ed5-8ec9-070c71d4bb79")
;(test-request :patch "/task/15f5adcd-1f1e-4cd5-b37e-d297cc648b47?name=futebol&status=done")
;(clean-store)
