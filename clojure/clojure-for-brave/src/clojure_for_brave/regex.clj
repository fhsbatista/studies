(ns clojure-for-brave.regex
  (:require [clojure.string :as str]))

(defn find-and-print
  [finding-str str-found]
  (println (str "Searching for \"" finding-str "\": " str-found)))

(defn find-and-replace
  [text pattern new-text]
  (str/replace text pattern new-text))

(defn -main []
  (find-and-print "^left-" (re-find #"^left-" "left-eye")) ;returns left-
  (find-and-print "^left-" (re-find #"^left-" "right-eye")) ;returns nil
  (find-and-print "^left-" (re-find #"^left-" "cleft-eye")) ;returns nil
  (find-and-print "left-" (re-find #"left-" "cleft-eye")) ;returns left-
  (println (find-and-replace "left eye" #"^left" "right" )) ;return "right eye"
  )