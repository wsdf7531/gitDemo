import util.BigDecimalUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2018/9/4
 * @Description:
 */
public class Main {


    public List<Long[]> getListIntArray(Long[] arr, int size) {
        List<Long[]> arrayList = new ArrayList<>();
        int length = arr.length;
        int amount = length / size;
        for (int i = 0; i < amount; i++) {
            Long[] arr0 = new Long[size];
            for (int j = 0; j < size; j++) {
                arr0[j] = arr[j + i * size];
            }
            arrayList.add(arr0);
        }
        if (length % size != 0) {
            int lastLength = length % size;
            Long[] lastArr = new Long[lastLength];
            int j = lastLength;
            for (int i = length; i > length - (length % size); i--) {
                lastArr[j - 1] = arr[i - 1];
                j--;
            }
            arrayList.add(lastArr);
        }
        return arrayList;
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public interface Runnable {
        void run();
    }

    public interface Callable<V> {
        V call();
    }

    public static void process(Runnable r) {
        r.run();
    }


    private static void decimalPointGoUpOne(List<Double> result, int scale) throws IllegalAccessException {
        double sumResult = 0;
        for (int i = 0; i < result.size(); i++) {
            sumResult = BigDecimalUtil.add(sumResult, result.get(i));
        }
        if (sumResult != 100) { //通过将小数部分递减的顺序加1来分配差异 好厉害啊
            for (int i = 0; i < result.size(); i++) {
                result.set(i, BigDecimalUtil.round(result.get(i), scale));
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {


        List<Double> result = new ArrayList<>();
        // 13.626332% 47.989636% 9.596008% 28.788024
        result.add(13.62);
        result.add(47.98);
        result.add(9.59);
        result.add(28.78);
        result.add(0d);

        List<Double> oldRes = result;
        //进一位看看
        decimalPointGoUpOne(result, 1);
        //如果不对，再进一位
        decimalPointGoUpOne(result, 0);
        /* 处理百分数之和大于100的问题 end*/
        double sumResult = 0d;
        for (int i = 0; i < result.size(); i++) {
            sumResult = BigDecimalUtil.add(sumResult, result.get(i));
        }
        //进了2次都不能搞定，说明无限循环情况
        if (sumResult != 100) {
            //返回原来的值
            result = oldRes;
        }
        System.out.println(result);


//        Runnable r1 = () -> System.out.println("Hello World 1");
//
//        Runnable r2 = new Runnable(){
//            @Override
//            public void run(){
//                System.out.println("Hello World 2");
//                System.out.println("Hello World 2.5");
//            }
//        };
//        process(r1);
//        process(r2);
//        process(() -> System.out.println("Hello World 3"));
    }
}
