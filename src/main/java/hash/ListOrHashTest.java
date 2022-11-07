package hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xusj
 * @Date: 2022/1/12 16:27
 * @Description: 用于测试list或hash contains效率 ------结论 hashSet 更快
 */
public class ListOrHashTest {

    public static List<String> publicList() {
        List<String> list = new ArrayList<>();
        //公共字段
        for (int i = 0; i < 10000000; i++) {
            list.add(String.valueOf(i));
        }
        list.add("设备类型编码");
        list.add("通讯方式");
        return list;
    }

    public static List<String> list() {
        List<String> list = new ArrayList<>();
        //公共字段
        list.add("设备类型编码");
        list.add("设备类型名称");
        list.add("设备制造商");
        list.add("通讯方式");
        list.add("位置信息类别");
        list.add("省");
        list.add("省名称");
        list.add("市");
        list.add("市名称");
        list.add("区");
        list.add("区名称");
        list.add("位置信息");
        list.add("地图经度");
        list.add("地图纬度");
        list.add("传输间隔");
        list.add("部门/单位");
        list.add("一级场景编码");
        list.add("二级场景编码");
        list.add("三级场景编码");
        //补充字段
        list.add("城市部件编码");
        return list;
    }

    public static Set<String> hashSet() {
        Set<String> hashSet = new HashSet<String>();
        //公共字段
        hashSet.add("设备类型编码");
        hashSet.add("设备类型名称");
        hashSet.add("设备制造商");
        hashSet.add("通讯方式");
        hashSet.add("位置信息类别");
        hashSet.add("省");
        hashSet.add("省名称");
        hashSet.add("市");
        hashSet.add("市名称");
        hashSet.add("区");
        hashSet.add("区名称");
        hashSet.add("位置信息");
        hashSet.add("地图经度");
        hashSet.add("地图纬度");
        hashSet.add("传输间隔");
        hashSet.add("部门/单位");
        hashSet.add("一级场景编码");
        hashSet.add("二级场景编码");
        hashSet.add("三级场景编码");
        //补充字段
        hashSet.add("城市部件编码");
        return hashSet;
    }

    public static void cultList(List<String> publicList) {
        List<String> list = list();
        for (String str : publicList) {
            if (list.contains(str)) {
                System.out.println(2);
            }
        }
    }

    public static void cultSet(List<String> publicList) {
        Set<String> hashSet = hashSet();
        for (String str : publicList) {
            if (hashSet.contains(str)) {
                System.out.println(2);
            }
        }
    }

    public static void main(String[] args) {
        List<String> publicList = publicList();
        long start = System.currentTimeMillis();
        System.out.println("start: " + start);

        //test
//        cultList(publicList);
        cultSet(publicList);

        long end = System.currentTimeMillis();
        System.out.println("end: " + end);
        long cost = end - start;
        System.out.println("cost: " + cost);
    }

}
