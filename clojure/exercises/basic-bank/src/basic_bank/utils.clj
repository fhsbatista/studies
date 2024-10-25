(ns basic-bank.utils
  (:import [java.time LocalDateTime]
           [java.time.format DateTimeFormatter]))

(defn current-date []
  (let [now (LocalDateTime/now)
        formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss")]
    (.format now formatter)))

(defn account-filename [document]
  (str "accounts/" document ".json"))