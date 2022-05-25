package DataAsset.Text.TextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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

    /**
     * If target string starts with a digit, this method automatically add given string in the head of it.
     * @param data A list of target strings.
     * @param s Given string that is supposed to be added.
     */
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

    public static void addStringAhead(ArrayList<String> data, String s) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, s + data.get(i));
        }
    }
    public static void deleteString(ArrayList<String> data, String s) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i).replaceAll(s,""));
        }
    }

    public static String composeStringArrayListWithCR(ArrayList<String> data) {
        StringBuilder tar = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            tar.append(data.get(i));
            if (i != data.size() - 1) tar.append("\n");
        }
        return tar.toString();
    }
    public static void BASE64Encode(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, Base64.getEncoder().encodeToString(data.get(i).getBytes()));
        }
    }
    public static String BASE64Encode(String txt) {
        return Base64.getEncoder().encodeToString(txt.getBytes());
    }
    public static void BASE64Decode(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, Arrays.toString(Base64.getDecoder().decode(data.get(i))));
        }
    }

    /**
     * E.g: Transform from: 1. 贫穷的：poor 2. 引起：engender3. 解决：deal with
     * To:
     * 1. 贫穷的：poor
     * 2. 引起：engender
     * 3. 解决：deal with
     */
    public static void addCarriageReturnBeforeAnyDigit(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            char[] str = data.get(i).toCharArray();
            ArrayList<Character> str_al = new ArrayList<>();
            boolean lastOneIsDigit = false;
            str_al.add(str[0]);
            for (int j = 1; j < str.length; j++) {
                lastOneIsDigit = Character.isDigit(str[j - 1]);
                if (Character.isDigit(str[j]) && !lastOneIsDigit) {
                    str_al.add('\n');
                }
                str_al.add(str[j]);
            }
            char[] str_arr = new char[str_al.size()];
            for (int j = 0; j < str_arr.length; j++) {
                str_arr[j] = str_al.get(j);
            }
            data.set(i, new String(str_arr));
        }
    }
}
