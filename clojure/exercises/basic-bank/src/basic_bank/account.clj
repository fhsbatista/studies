(ns basic-bank.account)

(defn create-account [document name]
  (let [filename (str  "accounts/" document ".txt")]
    (spit filename (str "Owner document: " document))
    (spit filename (str "\nOwner name: " name) :append true)
    (spit filename (str "\nInitial balance: 0.0") :append true)
    {:document document
     :name name
     :filename filename}))