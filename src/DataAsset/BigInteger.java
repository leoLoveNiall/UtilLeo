package DataAsset;

import java.util.*;


enum Sign {positive, negative, undefined}

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
                default -> new SignUndefinedException().printStackTrace();
            }
            for (byte i = 0; i < input.length() - 1; i++) {
                byteArr[i] = Byte.parseByte(String.valueOf(input.charAt(i + 1)));
            }
            byteArr = removeLeadingZero(byteArr);
            setDigit(byteArr);
        } else {
            System.out.println("Format error.");
        }
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
     *
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
        return "Num has not been initialized.";

    }

    public String toString() {
        return toString(true);
    }

    /**
     * Set the digit data and reverse format errors.
     * Zero should always be Sign.positive.
     *
     */
    private void setDigit(byte[] digit) {
        if (Arrays.equals(digit, new byte[]{0})) this.sign = Sign.positive;
        this.digit = digit;
    }

    private byte[] getDigit() {
        return digit;
    }

    private Sign getSign() {
        return sign;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            var bigInteger = new BigInteger(in.next());
            System.out.println(bigInteger);
        }
    }
}

class SignUndefinedException extends Exception {
    public SignUndefinedException() {
    }

    @Override
    public String toString() {
        return "SignUndefinedException";
    }
}