;; # 🎄 Advent of Clerk: Day 1
(ns advent-of-clerk.day-01
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def input
  (slurp (io/resource "day_01.txt")))
(def elves
  (map s/split-lines (s/split input #"\n\n")))
(def elf-calorie-totals
  (for [elf-food-items elves]
    (apply + (map parse-long elf-food-items))))
;; part 1
(apply max elf-calorie-totals)
;; part 2
(apply + (take 3 (sort #(compare %2 %1) elf-calorie-totals)))
