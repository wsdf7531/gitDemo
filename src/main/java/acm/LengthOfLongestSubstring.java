package acm;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Xusj
 * @Date: 2018/10/18
 * @Description:
 */
public class LengthOfLongestSubstring {
    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。

     示例 1:

     输入: "abcabcbb"
     输出: 3
     解释: 无重复字符的最长子串是 "abc"，其长度为 3。
     示例 2:

     输入: "bbbbb"
     输出: 1
     解释: 无重复字符的最长子串是 "b"，其长度为 1。
     示例 3:

     输入: "pwwkew"
     输出: 3
     解释: 无重复字符的最长子串是 "wke"，其长度为 3。
     请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
     *
     * */
    public int lengthOfLongestSubstring(String s) {
//        String sub=null;
//        List<Character> list=new ArrayList<>();
//        for (int i=0;i<s.length();i++){
//            //将每个位置的元素 存入list
//            Character a=s.charAt(i);
//            list.add(a);
//        }
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                //把字符串 和 两个位置传入 ，如果满足没有重复，取长度最大值
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }
    /**判断是否有重复字符*/
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        String s="abcdd";
        int res=new LengthOfLongestSubstring().lengthOfLongestSubstring(s);
        System.out.println(res);
    }
}
