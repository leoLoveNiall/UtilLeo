package DataAsset.Extend;

public class StringExtend {
    public static boolean hasChar(String str, char c) {
        for (var cc : str.toCharArray()) {
            if (c == cc) return true;
        }
        return false;
    }
    public static String removeNewLine(String str){
        return str.replaceAll("\n","");
    }
}
