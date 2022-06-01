package DataAsset.Data.Archive;

import DataAsset.Data.BigInteger;
import DataAsset.Data.Sign;
import DataAsset.Extended.ExtendedString;

import java.util.Scanner;

//Not finished yet

public class BigRealNumber {
    private Sign sign;
    private BigInteger integer;
    private BigInteger decimal;
    private boolean successfullyInitialized = false;

    BigRealNumber(String input) {
        String[] split;
        try {
            input = filteredScan(input);
            split = input.split("\\.");
            switch (input.charAt(0)) {
                case '+' -> this.sign = Sign.positive;
                case '-' -> this.sign = Sign.negative;
            }
            integer = new BigInteger(split[0]);
            decimal = new BigInteger(split[1]);
            integer.setSign(Sign.unsigned);
            integer.setLockSign(true);
            decimal.setSign(Sign.unsigned);
            decimal.setLockSign(true);
            successfullyInitialized = true;
        } catch (Exception e) {
            System.out.println("Format error.");
        }
    }

    /**
     * Converts this BigInteger object to normal string.
     * Free to decide sign visibility.
     */
    public String toString(boolean showSign) {
        String s = null;
        if (successfullyInitialized) {
            if (showSign) {
                switch (sign) {
                    case positive -> s = "+";
                    case negative -> s = "-";
                    case unsigned -> s = "";
                }
            } else s = "";
            s += integer.toString(false) + "." + decimal.toString(false);
            return s;
        } else return "Num has not been initialized.";
    }

    @Override
    public String toString() {
        return toString(true);
    }

    /**
     * Automatically add "+" before an unsigned input
     *
     * @return "+(\str)" or "(\str)"
     */
    private static String filteredScan(String str) {
        if (Character.isDigit(str.charAt(0))) str = "+" + str;
        if (!ExtendedString.hasChar(str, '.')) str += ".0";
        return str;
    }

    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var r1 = new BigRealNumber(in.next());
        System.out.println(r1);
    }
}
