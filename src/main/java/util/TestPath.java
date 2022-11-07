package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author: xusj
 * @Date: 2022/9/2 16:59
 * @Description:
 */
public class TestPath {


//    public static void main(String[] args) {
//        try {
//
//            InputStream fileIn = getFileInputStream("D:\\sgedge-1.0-SNAPSHOT.jar");
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(fileIn,
//                            StandardCharsets.UTF_8));
//            String s = "";
//            while ((s = reader.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    static void process(String fileName) {
        try {
            JarFile jarFile = new JarFile(fileName); // 创建JarFile对象
            Enumeration en = jarFile.entries();
            while (en.hasMoreElements()) { // 测试枚举中是否包含更多的元素
                JarEntry entry = (JarEntry) en.nextElement();// 获取集合中的元素
                if (entry.getName().contains("zxkj-ca")){
                    InputStream inputStream = jarFile.getInputStream(entry);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    BufferedReader buffReader = new BufferedReader(reader);
                    String strTmp = "";
                    while((strTmp = buffReader.readLine())!=null){
                        System.out.println(strTmp);
                    }
                    buffReader.close();
                    System.out.println(111);
                }
                String name = entry.getName(); // 获取文件名称
                long size = entry.getSize(); // 获取文件大小
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取Jar文件中的资源
     *
     * @param jarUrl  本地jar文件或网络上(http://host:port/subpath/jarfile.jar)jar文件路径
     * @param resPath 资源文件所在jar中的路径(不能以'/'字符开头)
     * @return 如果资源加载失败, 返回null
     */

    public static InputStream loadResourceFromJarURL(String jarUrl, String resPath) {
        if (!jarUrl.endsWith(".jar")) {
            return null;
        }
        URL url = null;
        if (jarUrl.startsWith("http://")) {
            try {
                url = new URL("jar:" + jarUrl + "!/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            try {
                url = new URL("jar:file:/" + jarUrl + "!/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            JarURLConnection jarURLConnection;
            jarURLConnection = (JarURLConnection) url.openConnection();
            JarFile jarFile = jarURLConnection.getJarFile();
            JarEntry jarEntry = jarFile.getJarEntry(resPath);
            if (jarEntry == null) {
                return null;
            }
            return jarFile.getInputStream(jarEntry);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getFilePath(String fileName) {
        return TestPath.class.getResource(fileName).getPath();
    }

    /**
     * 增加函数，替换getFilePath（）函数，直接获取资源流数据
     */
    public static InputStream getFileInputStream(String fileName) {
        return TestPath.class.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /* 资源文件路径,不能以'/'字符字符开头 */
        process("D:\\sgedge-1.0-SNAPSHOT.jar");
        String resourcePath = "zxkj-ca.txt";
//        /* 读取网络Jar文件 */
//        InputStream in = loadResourceFromJarURL(
//                "D:\\sgedge-1.0-SNAPSHOT.jar", resourcePath);
//        if (in != null) {
//            System.err.println(IOUtils.toString(in));
//            in.close();
//        }
    }

    /**
     * 读取Jar文件中的资源
     *
     * @param jarPath 本地jar文件路径
     * @param resPath 资源文件所在jar中的路径(不能以'/'字符开头)
     * @return 如果资源加载失败, 返回null
     */
    public static InputStream loadResourceFromJarFile(String jarPath, String resPath) {
        if (!jarPath.endsWith(".jar")) {
            return null;
        }
        try {
            JarFile jarFile = new JarFile(jarPath);
            JarEntry jarEntry = jarFile.getJarEntry(resPath);
            if (jarEntry == null) {
                return null;
            }
            return jarFile.getInputStream(jarEntry);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
