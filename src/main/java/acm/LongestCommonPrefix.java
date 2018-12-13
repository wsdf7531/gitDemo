package acm;

/**
 * @Author: Xusj
 * @Date: 2018/11/19
 * @Description:
 */
public class LongestCommonPrefix {

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。

     如果不存在公共前缀，返回空字符串 ""。

     示例 1:

     输入: ["flower","flow","flight"]
     输出: "fl"
     示例 2:

     输入: ["dog","racecar","car"]
     输出: ""
     解释: 输入不存在公共前缀。
     说明:

     所有输入只包含小写字母 a-z 。
     * */
    public String longestCommonPrefix(String[] strs) {
        //横向
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。如果没有找到子字符串，则返回-1。
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;

    }

    public static void main(String[] args) {
        String[] strs=new String[]{"flower","flow","flight"};
        String res=new LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(res);
    }
}
