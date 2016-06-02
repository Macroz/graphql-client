(ns graphql-client.core)

(declare args->str)

(defn arg->str [v]
  (cond (map? v) (str "{" (args->str v) "}")
        (keyword? v) (str "\"" (name v) "\"")
        (string? v) (str "\"" v "\"")
        (coll? v) (str "[" (apply str (interpose "," (map arg->str v))) "]")
        :else (str v)))

(defn args->str [m]
  (->> (for [[k v] m]
         [(name k) ":" (arg->str v)])
       (interpose ",")
       (flatten)
       (apply str)))

(defn query->str [q]
  (if (keyword? q)
    (name q)
    (let [f (first q)
          s (second q)
          r (if (map? s) (drop 2 q) (rest q))]
      (println f s r)
      (apply str (flatten (cond (keyword? f) [(name f)
                                              (if (map? s) ["(" (args->str s) ")"] "")
                                              (when (seq r) ["{" (interpose " " (map query->str r)) "}"])]
                                (vector? f) ["{" (map query->str q) "}"]
                                :else "")))
      )))
