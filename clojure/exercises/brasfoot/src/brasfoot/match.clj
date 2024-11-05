(ns brasfoot.match)

(defn event [seed]
  (if (even? seed)
    {:team :a
     :result :goal}
    {:team :b
     :result :goal}))

(defn start []
  (let [events (take 90 (repeatedly #(event (rand-int 11))))
        count-goals (fn [team] (count (filter #(and (= (:team %) team) (= (:result %) :goal)) events)))
        goals-a (count-goals :a)
        goals-b (count-goals :b)]
    (if (> goals-a goals-b)
      :a
      :b)))