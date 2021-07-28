package DataAsset;

import java.util.*;

enum Sign {
    positive(1), negative(-1), undefined(0);
    int value;

    Sign(int i) {
        value = i;
    }

    int getValue() {
        return value;
    }
}

public class BigInteger {
    private Sign sign = Sign.undefined;
    private byte[] digit = null;

    /**
     * Generates a BigInteger object using an input string.
     *
     * @param input The input string should be like: ±1234,±001234, ±00, ±0.
     *              Rest of forms will not be allowed and causes an exception subsequently.
     */
    BigInteger(String input) {
        if (isLegit(input)) {
            byte[] byteArr = new byte[input.length() - 1];
            switch (input.charAt(0)) {
                case '+' -> this.sign = Sign.positive;
                case '-' -> this.sign = Sign.negative;
            }
            for (byte i = 0; i < input.length() - 1; i++) {
                byteArr[i] = Byte.parseByte(String.valueOf(input.charAt(i + 1)));
            }
            setDigit(byteArr);
        } else {
            System.out.println("Format error.");
        }
    }

    public BigInteger(Sign sign, byte[] digit) {
        this.sign = sign;
        setDigit(digit);
    }

    /**
     * Remove the leading zeros in the byte array.
     *
     * @param byteArr Receive pure digit data from request method.
     * @return Return filtered digit data.
     */
    private byte[] removeLeadingZero(byte[] byteArr) {

        int firstNonZeroAt = 0;
        //This variable restricts maximum number of digits to less than 2,147,483,647.

        try {
            while (byteArr[firstNonZeroAt] == 0) {
                if (byteArr[++firstNonZeroAt] != 0) break;
            }
        } catch (IndexOutOfBoundsException e) {
            return new byte[]{0};
            //If the index equals to length, the only occasion has to be +000... or -000....
        }

        return Arrays.copyOfRange(byteArr, firstNonZeroAt, byteArr.length);
    }

    /**
     * The method expects a string of BigInteger and judge if the string is acceptable.
     *
     * @param input The input string should be like: ±1234,±001234, ±00, ±0.
     */
    private boolean isLegit(String input) {
        if (input.length() <= 1) return false;
        if (!(input.charAt(0) == '-' || input.charAt(0) == '+')) return false;
        for (var i = 1; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }

    /**
     * Converts this BigInteger object to normal string.
     * Free to decide sign visibility.
     */
    public String toString(boolean showSign) {
        StringBuilder str = null;
        if (getDigit() != null) {
            if (showSign) {
                switch (sign) {
                    case positive -> str = new StringBuilder("+");
                    case negative -> str = new StringBuilder("-");
                    case undefined -> str = new StringBuilder("?");
                }
            }
            for (var c : getDigit()) {
                assert str != null;
                str.append(c);
            }
            return String.valueOf(str);
        }
        return "Num hads not been initialized.";

    }

    public String toString() {
        return toString(true);
    }


    /**
     * Set the digit data and reverse format errors.
     * Zero should always be Sign.positive.
     */
    private void setDigit(byte[] digit) {
        digit = removeLeadingZero(digit);
        if (Arrays.equals(digit, new byte[]{0})) this.sign = Sign.positive;
        this.digit = digit;
    }


    private byte[] getDigit() {
        return digit;
    }

    private Sign getSign() {
        return sign;
    }

    /**
     * Reverse a byte array using for loops.
     * According to mathematics regulations, "add" operation should start from left to right.
     * It's obvious that the length of an array cannot be modified, this method helps streamline calculating procedures.
     *
     * @param array Receive the array to execute.
     * @return Reversed array.
     */
    public static byte[] reverse(byte[] array) {
        byte temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * This private inner static class provides two public static calculating methods:
     * 1.Add: Return a BigInteger object using given two params.
     * 2.Multiply: Return a BigInteger object using given two params.
     */
    private static class Calculate {
        public static BigInteger add(BigInteger num1, BigInteger num2) {
            final var resultLength = Math.max(num1.getDigit().length, num2.getDigit().length) + 1;
            var result = new byte[resultLength];
            var carryOne = 0;
            var sign = Sign.positive;
            num1.digit = Arrays.copyOf(reverse(num1.getDigit()), resultLength);
            num2.digit = Arrays.copyOf(reverse(num2.getDigit()), resultLength);
            for (int i = 0; i < resultLength - 1; i++) {
                result[i] = (byte) ((num1.getDigit()[i] * num1.getSign().getValue() + num2.getDigit()[i] * num2.getSign().getValue() + carryOne) % 10);
                carryOne = (num1.getDigit()[i] * num1.getSign().getValue() + num2.getDigit()[i] * num2.getSign().getValue() + carryOne) / 10;
            }
            result[resultLength - 1] += carryOne;
            num1.setDigit(reverse(num1.getDigit()));
            num2.setDigit(reverse(num2.getDigit()));
            reverse(result);
            if (result[1] <= 0) {
                for (int i = 0; i < resultLength; i++) {
                    result[i] = (byte) Math.abs(result[i]);
                }
                sign = Sign.negative;
            }
            return new BigInteger(sign, result);
        }

        public static BigInteger multiply(BigInteger num1, BigInteger num2) {


            return new BigInteger(Sign.positive, new byte[1]);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            var b1 = new BigInteger(in.next());
            var b2 = new BigInteger(in.next());
            System.out.println(Calculate.add(b1, b2));
        }
    }

}

class SignUndefinedException extends Exception {
    public SignUndefinedException() {
    }

    public SignUndefinedException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "SignUndefinedException";
    }
}
