package DataAsset.Data;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.Arrays;

/**
 * Since the exact size of boolean is machine dependent(may occupy from 1 bit 4 bytes),
 * this class has provided an embedded class to store 8 boolean into a single variable.
 */

public class ZippedBoolean {
    // Boolean variables are stored as bits using little endian.
    // while the explicit form is big endian
    private byte data = (byte) 0x00;
    
    ZippedBoolean(boolean[] booleans) {
        for (int i = 0; i < 8; i++) {
            setBooleanAt(booleans[i], i);
        }
    }

    ZippedBoolean(String biStr) {
        for (int i = 0; i < 8; i++) {
            //should be verified here
            setBooleanAt(Byte.parseByte(biStr.substring(i, i + 1)) == 1, i);
        }
    }

    public boolean getBooleanAt(int i) {
        //suppose to verify i
        return ((this.data & (0x01 << i)) != 0);
    }


    public void setBooleanAt(boolean b, int i) {
        //suppose to verify i here
        //generate contrary binary code
        if (b) {
            //true
            byte opp = (byte) (0x01 << i);
            this.data |= opp;
        } else {
            //false
            byte opp = (byte) ~(0x01 << i);
            this.data &= opp;
        }
    }

    public boolean[] getBooleanArray() {
        boolean[] b = new boolean[8];
        for (int i = 0; i < 8; i++) {
            b[i] = getBooleanAt(i);
        }
        return b;
    }

    public void outputBinaryString() {
        for (int i = 0; i < 8; i++) {
            System.out.print(getBooleanAt(i) ? 1 : 0);
        }
        System.out.println();
    }


}

class Test1 {
    public static void main(String[] args) {
        ZippedBoolean testZB = new ZippedBoolean("11110000");
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

}