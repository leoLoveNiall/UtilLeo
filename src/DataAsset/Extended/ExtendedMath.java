package DataAsset.Extended;

import java.util.Arrays;

public class ExtendedMath {

    public static double getAverage(int[] arr) {
        return Arrays.stream(arr).asDoubleStream().sum() / arr.length;
    }

    public static double getAverage(double[] arr) {
        return Arrays.stream(arr).sum() / arr.length;
    }

    //使用欧几里得算法求解数m和数n最大公约数
    public static int getGreatestCommonDivisor(int m, int n) {
        while (n > 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    //求解数m和n和最小公倍数
    public static int getLeastCommonMultiple(int m, int n) {
        int gcd = getGreatestCommonDivisor(m, n);
        return m * n / gcd;
    }

}
