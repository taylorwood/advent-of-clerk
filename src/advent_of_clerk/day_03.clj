;; # 🎄 Advent of Clerk: Day 3
(ns advent-of-clerk.day-03
  (:require
    [clojure.java.io :as io]
    [clojure.string :as s]))

(def input
  (s/split-lines (slurp (io/resource "day_03.txt"))))

(def char->priority
  (into {}
        (map #(vector (char %1) %2)
             (concat (range (int \a) (inc (int \z)))
                     (range (int \A) (inc (int \Z))))
             (next (range)))))

;; part 1
(apply +
  (for [sack input
        :let [[c1 c2] (split-at (/ (count sack) 2) sack)
              shared (first (clojure.set/intersection (set c1) (set c2)))]]
    (char->priority shared)))

;; part 2
(apply +
  (for [[a b c] (partition 3 input)
        :let [shared (first (clojure.set/intersection (set a) (set b) (set c)))]]
    (char->priority shared)))