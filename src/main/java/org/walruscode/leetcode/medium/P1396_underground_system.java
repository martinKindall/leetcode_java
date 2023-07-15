package org.walruscode.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class P1396_underground_system {

    class UndergroundSystem {

        private Map<Integer, String> checkIns;
        private Map<String, Integer> checkInTime;
        private Map<String, double[]> averages;

        public UndergroundSystem() {
            checkIns = new HashMap<>();
            checkInTime = new HashMap<>();
            averages = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            checkIns.put(id, stationName);
            checkInTime.put(id + stationName, t);
        }

        public void checkOut(int id, String stationName, int t) {
            String initialStation = checkIns.get(id);
            int checkIn = checkInTime.get(id + initialStation);
            int time = t - checkIn;
            String averageId = initialStation + "/" + stationName;

            double[] average = averages.get(averageId);
            if (average == null) {
                average = new double[2];
                average[0] = (double) time;
                average[1] = 1.0;
                averages.put(averageId, average);
            } else {
                double averageSum = average[0] * average[1];
                averageSum += time;
                average[1] += 1.0;
                average[0] = averageSum / average[1];
            }
        }

        public double getAverageTime(String startStation, String endStation) {
            String averageId = startStation + "/" + endStation;
            double[] average = averages.get(averageId);

            return average[0];
        }
    }
}
