package acm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Xusj
 * @Date: 2018/11/21
 * @Description:
 */
public class LetterCombinations {
    /**
     * 17. 电话号码的字母组合
     *
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

     给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
        2-abc 3-def ...9-wxyz


     示例:

     输入："23"
     输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     说明:
     尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     * */
    public List<String> letterCombinations(String digits) {

        List<String> list = new ArrayList<>();
        if (digits.isEmpty()) {
            return list;
        }
        //定义每个按键对应的字符串
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        list.add("");
        String temp = null;
        int id = 0;

        //循环嵌套  目前是n的m次  性能极差
        for (int i = 0; i < digits.length(); i++) {
            //分割  String.charAt、即可
            id = Integer.parseInt(digits.charAt(i) + "");
            while (list.get(0).length() == i) {
                temp = list.remove(0);
                for (int j = 0; j < mapping[id].length(); j++) {
                    list.add(temp + mapping[id].charAt(j));
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> res=new LetterCombinations().letterCombinations("245");
        System.out.println(res);
    }
}
