(ns clojure-service.routes
  (:require [io.pedestal.http.route :as route]
            [com.stuartsierra.component :as component]))

(defrecord Routes []
  component/Lifecycle

  (start [this]
    (println "Starting routes")
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

    (assoc this :endpoints routes))

  (stop [this]
    (println "Stopping routes")
    (assoc this :endpoints nil)))


(defn new-routes []
  (->Routes))

