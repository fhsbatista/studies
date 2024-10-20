(ns clojure-for-brave.regex)

(defn find-and-print
  [finding-str str-found]
  (println (str "Searching for \"" finding-str "\": " str-found)))

(defn -main []
  (find-and-print "^left-" (re-find #"^left-" "left-eye")) ;returns left-
  (find-and-print "^left-" (re-find #"^left-" "right-eye")) ;returns nil
  (find-and-print "^left-" (re-find #"^left-" "cleft-eye")) ;returns nil
  (find-and-print "left-" (re-find #"left-" "cleft-eye")) ;returns left-
  )