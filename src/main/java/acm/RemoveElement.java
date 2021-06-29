package acm;

/**
 * @Author: Xusj
 * @Date: 2020/1/16
 * @Description: 数组元素移除
 */
public class RemoveElement {

    /**
     * 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。------>只需要把前面长度的元素更新
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     */


    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        //获取剩余元素数量
        int remain = new RemoveElement().removeElement(nums, 2);
        System.out.println(remain);
        //输出每个元素
        for (int i = 0; i < remain; i++) {
            System.out.println(nums[i]);
        }
    }
}
