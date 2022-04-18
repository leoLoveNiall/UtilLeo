package DataAsset.Data.ZippedBoolean;

import java.util.Arrays;

/**
 * Since the exact size of boolean is machine dependent(may occupy space from 1 bit to 4 bytes),
 * this class has provided an embedded class to store META_LEN boolean into a single variable.
 */

public class ZippedBoolean {
    // Boolean variables are stored as bits using little endian.
    // while the explicit form is big endian
    private long data = 0x0;
    private static final int META_LEN = 64;

    ZippedBoolean(boolean[] booleans) {
        for (int i = 0; i < META_LEN; i++) {
            setBooleanAt(booleans[i], i);
        }
    }

    ZippedBoolean(String biStr) {
        String pureBiStr = biStr.replaceAll(" ", "");
        for (int i = 0; i < META_LEN; i++) {
            //should be verified here
            setBooleanAt(Byte.parseByte(pureBiStr.substring(i, i + 1)) == 1, i);
        }
    }

    protected boolean getBooleanAt(int i) {
        //suppose to verify i
        return ((this.data & (0x01L << i)) != 0);
    }

    protected int getBitAt(int i) {
        //suppose to verify i
        return (getBooleanAt(i) ? 1 : 0);
    }

    protected void setBooleanAt(boolean b, int i) {
        //suppose to verify i here
        //generate contrary binary code
        if (b) {
            //true
            byte opp = (byte) (0x01L << i);
            this.data |= opp;
        } else {
            //false
            byte opp = (byte) ~(0x01L << i);
            this.data &= opp;
        }
    }

    protected boolean[] getBooleanArray() {
        boolean[] b = new boolean[8];
        for (int i = 0; i < META_LEN; i++) {
            b[i] = getBooleanAt(i);
        }
        return b;
    }

    protected void outputBinaryString() {
        for (int i = 0; i < META_LEN; i++) {
            System.out.print(getBitAt(i));
        }
        System.out.println();
    }


}

class Test1 {
    public static void main(String[] args) {
        ZippedBoolean testZB = new ZippedBoolean("00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000");
        testZB.outputBinaryString();
        //modify
        testZB.setBooleanAt(true, 5);
        testZB.outputBinaryString();
        //get
        System.out.println(testZB.getBooleanAt(0));
        System.out.println(testZB.getBooleanAt(4));
        System.out.println(testZB.getBooleanAt(5));
        System.out.println(Arrays.toString(testZB.getBooleanArray()));
    }
}

class Test2 {
    //what's the exact range of a byte on my mac?
    public static void main(String[] args) {
        byte b = 1;
        while (b + 1 > 0) {
            b++;
            System.out.println(b);
        }
        System.out.println(b);
    }
}