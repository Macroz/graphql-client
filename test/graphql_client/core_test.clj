(ns graphql-client.core
  (:use [clojure.test]))

(deftest arg->str-test []
  (is (= "" (arg->str nil)))
  (is (= "1" (arg->str 1)))
  (is (= "\"abc\"" (arg->str "abc")))
  (is (= "\"abc\"" (arg->str :abc)))
  (is (= "{y:2}" (arg->str {:y 2})))
  (is (= "[1,2,3]" (arg->str [1 2 3]))))

(deftest args->str-test []
  (is (= "" (args->str {})))
  (is (= "x:1" (args->str {:x 1})))
  (is (= "x:1,y:2" (args->str {:x 1 :y 2})))
  (is (= "x:{y:2}" (args->str {:x {:y 2}})))
  (is (= "v:[1,2,3]" (args->str {:v [1 2 3]}))))

(deftest query->str-test []
  (is (= "" (query->str nil)))
  (is (= "" (query->str [])))
  (is (= "q" (query->str [:q])))
  (is (= "q{r}" (query->str [:q [:r]])))
  (is (= "{q}" (query->str [[:q]])))
  (is (= "{q{a}}" (query->str [[:q :a]])))
  (is (= "{q{a b}}" (query->str [[:q :a :b]])))
  (is (= "{q{a}}" (query->str [[:q :a]]))))

