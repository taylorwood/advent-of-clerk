;; # 🎄 Advent of Clerk: Day 4
(ns advent-of-clerk.day-04
  (:require
    [clojure.java.io :as io]
    [clojure.string :as s]))

(def input
  (s/split-lines (slurp (io/resource "day_04.txt"))))
(defn parse-line [s]
  (let [[a b c d] (map parse-long (s/split s #"[^\d]"))]
    [[a b] [c d]]))
(def ranges
  (map parse-line input))

;; part 1
(count
  (filter
    (fn [[[a b] [c d]]]
      (or (and (<= a c) (<= d b))
          (and (<= c a) (<= b d))))
    ranges))

;; part 2
(count
  (filter
    (fn [[[a b] [c d]]]
      (and (<= a d) (<= c b)))
    ranges))
