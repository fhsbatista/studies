(ns clojure-for-brave.sequences)

(defn greeting [name]
  (str "Hello, " name))

(defn iterate-greetings [names]
  (let [messages (map greeting names)]
    (doseq [message messages] (println message))))

(defn -main []
  ;See `map` will work on any data structure that look like a sequence (which is a collection where all nodes have "before" and "after")
  ;This is the concept of abstractions on clojure. Sequence is an abstraction and map function relies on it.
  (iterate-greetings ["John" "Mark" "Ivan"])
  (iterate-greetings '("John" "Mark" "Ivan"))
  (iterate-greetings #{"John" "Mark" "Ivan"}))