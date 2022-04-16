package DataAsset.Data;

import DataAsset.Extended.ExtendedArray;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class is designed to provide a way of large integer operation.
 * The maximum number of digits of a BigInteger is limited by int.
 * The existing form of data is byte[].
 * Provides two calculation methods: addition and multiplication.
 * BigInteger has strong types(positive, negative and undefined).
 * 0 is a positive integer by default and will be corrected during minus calculation.
 */
public class BigInteger {
    private Sign sign = Sign.unsigned;
    private byte[] digit = null;
    private boolean lockSign = false;
    private boolean successfullyInitialized = false;

    /**
     * Generates a BigInteger object using an input string.
     *
     * @param input The input string should be like: ±1234,±001234, ±00, ±0.
     *              Rest of forms will not be allowed and causes an exception subsequently.
     */
    BigInteger(String input) {
        input = filterInput(input);
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
            successfullyInitialized = true;
        } else {
            System.out.println("Format error.");
        }
    }

    public BigInteger(Sign sign, byte[] digit) {
        this.sign = sign;
        setDigit(digit);
        successfullyInitialized = true;
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
        return IntStream.range(1, input.length()).allMatch(i -> Character.isDigit(input.charAt(i)));
    }

    /**
     * Converts this BigInteger object to normal string.
     * Free to decide sign visibility.
     */
    public String toString(boolean showSign) {
        StringBuilder str = new StringBuilder();
        if (getDigit() != null && successfullyInitialized) {
            if (showSign) {
                switch (sign) {
                    case positive -> str = new StringBuilder("+");
                    case negative -> str = new StringBuilder("-");
                    case unsigned -> str = new StringBuilder("u");
                }
            }
            for (var c : getDigit()) {
                str.append(c);
            }
            return String.valueOf(str);
        } else return "Num has not been initialized.";

    }

    public String toString() {
        return toString(true);
    }

    /**
     * Return absolute scientific notation as string
     */
    public String toAbsScientificNotationString() {
        return String.valueOf(getDigit()[0]) + "." + String.valueOf(getDigit()[1]) + "e" + (getDigit().length - 1);
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

    /**
     * Sign lock identifier.
     */
    public void setLockSign(boolean lockSign) {
        this.lockSign = lockSign;
    }

    public void setSign(Sign sign) {
        if (lockSign) {
            //throw  new SignUneditableException();
        } else {
            this.sign = sign;
        }
    }

    private int getDigitLength() {
        return this.getDigit().length;
    }

    private byte[] getDigit() {
        return this.digit;
    }

    private Sign getSign() {
        return this.sign;
    }

    /**
     * Reverse a byte array using for loops.
     * According to mathematics regulations, "add" operation should start from left to right.
     * It's obvious that the length of an array cannot be modified, this method helps streamline calculating procedures.
     *
     * @param array Receive the array to execute.
     * @return Reversed array.
     */
    private static byte[] reverse(byte[] array) {
        return ExtendedArray.reverse(array);

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
            /*
                If setter is used here, the zero filter automatically cancels normal useful zeros.
                Use direct assignment to avoid digit missing bug(s).

                According to mathematics regulations, "add" operation should start from left to right.
                Reverse digit arrays to reduce complexity.
            */
            num1.digit = Arrays.copyOf(reverse(num1.getDigit()), resultLength);
            num2.digit = Arrays.copyOf(reverse(num2.getDigit()), resultLength);
            for (int i = 0; i < resultLength - 1; i++) {
                //Current digit
                result[i] = (byte) ((num1.getDigit()[i] * num1.getSign().getValue() + num2.getDigit()[i] * num2.getSign().getValue() + carryOne) % 10);
                //If next digit should carry 1
                carryOne = (num1.getDigit()[i] * num1.getSign().getValue() + num2.getDigit()[i] * num2.getSign().getValue() + carryOne) / 10;
            }
            result[resultLength - 1] += carryOne;
            //Roll back to normal order
            num1.setDigit(reverse(num1.getDigit()));
            num2.setDigit(reverse(num2.getDigit()));
            reverse(result);
            //Correct signs
            if (result[1] < 0) {
                IntStream.range(0, resultLength).forEach(i -> result[i] = (byte) Math.abs(result[i]));
                sign = Sign.negative;
            }
            return new BigInteger(sign, result);
        }

        public static BigInteger multiply(BigInteger num1, BigInteger num2) {
            /*
                Multiply every digit one after another.
                As:
                      1 2 3
                    x   1 2
                    -------
                  =   2 4 6    (1)
                    1 2 3      (2)
                    -------
                re: 1 4 7 6

                In effect, num1BasedProductArr below stores every single half-way lines(as showed).

             */
            var num1BasedProductArr = new BigInteger[num1.getDigitLength()];
            /*
                If setter is used here, the zero filter automatically cancels normal useful zeros.
                Use direct assignment to avoid digit missing bug(s).

                According to mathematics regulations, "multiply" operation should start from left to right.
                Reverse digit arrays to reduce complexity.
            */
            var num1ReversedArr = reverse(num1.getDigit());
            var num2ReversedArr = reverse(num2.getDigit());
            for (int i = 0; i < num1BasedProductArr.length; i++) {
                var newBigInteger = new BigInteger(Sign.unsigned, new byte[0]);
                newBigInteger.digit = new byte[num2.getDigitLength() * num1.getDigitLength() + 1];
                //If next digit should carry more
                byte carry = 0;
                //Base stands for the digit that is going to multiply with
                byte base = num1ReversedArr[i];
                for (int cnt = 0; cnt < num2.getDigitLength(); cnt++) {
                    newBigInteger.getDigit()[cnt + i] = (byte) ((base * num2ReversedArr[cnt] + carry) % 10);
                    carry = (byte) ((base * num2ReversedArr[cnt] + carry) / 10);
                }
                newBigInteger.setDigit(reverse(newBigInteger.getDigit()));
                num1BasedProductArr[i] = newBigInteger;
            }
            //Roll back to normal order
            num1.setDigit(reverse(num1.getDigit()));
            num2.setDigit(reverse(num2.getDigit()));
            var sumBigInteger = new BigInteger(Sign.positive, new byte[]{0});
            for (var b : num1BasedProductArr) {
                sumBigInteger.setDigit(add(sumBigInteger, b).getDigit());
            }
            //Correct signs
            if (num1.getSign() == num2.getSign()) sumBigInteger.setSign(Sign.positive);
            else sumBigInteger.setSign(Sign.negative);

            return sumBigInteger;
        }

        /**
         * @param b give a biginteger to calculate
         * @param p limit power under int.max_value
         * @return answer
         */
        public static BigInteger powerByInt(BigInteger b, int p) {

            if (p < 0) {
                var res = new BigInteger("0");
                res.successfullyInitialized = false;
                return res;
            }
            if (p == 0) return new BigInteger("0");
            var res = new BigInteger(b.toString());//水平有限，暂不使用深拷贝功能
            for (int i = 0; i < p - 1; i++) {
                res = multiply(res, b);
            }
            return res;
        }
    }

    /**
     * Automatically add "+" before an unsigned input
     *
     * @return "+(\str)" or "(\str)"
     */
    private static String filterInput(String str) {
        if (Character.isDigit(str.charAt(0))) return "+" + str;
        else return str;
    }


    public static void main(String[] args) {
        var in = "123456";
        for (int i = 500; i < 5000; i+=10) {
            long b = System.currentTimeMillis();
            var bi = Calculate.powerByInt(new BigInteger(in), i);
            System.out.println(bi.toString());
            System.out.println("计算" + in + "^" + i + ", 耗时" +(System.currentTimeMillis()-b) + "ms,结果共计" + bi.getDigitLength() + "位");
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

class SignUneditableException extends Exception {
    public SignUneditableException() {
    }

    public SignUneditableException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "SignUndefinedException";
    }
}

//待解决：String不可以存过长的数据，需要改进
//To be continued: Fix string_out_of_bounds bug.