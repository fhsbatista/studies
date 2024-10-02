(ns clojure-service.main
  (:require [clojure-service.server :as server]))

(server/start)

(server/test-request :get "/hello?name=fernando")
(server/test-request :post "/task?name=eat&status=pending")
(server/test-request :post "/task?name=run&status=done")
(server/test-request :post "/task?name=study&status=done")
(clojure.edn/read-string (:body (server/test-request :get "/list-tasks")))
(server/test-request :delete "/task/3285c74e-55a7-4ed5-8ec9-070c71d4bb79")
(server/test-request :patch "/task/15f5adcd-1f1e-4cd5-b37e-d297cc648b47?name=futebol&status=done")