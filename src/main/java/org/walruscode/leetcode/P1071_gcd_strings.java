package org.walruscode.leetcode;

public class P1071_gcd_strings {

    // this is not a good solution, but worked.
    // a better approach is using the string str1 + str2 == str2 + str1 ?
    // and based on that just return the substring using gcd of the lengths.
    public String gcdOfStrings(String str1, String str2) {

        String result = "";

        int minLength =  gcd(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++) {
            String current1 = str1.substring(0, i+1);
            String current2 = str2.substring(0, i+1);

            if (!current1.equals(current2)) break;

            int times = str1.length() / current1.length();

            if (!current1.repeat(times).equals(str1)) continue;

            times = str2.length() / current2.length();

            if (!current2.repeat(times).equals(str2)) continue;

            result = current1;
        }

        return result;
    }

    int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
