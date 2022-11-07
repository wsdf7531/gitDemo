package util;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author: xusj
 * @Date: 2022/4/11 15:51
 * @Description: 文件处理（涉及到图片压缩）
 */
public class FileUtils {

    static final String filePath = "/obj_train_data";
    static final String validPath = "/obj_train_valid_data";
    static final String textPath = "/labels";
    static final String imagesPath = "/images";
    static final String invalidPath = "/obj_train_invalid_data";
    static final String txt = ".txt";
    static final String png = ".PNG";
//
//    static final String filePath = "\\obj_train_data";
//    static final String validPath = "\\obj_train_valid_data";
//    static final String textPath = "\\labels";
//    static final String imagesPath = "\\images";
//    static final String invalidPath = "\\obj_train_invalid_data";

    public static void main(String[] args) {
        try {
            Long start = System.currentTimeMillis();
            System.out.println("process start: " + start);
            String basePath = getBasePath();
            String path = basePath + filePath;
//            String basePath = "D:\\test";
//            String path = basePath + filePath;
            File[] files = new File(path).listFiles();
            for (File file : files) {
                String fileName = file.getName();
                boolean isCurrentFile = false;
                //文本文件处理
                if (fileName.contains(txt)) {
                    //考虑到编码格式
                    String encoding = "GBK";
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);

                    while (StringUtils.isNotBlank(bufferedReader.readLine())) {
                        isCurrentFile = true;
                    }
                    //文件转移
                    processFile(isCurrentFile, file, fileName, basePath);
                    read.close();
                }
            }
            Long end = System.currentTimeMillis();
            System.out.println("process finish: " + end);
            System.out.println("process cost: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void processFile(Boolean isCurrent, File file, String fileName, String basePath) throws IOException {
        //文本文件处理
        String pixName = basePath;
        //图片文件处理
        String photoName = fileName.replace(txt, png);
        File photo = new File(basePath + filePath + "/" + photoName);
        if (isCurrent) {
            //txt
            fileCreate(file, pixName + validPath + textPath + "/" + fileName);
            //photo
            if (photo.exists()) {
                doWithPhoto(photo.getPath(), pixName + validPath + imagesPath + "/" + photoName);
            }
        } else {
            //txt
            fileCreate(file, pixName + invalidPath + "/" + fileName);
            if (photo.exists()) {
                doWithPhoto(photo.getPath(), pixName + invalidPath + "/" + photoName);
            }
        }
    }

    public static String getBasePath() throws IOException {
        File directory = new File(".");
        return directory.getCanonicalPath();
    }

    public static void fileCreate(File file, String pathName) throws IOException {
        File newFile = new File(pathName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        Files.copy(file, newFile);
    }

    /**
     * 对图片进行原比例无损压缩
     *
     * @param path
     */
    private static void doWithPhoto(String path, String outPath) {
        outPath = outPath.replace("PNG","png");
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        BufferedImage image = null;
        FileOutputStream os = null;
        try {
            image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage bfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            //输出
            File newFile = new File(outPath);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            if (!newFile.exists()){
                newFile.createNewFile();
            }
            os = new FileOutputStream(outPath);
                ImageIO.write(bfImage, "JPEG", os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
