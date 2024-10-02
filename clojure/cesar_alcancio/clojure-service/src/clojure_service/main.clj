(ns clojure-service.main
  (:require [clojure-service.server :as server]
            [com.stuartsierra.component :as component]
            [clojure-service.database :as database]
            [clojure-service.routes :as routes]))

(defn my-component-system []
  (component/system-map
    :database (database/new-database)
    :routes (routes/new-routes)
    :server (component/using (server/new-server) [:database :routes])))

(def component-result (component/start (my-component-system)))
(def test-request (-> component-result :server :test-request))

(server/test-request :get "/hello?name=fernando")
(server/test-request :post "/task?name=eat&status=pending")
(server/test-request :post "/task?name=run&status=done")
(server/test-request :post "/task?name=study&status=done")
(clojure.edn/read-string (:body (server/test-request :get "/list-tasks")))
(server/test-request :delete "/task/42411d1e-ae45-492d-87f0-2987ce3b0ed5")
(server/test-request :patch "/task/15f5adcd-1f1e-4cd5-b37e-d297cc648b47?name=futebol&status=done")