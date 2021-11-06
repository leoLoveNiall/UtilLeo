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


}
