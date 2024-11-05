(ns brasfoot.match)

(defn event [seed]
  (if (even? seed)
    {:team :a
     :result :goal}
    {:team :b
     :result :goal}))

(defn start []
  (let [match-result (event (rand-int 10))]
    (println match-result)))