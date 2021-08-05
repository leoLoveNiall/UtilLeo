package DataAsset.ArrayUtil;

import java.util.Arrays;

/**
 * This class is designed to offer efficient methods that helps programmer streamline codes.
 * All methods are static, and you need not to instantiate ArrayUtil.
 */
public class ArrayUtil {
    public static Object[] reverse(Object[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            Object tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static byte[] reverse(byte[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            byte tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static boolean[] reverse(boolean[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            boolean tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static double[] reverse(double[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            double tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static char[] reverse(char[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            char tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static long[] reverse(long[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            long tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 5};
        reverse(test);
        System.out.println(Arrays.toString(test));
    }
}

