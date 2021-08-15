package DataAsset.Random;

import DataAsset.Extend.MathExtend;
import DataAsset.Extend.NumberExtend;

import java.util.Arrays;

public class RandomNumber {
    public static int getRandomInt(int from, int to) {
        int range = to - from;
        int size = (int) (Math.pow(10, NumberExtend.getDigit(range) + 3) * Math.random());
        int result = size % range + from;
        if (result > to || result < from) try {
            throw new InputFormatUnfitException();
        } catch (InputFormatUnfitException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getRandomInt(int from1, int to1, int from2, int to2) {
        int range = to1 - from1 + to2 - from2;
        int rand = getRandomInt(0, range);
        if (rand < to1 - from1) return getRandomInt(from1, to1);
        else return getRandomInt(from2, to2);
    }

    public static int[] getRandomIntArray(int from, int to, int amount) {
        var arr = new int[amount];
        for (int i = 0; i < amount; i++) {
            arr[i] = getRandomInt(from, to);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(getRandomInt(100, 200, 300, 400));
    }
}

class InputFormatUnfitException extends Exception {
    public InputFormatUnfitException() {
    }

    public InputFormatUnfitException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "InputFormatUnfitException";
    }
}