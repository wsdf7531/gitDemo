package util;

import java.io.*;
import java.util.Base64;

/**
 * @Desc: Base64工具类
 * @Author: Lian
 * @Time: 2021/6/8 11:13
 */
public class Base64Utils {

    /**
     * 图片转化成base64字符串
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = Base64.getEncoder().encodeToString(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     * @param imgData 图片编码
     * @param imgFilePath 存放到本地路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = Base64.getDecoder().decode(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }

    public static void main(String[] args) {
//        String a = "E:\\Project Data\\数字安监系统(国网)\\self\\face_images\\7";
//        String a = "/usr/local/sgedge/data/person_image/7.jpg";
        String a = "e://photo/xu.jpg";
        String s = GetImageStr(a);

        String newPath = "e://photo/xu1.jpg";
        try {
            GenerateImage(s, newPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(s);
    }

}
