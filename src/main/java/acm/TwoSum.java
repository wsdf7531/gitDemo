package acm;

/**
 * @Author: Xusj
 * @Date: 2018/10/18
 * @Description:
 */
public class TwoSum {
    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。

     *  给定 nums = [2, 7, 11, 15], target = 9
        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
     * */

    public int[] twoSum(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[j]==target-nums[i]){
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("no result");
    }
    public static int[] twoSum(){
        int[] nums={3,4,5,7};
        int target=9;
        int[] res=new TwoSum().twoSum(nums,target);
        return res;
    }

}
