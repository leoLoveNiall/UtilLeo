package DataAsset.Text.Asset;

import DataAsset.Text.Formatter;
import java.util.HashMap;
public class TencentDocContributionRateCalculator {
    public static void main(String[] args) {
        var metaData = Formatter.universalConsoleScanner();
        //remove irrelevant string
        var idx = 0;
        while (idx != metaData.size()) {
            if (metaData.get(idx).startsWith("2022")
                    || metaData.get(idx).startsWith("第")
                    || metaData.get(idx).startsWith("等人")//省略的部分需要人工补充
            ) {
                metaData.remove(idx);
            } else {
                idx++;
            }
        }
        //print all effective records
        metaData.forEach(System.out::println);
        var dataCounter = new HashMap<String, Integer>();
        for (String item : metaData) {
            if (dataCounter.containsKey(item)) {
                dataCounter.put(item, dataCounter.get(item) + 1);
            } else {
                dataCounter.put(item, 1);
            }
        }
        var total = 0;
        for (String key : dataCounter.keySet()) {
            total += dataCounter.get(key);
        }
        System.out.println("统计结果：");
        for (String key : dataCounter.keySet()) {
            System.out.println(key + ": 总计" + dataCounter.get(key) + ", 贡献度:" + (int)(dataCounter.get(key) * 100.0 / total));
        }
    }

}
