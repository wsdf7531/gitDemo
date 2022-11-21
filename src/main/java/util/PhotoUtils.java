package util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @Author: xusj
 * @Date: 2022/11/7 17:11
 * @Description:
 */
public class PhotoUtils {

    public static String convertFileToBase64(String imgPath) {
        byte[] data = null;
        // 读取文件字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            System.out.println("文件大小（字节）="+in.available());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组进行Base64编码，得到Base64编码的字符串
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(data);
        return base64Str;
    }

    public static void main(String[] args) {
//        String s = convertFileToBase64("e://photo/xu.jpg");
//        System.out.println(s);

        long utc = LocalDateTime.now(ZoneId.of("UTC")).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(utc);

    }
}
