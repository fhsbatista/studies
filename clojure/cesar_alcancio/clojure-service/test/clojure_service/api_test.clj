(ns clojure-service.api-test
  (:require [clojure.test :refer :all]
            [clojure-service.server :as server]
            [com.stuartsierra.component :as component]
            [clojure-service.database :as database]
            [clojure-service.routes :as routes])
  (:use [clojure.pprint]))

(def my-component-system
  (component/system-map
    :database (database/new-database)
    :routes (routes/new-routes)
    :server (component/using (server/new-server) [:database :routes])))

(def component-result (component/start my-component-system))
(def test-request (-> component-result :server :test-request))

(deftest task-api-test
  (testing "Hello World Test"
    (let [path "/hello?name=fernando"
          response (test-request :get path)
          body (:body response)]
      (is (= "Hello world fernando" body))))

  (testing "CRUD Test"
    (let [_ (test-request :post "/task?name=eat&status=pending")
          _ (test-request :post "/task?name=run&status=done")
          tasks (clojure.edn/read-string (:body (test-request :get "/list-tasks")))
          task1 (-> tasks first second )
          task1-id (:id task1)
          task2 (-> tasks second second)
          task2-id (:id task2)
          _ (test-request :delete (str "/task/" task1-id))
          _ (test-request :patch (str "/task/" task2-id "?name=futebol&status=done"))
          processed-tasks (clojure.edn/read-string (:body (test-request :get "/list-tasks")))
          task-updated (-> processed-tasks vals first)]

      (is (= 2 (count tasks)))
      (is (= "eat" (:name task1)))
      (is (= "pending" (:status task1)))
      (is (= "run" (:name task2)))
      (is (= "done" (:status task2)))
      (is (= 1 (count processed-tasks)))
      (is (= "futebol" (:name task-updated)))
      (is (= "done" (:status task-updated))))))

;(test-request :get "/hello?name=fernando")
;(test-request :post "/task?name=eat&status=pending")
;(test-request :post "/task?name=run&status=done")
;(test-request :post "/task?name=study&status=done")
(clojure.edn/read-string (:body (test-request :get "/list-tasks")))
;(test-request :delete "/task/42411d1e-ae45-492d-87f0-2987ce3b0ed5")
;(test-request :patch "/task/15f5adcd-1f1e-4cd5-b37e-d297cc648b47?name=futebol&status=done")