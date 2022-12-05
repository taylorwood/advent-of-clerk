;; # 🎄 Advent of Clerk: Day 2
(ns advent-of-clerk.day-02
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(def input
  (s/split-lines (slurp (io/resource "day_02.txt"))))

(def move-map {"A" :rock, "B" :paper, "C" :scissors
               "X" :rock, "Y" :paper, "Z" :scissors})
(def score-map {:rock 1 :paper 2 :scissors 3})

(defn move-history [move-map]
  (for [line input
        :let [[them _ us] line]]
    [(move-map (str them)) (move-map (str us))]))

(move-history move-map)

(def win-map
  {:rock :scissors
   :scissors :paper
   :paper :rock})

(defn move-score [x y]
  (let [x-win? (= (win-map x) y)
        x-score (score-map x)
        y-win? (= (win-map y) x)
        y-score (score-map y)]
    (cond
      (and (not x-win?) (not y-win?)) [(+ 3 x-score) (+ 3 y-score)]
      x-win? [(+ 6 x-score) y-score]
      y-win? [x-score (+ 6 y-score)])))

(move-score :rock :paper)
(move-score :paper :rock)
(move-score :scissors :scissors)

(def round-scores
  (map #(apply move-score %) (move-history move-map)))

;; part 1

(apply + (map second round-scores))

(def move-map-2 (assoc move-map "X" :lose, "Y" :draw, "Z" :win))
(def lose-map (clojure.set/map-invert win-map))

(def strategic-moves
  (for [[them us] (move-history move-map-2)]
    [them (case us
            :win (lose-map them)
            :lose (win-map them)
            :draw them)]))

(def second-round-scores
  (map #(apply move-score %) strategic-moves))

;; part 2
(apply + (map second second-round-scores))
