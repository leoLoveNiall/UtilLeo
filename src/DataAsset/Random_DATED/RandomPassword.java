package DataAsset.Random_DATED;

import java.util.stream.IntStream;
@SuppressWarnings("unused")

public class RandomPassword {
    public static String getRandDigitPwd(int length) {
        var arr = new char[length];
        IntStream.range(0, length).forEach(i -> arr[i] = RandomCharacter.getRandNumChar());
        return String.valueOf(arr);
    }

    public static String getRandDigitPwdUsingDict(int length, String dict) {
        var arr = new char[length];
        IntStream.range(0, length).forEach(i -> arr[i] = RandomCharacter.getRandChar(dict));
        return String.valueOf(arr);
    }

    public static String getRandDigitLetterPwd(int length) {
        var arr = new char[length];
        for (int i = 0; i < length; i++) {
            var rand = RandomNumber.getRandomInt(0, 62);
            if (rand >= 37) {
                arr[i] = RandomCharacter.getRandCapLetterChar();
            } else if (rand >= 11) {
                arr[i] = RandomCharacter.getRandLetterChar();
            } else {
                arr[i] = RandomCharacter.getRandNumChar();
            }
        }
        return String.valueOf(arr);
    }

    public static String getRandDigitLetterSignPwd(int length) {
        var arr = new char[length];
        IntStream.range(0, length).forEach(i -> {
            var rand = RandomNumber.getRandomInt(0, 73);
            if (rand >= 48) {
                arr[i] = RandomCharacter.getRandSignChar();
            } else if (rand >= 37) {
                arr[i] = RandomCharacter.getRandCapLetterChar();
            } else if (rand >= 11) {
                arr[i] = RandomCharacter.getRandLetterChar();
            } else {
                arr[i] = RandomCharacter.getRandNumChar();
            }
        });
        return String.valueOf(arr);
    }

    public static String getBankPwd() {
        return getRandDigitPwd(4);
    }

    public static String getScreenPwd() {
        return getRandDigitPwd(6);
    }

    public static String getPwd8() {
        return getRandDigitLetterSignPwd(8);
    }

    public static String getPwd16() {
        return getRandDigitLetterSignPwd(16);
    }

    public static String getPwd32() {
        return getRandDigitLetterSignPwd(32);
    }

    public static void main(String[] args) {
        System.out.println(getPwd32());
    }
}
