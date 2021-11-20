package DataAsset.Text.MarkdownFormatter;

import DataAsset.Text.Formatter;

import java.util.Scanner;

public class Main {
    public static void transformToH1Title() {
        var data = Formatter.universalConsoleScanner();
        Formatter.addStringBeforeSerialNumber(data, "# ");
        Formatter.universalPrint(data);
    }

    public static void transformToH2Title() {
        var data = Formatter.universalConsoleScanner();
        Formatter.addStringBeforeSerialNumber(data, "## ");
        Formatter.universalPrint(data);
    }

    public static void transformToH3Title() {
        var data = Formatter.universalConsoleScanner();
        Formatter.addStringBeforeSerialNumber(data, "### ");
        Formatter.universalPrint(data);
    }

    public static void main(String[] args) {
        var data = Formatter.universalConsoleScanner();
        Formatter.addCarriageReturnBeforeAnyDigit(data);
        Formatter.universalPrint(data);
    }
}
