package acm;

import java.util.HashMap;

/**
 * @Author: Xusj
 * @Date: 2018/11/8
 * @Description:
 */
public class romanToInt {
    /**
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

     示例 1:
     输入: "III"
     输出: 3

     示例 2:
     输入: "IV"
     输出: 4

     示例 3:
     输入: "IX"
     输出: 9

     示例 4:
     输入: "LVIII"
     输出: 58
     解释: L = 50, V= 5, III = 3.

     示例 5:
     输入: "MCMXCIV"
     输出: 1994
     解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * */

    public int romanToInt(String s) {
        HashMap<String, Integer> hashMap=new HashMap<>(7);
        hashMap.put("I",1);
        hashMap.put("V",5);
        hashMap.put("X",10);
        hashMap.put("L",50);
        hashMap.put("C",100);
        hashMap.put("D",500);
        hashMap.put("M",1000);
        int sum=0;
        int now;
        int last=0;
        for (int i=0;i<s.length();i++){
            String nowS=s.substring(i,i+1);
            String lastS;
            if (i>0){
                lastS=s.substring(i-1,i);
                last=switchHandle(lastS,hashMap);
            }
            now=switchHandle(nowS,hashMap);
            //当前比上一个大  6中特别情况
            if (now>last){
                sum=sum+now-last*2;
            }else {
                sum=sum+now;
            }
        }
        return sum;
    }
    private int switchHandle(String s, HashMap<String, Integer> hashMap){
        int now = 0;
        switch (s){
            case "I":
                now =hashMap.get("I");
                break;
            case "V":
                now =hashMap.get("V");
                break;
            case "X":
                now =hashMap.get("X");
                break;
            case "L":
                now =hashMap.get("L");
                break;
            case "C":
                now =hashMap.get("C");
                break;
            case "D":
                now =hashMap.get("D");
                break;
            case "M":
                now =hashMap.get("M");
                break;
            default:
        }
        return now;
    }
    public static void main(String[] args) {
        //int rev=new romanToInt().romanToInt("MCMXCIV");
        String rev="MCMXCIV";
        int c=rev.indexOf("CM");
        System.out.println(c);
    }
}
