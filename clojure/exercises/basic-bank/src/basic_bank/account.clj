(ns basic-bank.account)

(defn create-account [document name]
  (let [filename (str  "accounts/" document ".txt")]
    (spit filename (str "Owner document: " document))
    (spit filename (str "\nOwner name: " name) :append true)
    (spit filename (str "\nInitial balance: 0.0") :append true)
    {:document document
     :name name
     :balance 0.0
     :filename filename}))

(defn deposit [account value]
  (let [updated-balance (+ (account :balance) value)]
    (spit (account :filename) (str "\nDeposit: " value " | Balance: " updated-balance) :append true)
    (assoc account :balance updated-balance)))


