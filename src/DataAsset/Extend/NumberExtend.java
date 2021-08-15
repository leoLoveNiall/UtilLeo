package DataAsset.Extend;


public class NumberExtend {
    public static int getDigit(int num) {
        num = Math.abs(num);
        if (num == 0) return 1;
        int digit = 0;
        for (; num > 0; digit++) {
            num /= 10;
        }
        return digit;
    }
}
