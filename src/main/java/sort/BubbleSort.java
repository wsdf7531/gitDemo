package sort;

import java.util.Arrays;

/**
 * @Author: Xusj
 * @Date: 2019/10/30
 * @Description:
 */
public class BubbleSort {
    public void bubbleSort() {
        int[] array = {3, 4, 2, 5, 1, 9, 7, 6, 8};
        int size = array.length;
        long startTime = System.currentTimeMillis();
        System.out.println("bubble sort start, startAt:" + startTime);
        for (int i = 0; i < size; i++) {
            boolean mark = true;
            for (int j = 0; j < size - i - 1; j++) {
                //从小到大排序
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    mark = false;
                }
            }
            if (mark) {
                long endTime = System.currentTimeMillis();
                System.out.println("bubble sort end, endAt:" + endTime + "cost:" + (endTime - startTime));
                break;
            }
            System.out.println("第" + (i+1) + "次排序：" + Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        System.out.println("冒泡排序test");
        new BubbleSort().bubbleSort();
    }
}
