package acm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2018/11/28
 * @Description:
 */
public class FourSum {
    /**
     * 四数之和
     *
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

     注意：
     答案中不可以包含重复的四元组。

     示例：
     给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

     满足要求的四元组集合为：
     [
     [-1,  0, 0, 1],
     [-2, -1, 1, 2],
     [-2,  0, 0, 2]
     ]
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<>();
        if (nums==null || nums.length<4){
            return res;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            // 对第一重元素去重处理
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 对第二重元素去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int p = j + 1;
                int q = nums.length - 1;
                while (p < q) {
                    int sum = nums[i] + nums[j] + nums[p] + nums[q];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[p]);
                        list.add(nums[q]);
                        res.add(list);
                        // 对第三重元素去重操作
                        while (p < q && nums[p] == nums[p + 1]) {
                            p++;
                        }
                        while (p < q && nums[q] == nums[q - 1]) {
                            q--;
                        }
                        p++;
                        q--;
                    }else if (sum < target) {
                        p++;
                    }else {
                        q--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums =new int[]{1,0,-1,0,-2,2};
        List<List<Integer>> res=new FourSum().fourSum(nums,0);
        System.out.println(res);
    }
}
