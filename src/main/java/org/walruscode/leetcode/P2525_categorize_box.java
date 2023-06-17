package org.walruscode.leetcode;

public class P2525_categorize_box {

    public String categorizeBox(int length, int width, int height, int mass) {
        boolean isBulky = false;
        boolean isHeavy = false;

        int bulkyLimit = 10000;
        if (length >= bulkyLimit || width >= bulkyLimit || height >= bulkyLimit) isBulky = true;
        else if ((long) length * width * height >= 1000000000L) isBulky = true;

        if (mass >= 100) isHeavy = true;

        if (isBulky && isHeavy) return "Both";
        if (isBulky) return "Bulky";
        if (isHeavy) return "Heavy";

        return "Neither";
    }
}
