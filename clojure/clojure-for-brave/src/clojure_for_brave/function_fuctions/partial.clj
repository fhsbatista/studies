(ns clojure-for-brave.function-fuctions.partial)

(def add3 (partial + 3))

(defn example-sum []
  (println "Using partial to sum 3 to a given number")
  (println "(add3 3) => 6")
  (println (add3 3)) ;=> 6
  (println "(add3 4) => 7")
  (println (add3 4))) ;=> 7

(defn send-email [user msg]
  (println (str "Sending message to " user " : " msg)))

(def send-email-to-john
  (partial send-email "John"))

(defn example-send-email []
  (send-email-to-john "Hello John!"))

(def add-to-vector
  (partial conj [1 2 3 4]))

(defn example-merge-seqs []
  (println "Using partial do merge two vectores")
  (println (add-to-vector 5 6 7)))

(defn -main []
  (println "---------------------------")
  (println "(partial) examples")
  (println "(partial) is useful to call functions with pre-defined arguments")
  (example-sum)
  (example-send-email)
  (example-merge-seqs))