(ns cyphers.core-test
  (:require [clojure.test :as t]
            [cyphers.core :as core]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]))

(t/deftest rot-13-test
  (t/testing "rot13 test"
    (t/is (= "hello" (core/rot-by (core/rot-by "hello" 13) 13)))))
