package FileAsset;

import java.io.*;

public class ModifyCreatedDateByFileName {
    public static void main(String[] args) throws IOException {
        String abp = "/Users/kongweirui/Downloads/";
        String p1 = "陳昊森 Ed Chen ";
        String p2 = ".jpeg";
        int bid = 1;
        int eid = 389;
        for (int i = bid; i <= eid; i++) {
            String fabp = abp + p1 + i + p2;
            String ofabp = abp + "[OUT]" + p1 + i + p2;
            File originalFile = new File(fabp);//指定要读取的图片
            File result = new File(ofabp);//要写入的图片
            if (result.exists()) {//校验该文件是否已存在
                result.delete();//删除对应的文件，从磁盘中删除
                result = new File(ofabp);//只是创建了一个File对象，并没有在磁盘下创建文件
            }
            if (!result.exists()) {//如果文件不存在
                result.createNewFile();//会在磁盘下创建文件，但此时大小为0K
            }
            FileInputStream in = new FileInputStream(originalFile);
            FileOutputStream out = new FileOutputStream(result);// 指定要写入的图片
            int n = 0;// 每次读取的字节长度
            byte[] bb = new byte[1024];// 存储每次读取的内容
            while ((n = in.read(bb)) != -1) {
                out.write(bb, 0, n);// 将读取的内容，写入到输出流当中
            }
            out.close();// 关闭输入输出流
            in.close();
        }

    }
}
