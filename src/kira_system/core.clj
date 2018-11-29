(ns kira-system.core
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo]))

;;set timezone to UTC for readability 
(java.util.TimeZone/setDefault (java.util.TimeZone/getTimeZone "UTC"))

(defn create-date
  "Create Date by given arguments (year, month, day, hours, mins)"
  [year month day hours mins] 
  (java.util.Date. (- year 1900) (- month 1) day hours mins))

(defn is-dbl-booked
  "Return true if a pair of schedules is double-booked."
  [s1 s2]
  (or (and (or (.before (s1 0) (s2 0)) 
               (= (.compareTo (s1 0) (s2 0)) 0)) 
           (.before (s2 0) (s1 1)))
      (and (or (.before (s2 0) (s1 0))
               (= (.compareTo (s2 0) (s1 0)) 0))
           (.before (s1 0) (s2 1)))))

(defn find-dbl-booked-pairs
  "Return a sequence of all double-booked pairs from given sequence of schedules.
  
  Argument (schedules): contains sequence of schedule (i.e. list of start time and
  end time). 
  >> schedules = ([start_time_0 end_time_0] .. [start_time_i end_time_i] 
                  ... [start_time_N end_time_N])
  for N number of schedules.
  (Assume that start_time_i and end_time_i are in a type of java.util.Date.)
  
  Return empty sequence iff no pair has been double-booked.
         sequence of sequence of two lists (i.e. sequence of date pairs) iff
         any pair has been double-booked.
  "
  [schedules]
  (filter #(is-dbl-booked (first %) (last %)) (combo/combinations schedules 2)))

(defn -main []
  (def schedules (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                       [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]
                       [(create-date 2018 10 29 8 0) (create-date 2018 10 29 9 0)]
                       [(create-date 2018 10 29 9 30) (create-date 2018 10 29 10 0)]]))
  (println (find-dbl-booked-pairs schedules)))

