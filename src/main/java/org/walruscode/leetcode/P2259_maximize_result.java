package org.walruscode.leetcode;

public class P2259_maximize_result {

    public String removeDigit(String number, char digit) {

        int lastOccurrence = -1;

        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);

            if (current == digit) {
                lastOccurrence = i;
                if (((i + 1) < number.length()) && number.charAt(i+1) > digit) {
                    break;
                }
            }
        }

        return number.substring(0, lastOccurrence) +
                number.substring(lastOccurrence + 1);
    }

    public String removeDigitV2(String number, char digit) {

        long max = 0;

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                long current = Long.parseLong(number.substring(0, i) + number.substring(i+1, number.length()));
                if (current > max) max = current;
            }
        }

        return String.valueOf(max);
    }
}
