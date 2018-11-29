(ns kira-system.core-test
  (:use clojure.test)
  (:use kira-system.core))

(deftest test-dbl-book
  ;; test1
  (def s1 (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]
                [(create-date 2018 10 29 8 30) (create-date 2018 10 29 9 0)]]))
  (def s1-ans (seq [(seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                          [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]])]))
  
  (testing "One Double Book"
    (is (= (find-dbl-booked-pairs s1) s1-ans)))
  
  ;; test2
  (def s2 (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                [(create-date 2018 10 29 8 30) (create-date 2018 10 29 9 0)]]))
  (def s2-ans (sequence []))
  
  (testing "No Double Book"
    (is (= (find-dbl-booked-pairs s2) s2-ans)))
  
  ;; test3
  (def s3 (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                [(create-date 2018 10 29 7 20) (create-date 2018 10 29 7 50)]
                [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]
                [(create-date 2018 10 29 8 30) (create-date 2018 10 29 9 0)]]))
  (def s3-ans (seq [(seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                          [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]])
                    (seq [[(create-date 2018 10 29 7 20) (create-date 2018 10 29 7 50)]
                          [(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 30)]])
                    (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                          [(create-date 2018 10 29 7 20) (create-date 2018 10 29 7 50)]])]))
  
  (testing "Multiple Double Books"
    (is (= (set (find-dbl-booked-pairs s3)) (set s3-ans))))
  
  ;; test4
  (def s4 (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 29 8 0)]
                [(create-date 2018 10 30 7 20) (create-date 2018 10 30 7 50)]]))
  (def s4-ans (sequence []))
  (testing "Same Hours, Different Date"
    (is (= (find-dbl-booked-pairs s4) s4-ans)))
  
  ;; test5
  (def s5 (seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 30 8 0)]
                [(create-date 2018 10 29 12 20) (create-date 2018 10 29 13 50)]]))
  (def s5-ans (seq [(seq [[(create-date 2018 10 29 7 30) (create-date 2018 10 30 8 0)]
                          [(create-date 2018 10 29 12 20) (create-date 2018 10 29 13 50)]])]))
  (testing "Double Book with day long Schedule"
    (is (= (find-dbl-booked-pairs s5) s5-ans))))


(run-tests)
