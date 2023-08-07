package org.walruscode.leetcode;

public class P2325_decode_message {

    public String decodeMessage(String key, String message) {
        int[] keyMap = new int[122 - 97 + 1];

        int position = 1;

        for (char c: key.toCharArray()) {
            if (position > 26) break;
            if (c == ' ') continue;
            int pos = c - 97;
            if (keyMap[pos] == 0) keyMap[pos] = position++;
        }

        char[] result = new char[message.length()];

        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ')
                result[i] = ' ';
            else
                result[i] = (char) (keyMap[message.charAt(i) - 97] - 1 + 97);
        }

        return new String(result);
    }
}
