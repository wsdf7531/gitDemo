package sort;

import java.util.Arrays;

/**
 * @Author: Xusj
 * @Date: 2020/5/19
 * @Description:
 */
public class QuickSort {

    /**
     * 快速排序
     */
    void quickSort(int[] s, int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) {
                    // 从右向左找第一个小于x的数
                    j--;
                }
                if (i < j) {
                    s[i++] = s[j];
                }

                while (i < j && s[i] < x) {
                    // 从左向右找第一个大于等于x的数
                    i++;
                }
                if (i < j) {
                    s[j--] = s[i];
                }
            }
            s[i] = x;
            // 递归调用
            quickSort(s, l, i - 1);
            quickSort(s, i + 1, r);
        }
    }

    public static void main(String[] args) {
        System.out.println("快速排序test");
        int[] array = {3, 4, 2, 5, 1, 9, 7, 6, 8};
        new QuickSort().quickSort(array, 0, array.length - 1);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
