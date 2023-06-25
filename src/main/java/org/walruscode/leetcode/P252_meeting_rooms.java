package org.walruscode.leetcode;

import java.util.*;

public class P252_meeting_rooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 1) return true;

        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        int prev = -1;

        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] < prev) return false;
            prev = intervals[i][1];
        }

        return true;
    }

    public boolean canAttendMeetingsOld(int[][] intervals) {
        if (intervals == null || intervals.length == 1) return true;

        Map<Integer, Integer> myIntervals = new HashMap<>();

        for (int i = 0; i < intervals.length; i++) {
            if (myIntervals.containsKey(intervals[i][0])) return false;
            myIntervals.put(intervals[i][0], intervals[i][1]);
        }

        List<Integer> keys = new ArrayList<>(myIntervals.keySet());
        Collections.sort(keys);

        int prev = -1;
        for (int i = 0; i < keys.size(); i++) {

            if (keys.get(i) < prev) return false;

            prev = myIntervals.get(keys.get(i));
        }

        return true;
    }
}
