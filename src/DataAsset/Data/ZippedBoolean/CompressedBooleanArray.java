package DataAsset.Data.ZippedBoolean;

import java.util.Random;
import java.util.Scanner;

/**
 * Finally I worked this out...
 *
 * Re:
 *
 * Since the exact size of boolean is machine dependent(may occupy space from 1 bit to 4 bytes),
 * this class has provided an embedded class to store META_LEN boolean into a single variable.
 *
 * On ny MacBook working with macOS 12 and openjdk 16, the test result is:
 *
 *   | Original | Compressed |
 *   | 420 MB   | 69.7 MB    |
 *   | 59.7 MB  | 25.6 MB    |
 *
 * Kinda trilling!
 */
public class CompressedBooleanArray {
    private long[] data;
    private final int length;
    private final int innerLen;
    private final static int META_LEN = 64;

    CompressedBooleanArray(int length) {
        this.length = length;
        this.innerLen = length % META_LEN == 0 ? length / META_LEN : length / META_LEN + 1;
        this.data = new long[innerLen];
    }

    CompressedBooleanArray(String biStr) {
        String pureBiStr = biStr.replaceAll(" ", "");
        this.length = pureBiStr.length();
        this.innerLen = length % META_LEN == 0 ? length / META_LEN : length / META_LEN + 1;
        this.data = new long[innerLen];
        for (int i = 0; i < length; i++) {
            //should be verified here
            setBooleanAt(Byte.parseByte(pureBiStr.substring(i, i + 1)) == 1, i);
        }
    }

    private long getInnerLongValue(int index) {
        return data[index / META_LEN];
    }

    private void setInnerLongValue(long value, int index) {
        data[index / META_LEN] = value;
    }

    public boolean getBooleanAt(int index) {
        return ((getInnerLongValue(index) & (0x01L << index % META_LEN)) != 0);
    }

    public void setBooleanAt(boolean b, int index) {
        if (b) {
            //true
            setInnerLongValue(getInnerLongValue(index) | (0x01L << (index % META_LEN)), index);
        } else {
            //false
            setInnerLongValue(getInnerLongValue(index) & ~(0x01L << index % META_LEN), index);
        }
    }

    protected void outputBinaryString() {
        for (int i = 0; i < length; i++) {
            System.out.print(getBooleanAt(i) ? 1 : 0);
        }
        System.out.println();
    }
}

class Test1 {
    public static void main(String[] args) {
        CompressedBooleanArray c = new CompressedBooleanArray("00000000 00000000 00000000 00000000");
        c.outputBinaryString();
        c.setBooleanAt(true, 2);
        c.outputBinaryString();
        c.setBooleanAt(true, 5);
        c.outputBinaryString();
    }
}

class Test2 {
    public static void main(String[] args) {
        int range = 64000 * 6400;
        Random r = new Random();
        //method1
        CompressedBooleanArray c = new CompressedBooleanArray(range);
        for (int i = 0; i < range; i++) {
            c.setBooleanAt(r.nextBoolean(), i);
        }
        //method2
        boolean[] b = new boolean[range];
        for (int i = 0; i < range; i++) {
            b[i] = r.nextBoolean();
        }
        //pause
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
    }
}