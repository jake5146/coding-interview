# kira-system

Function name: find-dbl-booked-pairs

Input: a sequence of 2-elements containing lists (seq of [start_time_i end_time_i] where i = 0,...,N for N =number of bookings/schedules)


Output: a sequence of all double booked pairs

(where pair is a sequence of two 2-elements containing lists;

([start1, end1] [start2, end2]), for example).

And type of start_time and end_time is in java.util.Date.


Coding has been done in the environment of NightCode 2.6.0.

## Usage

In NightCode IDE, clicking Run will execute Main function to test one sequence of 4 bookings and return the evaluated output.

Clicking Test will execute 5 testings which are included in test file (test/kira_system/core-test.clj).
