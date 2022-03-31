package DataAsset.Text.Asset;

import DataAsset.Text.Formatter;

import java.util.ArrayList;

public class JSONFormatter {
    /**
     * eg
     * 1.       Pragma : no-cache
     * 2.       "Pragma": "no-cache"
     * @param data
     */
    public static void addQuotationMarkByColon(ArrayList<String> data) {
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Character> s = new ArrayList<Character>();
            for (char c : data.get(i).toCharArray()) {
                s.add(c);
            }
            s.add(0, '"');
            boolean found = false;
            for (int j = 1; j < s.size(); j++) {
                if (!found && s.get(j) == ':') {
                    s.add(j, '"');
                    j++;
                    //remove possible blanks
                    while (true){
                        if (s.get(j) == ' '){
                            j++;
                        }else break;
                    }
                    s.add(j + 2, '"');

                    found = true;
                    break;
                }
            }
            s.add('"');
            s.add(',');
            char[] res = new char[s.size()];
            for (int j = 0; j < s.size(); j++) {
                res[j] = s.get(j);
            }
            data.set(i,new String(res));
        }
    }

    public static void main(String[] args) {
        var data = Formatter.universalConsoleScanner();
        addQuotationMarkByColon(data);
        Formatter.universalPrint(data);
    }
}
