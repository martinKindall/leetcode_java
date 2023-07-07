package org.walruscode.leetcode.medium;

public class P1041_robot_circle {

    public boolean isRobotBounded(String instructions) {

        int direction = 0;
        int[] position = new int[2];

        int cycles = 0;

        while (cycles < 4) {
            for (int i = 0; i < instructions.length(); i++) {
                if (instructions.charAt(i) == 'G') {
                    nextPosition(direction, position);
                } else if (instructions.charAt(i) == 'L') {
                    direction = Math.floorMod(direction - 1, 4);
                } else {
                    direction = (direction + 1) % 4;
                }
            }

            if (direction == 0 && position[0] == 0 && position[1] == 0) return true;
            cycles++;
        }

        return false;
    }

    void nextPosition(int direction, int[] position) {
        switch (direction) {

            // north
            case 0: position[1] = position[1] + 1; break;
            // west
            case 3: position[0] = position[0] - 1; break;
            // south
            case 2: position[1] = position[1] - 1; break;
            // east
            case 1: position[0] = position[0] + 1; break;
            default:
        }
    }
}
