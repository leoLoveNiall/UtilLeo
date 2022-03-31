package DataAsset.Text.Asset;

import DataAsset.Text.Formatter;

import java.util.ArrayList;

public class WenetNetworkUsageStatistics {
    public static String[] getTime(ArrayList<String> data) {
        int j = 0;
        String s = null, e = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).contains("时间")) {
                s = data.get(i);
                e = data.get(i);
                j = i + 1;
                break;
            }
        }
        for (int i = j; i < data.size(); i++) {
            if (data.get(i).contains("时间")) {
                assert s != null;
                if (data.get(i).compareTo(s) < 0) {
                    s = data.get(i);
                }
                assert e != null;
                if (data.get(i).compareTo(e) > 0) {
                    e = data.get(i);
                }
            }
        }
        return new String[]{s, e};
    }

    public static double[] calculate(ArrayList<String> data) {
        double sum = 0;
        double max = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).contains("流量")) {
                double value = Double.parseDouble(data.get(i).replaceAll("[^0-9\\.]", ""));
                sum += value;
                max = Math.max(max, value);
            } else {
                data.remove(i);
            }
        }
        return new double[]{sum, max};
    }

    public static void main(String[] args) {
        var data = Formatter.universalConsoleScanner();
        var time = getTime(data);
        System.out.println("起始" + time[0]);
        System.out.println("截止" + time[1]);
        double[] result = calculate(data);
        double res_MB = result[0];
        System.out.printf("Used:%.2f MB\n", res_MB);
        System.out.printf("Used:%.2f GB\n", res_MB / 1024);
        System.out.printf("Used:%.2f TB\n\n", res_MB / 1024 / 1024);
        double max_MB = result[1];
        System.out.printf("Max Used:%.2f MB\n", max_MB);
        System.out.printf("Max Used:%.2f GB\n", max_MB / 1024);
        System.out.printf("Max Used:%.2f TB\n", max_MB / 1024 / 1024);
    }
}
