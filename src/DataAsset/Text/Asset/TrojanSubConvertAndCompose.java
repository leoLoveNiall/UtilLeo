package DataAsset.Text.Asset;

import DataAsset.Text.TextFormatter.Formatter;

import java.util.ArrayList;

public class TrojanSubConvertAndCompose {

    public static void main(String[] args) {
        String prefix = "trojan://";
        ArrayList<String> subList = Formatter.universalConsoleScanner();
//        Formatter.deleteString(subList,prefix);
//        Formatter.BASE64Encode(subList);
//        Formatter.addStringAhead(subList,prefix);
        String tar = Formatter.composeStringArrayListWithCR(subList);
        System.out.println(Formatter.BASE64Encode(tar));
    }
}
