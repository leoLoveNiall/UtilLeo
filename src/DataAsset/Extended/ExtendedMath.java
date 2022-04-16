package DataAsset.Extended;

import java.util.Arrays;

public class ExtendedMath {

    public static double getAverage(int[] arr) {
        return Arrays.stream(arr).asDoubleStream().sum() / arr.length;
    }

    public static double getAverage(double[] arr) {
        return Arrays.stream(arr).sum() / arr.length;
    }

    public static void main(String[] args) {
        System.out.println(getAverage(new double[]{1, 2, 3.3}));
    }
}
