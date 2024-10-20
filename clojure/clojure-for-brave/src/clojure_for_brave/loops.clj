(ns clojure-for-brave.loops)

(defn start-loop []
  (loop [iteration 0]
    (if (> iteration 3)
      (println "Goodbye!")
      (do
        (println (str "Iteration: " iteration))
        (recur (inc iteration))))))

(defn -main []
  (start-loop))