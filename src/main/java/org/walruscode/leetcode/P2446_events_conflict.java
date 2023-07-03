package org.walruscode.leetcode;

public class P2446_events_conflict {

    public boolean haveConflict(String[] event1, String[] event2) {
        String[] event1Start = event1[0].split(":");
        String[] event1End = event1[1].split(":");

        String[] event2Start = event2[0].split(":");
        String[] event2End = event2[1].split(":");

        return toMinutes(event1End[0], event1End[1]) >= toMinutes(event2Start[0], event2Start[1]) &&
                toMinutes(event1Start[0], event1Start[1]) <= toMinutes(event2End[0], event2End[1]) ||

                // this seems to be not necessary for the test cases
                // but one should not assume event2 will always happen later than event1
                toMinutes(event2End[0], event2End[1]) >= toMinutes(event1Start[0], event1Start[1]) &&
                        toMinutes(event2Start[0], event2Start[1]) <= toMinutes(event1End[0], event1End[1]);
    }

    int toMinutes(String hour, String minutes) {
        return Integer.parseInt(minutes) + 60 * Integer.parseInt(hour);
    }
}
