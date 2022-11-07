package util;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.IOException;

/**
 * @Author: xusj
 * @Date: 2022/8/24 14:38
 * @Description:
 */
public class FileZipUtil {

    public static void main(String[] args) {
        try {

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
// Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
            zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

            File fileToAdd = new File("D:\\iot-upload-test.py");

            ZipFile zipFile = new ZipFile("D:\\filename.zip", "password".toCharArray());
            zipFile.addFile(fileToAdd, zipParameters);

//            //文件压缩包
//            new ZipFile("D:\\filename.zip").addFile("D:\\iot-upload-test.py");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//
//    /**
//     * 压缩
//     *
//     * @param srcFile 源目录
//     * @param dest    要压缩的目录
//     * @param passwd  密码 不是必填
//     * @throws ZipException 异常
//     */
//    public static void zip(String srcFile, String dest, String passwd) throws ZipException {
//        File srcfile = new File(srcFile);
//
//        //创建目标文件
//        String destname = buildDestFileName(srcfile, dest);
//        ZipParameters par = new ZipParameters();
//        par.setCompressionMethod(CompressionMethod.DEFLATE);
//        par.setCompressionLevel(CompressionLevel.NORMAL);
//
//        if (passwd != null) {
//            par.setEncryptFiles(true);
//            par.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
//            par.s(passwd.toCharArray());
//        }
//
//        ZipFile zipfile = new ZipFile(destname);
//        if (srcfile.isDirectory()) {
//            zipfile.addFolder(srcfile, par);
//        } else {
//            zipfile.addFile(srcfile, par);
//        }
//    }
//
//    /**
//     * 解压
//     *
//     * @param zipfile 压缩包文件
//     * @param dest    目标文件
//     * @param passwd  密码
//     * @throws ZipException 抛出异常
//     */
//    public static void unZip(String zipfile, String dest, String passwd) throws ZipException {
//        ZipFile zfile = new ZipFile(zipfile);
//        zfile.setFileNameCharset("UTF-8");//在GBK系统中需要设置
//        if (!zfile.isValidZipFile()) {
//            throw new ZipException("压缩文件不合法，可能已经损坏！");
//        }
//
//        File file = new File(dest);
//        if (file.isDirectory() && !file.exists()) {
//            file.mkdirs();
//        }
//
//        if (zfile.isEncrypted()) {
//            zfile.setPassword(passwd.toCharArray());
//        }
//        zfile.extractAll(dest);
//    }
//
//    public static String buildDestFileName(File srcfile, String dest) {
//        if (dest == null) {
//            if (srcfile.isDirectory()) {
//                dest = srcfile.getParent() + File.separator + srcfile.getName() + ".zip";
//            } else {
//                String filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
//                dest = srcfile.getParent() + File.separator + filename + ".zip";
//            }
//        } else {
//            createPath(dest);//路径的创建
//            if (dest.endsWith(File.separator)) {
//                String filename = "";
//                if (srcfile.isDirectory()) {
//                    filename = srcfile.getName();
//                } else {
//                    filename = srcfile.getName().substring(0, srcfile.getName().lastIndexOf("."));
//                }
//                dest += filename + ".zip";
//            }
//        }
//        return dest;
//    }
//
//    private static void createPath(String dest) {
//        File destDir = null;
//        if (dest.endsWith(File.separator)) {
//            destDir = new File(dest);//给出的是路径时
//        } else {
//            destDir = new File(dest.substring(0, dest.lastIndexOf(File.separator)));
//        }
//
//        if (!destDir.exists()) {
//            destDir.mkdirs();
//        }
//    }
}
