package DataAsset;

import java.util.*;


enum Sign {positive, negative, undefined}

public class BigInteger {
    Sign sign = Sign.undefined;
    byte[] data = null;

    BigInteger(String input) {
        if (isLegit(input)) {
            byte[] byteArr = new byte[input.length() - 1];
            switch (input.charAt(0)) {
                case '+':
                    this.sign = Sign.positive;
                    break;
                case '-':
                    this.sign = Sign.negative;
                    break;
                default:
                    new SignUndefinedException().printStackTrace();
                    break;
            }
            for (byte i = 0; i < input.length() - 1; i++) {
                byteArr[i] = Byte.parseByte(String.valueOf(input.charAt(i + 1)));
            }
            this.data = byteArr;
        } else {
            System.out.println("Format error.");
        }
    }

    boolean isLegit(String input) {
        if (input.length() <= 1) return false;
        if (!(input.charAt(0) == '-' || input.charAt(0) == '+')) return false;
        for (var i = 1; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }

    public String toString(boolean showSign) {
        StringBuilder str = null;
        if (data != null) {
            if (showSign) {
                switch (sign) {
                    case positive -> str = new StringBuilder("+");
                    case negative -> str = new StringBuilder("-");
                    case undefined -> str = new StringBuilder("?");
                }
            }
            for (var c : data) {
                assert str != null;
                str.append(c);
            }
            return String.valueOf(str);
        }
        return "Num has not been initialized.";

    }

    @Override
    public String toString() {
        return toString(true);
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