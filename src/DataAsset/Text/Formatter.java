package DataAsset.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class Formatter {
    public static Scanner sc = new Scanner(System.in);

    public static void universalPrint(ArrayList<String> al) {
        for (String s : al) {
            System.out.println(s);
        }
    }

    public static ArrayList<String> universalConsoleScanner() {
        return universalConsoleScanner("EOF");
    }

    public static ArrayList<String> universalConsoleScanner(String endWith) {
        var data = new ArrayList<String>();
        var tmp = sc.nextLine();
        while (!tmp.equals(endWith)) {
            data.add(tmp);
            tmp = sc.nextLine();
        }
        return data;
    }

    public static void addStringBeforeSerialNumber(ArrayList<String> data, String s) {
        for (int i = 0; i < data.size(); i++) {
            try {
                if (Character.isDigit(data.get(i).charAt(0))) {
                    data.set(i, s + data.get(i));
                }
            } catch (Exception ignored) {

            }
        }
    }

    /**
     * E.g: Transform from: 1. 贫穷的：poor 2. 引起：engender3. 解决：deal with
     * To:               1. 贫穷的：poor
     * 2. 引起：engender
     * 3. 解决：deal with
     */
    public static void addCarriageReturnBeforeAnyDigit(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            char[] str = data.get(i).toCharArray();
            ArrayList<Character> str_al = new ArrayList<>();
            boolean lastOneIsDigit = false;
            for (char c : str) {
                if (Character.isDigit(c) && !lastOneIsDigit) {
                    str_al.add('\n');
                    lastOneIsDigit = true;
                } else {
                    lastOneIsDigit = false;
                }
                str_al.add(c);
            }
            char[] str_arr = new char[str_al.size()];
            for (int j = 0; j < str_arr.length; j++) {
                str_arr[j] = str_al.get(j);
            }
            data.set(i, new String(str_arr));
        }
    }
}
