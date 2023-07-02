package org.walruscode.leetcode;

public class P832_filpping_an_image {

    public int[][] flipAndInvertImage(int[][] image) {

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image.length / 2; j++) {
                int temp = image[i][j];

                temp = temp == 1 ? 0 : 1;

                image[i][j] = image[i][image.length - j - 1];

                image[i][j] = image[i][j] == 1 ? 0 : 1;

                image[i][image.length - j - 1] = temp;
            }

            if (image.length % 2 != 0) {
                int col = image.length / 2;
                image[i][col] = image[i][col] == 1 ? 0 : 1;
            }
        }

        return image;
    }
}
