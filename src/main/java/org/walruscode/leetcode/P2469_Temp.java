package org.walruscode.leetcode;

public class P2469_Temp {

    public double[] convertTemperature(double celsius) {

        double[] result = new double[2];
        result[0] = celsiusToKelvin(celsius);
        result[1] = celsiusToFahrenheit(celsius);

        return result;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 1.8 + 32.0;
    }
}
