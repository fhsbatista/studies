(ns clojure-for-brave.sequences.filter-some)

(defn long-word? [word]
  (> (count word) 6))

(defn example-filter []
  (println "-----------------------")
  (println "(filter) example")
  (println "(filter) selects all elements that test true to a predicate function")
  (println "Here, words which length is greater than 6 will be filtered")
  (let [words ["Apple" "Banana" "Strawberry" "Blueberry" "Coco" "Pineapple"]
        long-words (filter #(long-word? %) words)]
    (println (str "Words: " words))
    ;(filter) returns lazy sequence, so we need to evaluate it in order to print, hence the "vec" function
    (println (str "Filtered long words: " (vec long-words)))) ; => Strawberry, Blueberry, Pineapple
  )

(defn example-some []
  (println "-----------------------")
  (println "(some) example")
  (println "(some) returns true if any element that test true for a predicate function")
  (println "(some) is the similar to any? in ruby")
  (let [words ["Apple" "Banana" "Strawberry" "Blueberry" "Coco" "Pineapple"]
        long-words? (some long-word? words)]
    (println (str "Words: " words))
    (println (str "Contains a word greater than 6 chars?: " long-words?))) ; => true
  (let [words ["Apple" "Banana" "Grape" "Coco"]
        long-words? (some long-word? words)]
    (println (str "Words: " words))
    (println (str "Contains a word greater than 6 chars?: " long-words?))) ; => nil
  )

(defn -main []
  (example-filter)
  (example-some))