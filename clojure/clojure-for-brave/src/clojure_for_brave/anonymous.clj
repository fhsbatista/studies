(ns clojure-for-brave.anonymous)

(defn anonymous-calls
  []
  (println ((fn [x] (* x 3)) 8))) ;prints 24

(def squared (fn [x] (* x x))) ;anonymous function with a name

;#(%) % is the argument. Ex: (#(* % 2) 10) => 20
(def greetings (map #(str "Hi, " %) ;anonymous function compacted
                    ["Cacau" "Meg" "Mel"]))

(def greetings-rest-args
  (#(identity %&) 1 2 3)) ;%& allows to deconstruct parameteres list (rest) ;identity returns the same value it is given

(defn -main
  []
  (println "Anonymous functions")
  (anonymous-calls)
  (println (str "5 squared is: " (squared 5)))
  (println (str "4 squared is: " (#(* % %) 4))) ; "%" is the argument. more compact way of making anonymous function.
  (doseq [hi greetings] (println hi))
  (doseq [value greetings-rest-args] (println value)))
