(ns brasfoot.match-test
  (:require [clojure.test :refer :all]
            [brasfoot.match :refer :all]))


(deftest event-test
  (testing "When seed is even, team A gets a :goal"
    (let [result (event 2)]
      (is (= (:team result) :a))
      (is (= (:result result) :goal))))

  (testing "When seed is odd, team B gest :goal"
    (let [result (event 1)]
      (is (= (:team result) :b))
      (is (= (:result result) :goal))))
  
  (testing "When seed is 0, team A gest :goal"
    (let [result (event 0)]
      (is (= (:team result) :a))
      (is (= (:result result) :goal))))) 