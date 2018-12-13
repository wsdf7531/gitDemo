package acm;

/**
 * @Author: Xusj
 * @Date: 2018/11/9
 * @Description:
 */
public class intToRoman {
    /**
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

     示例 1:
     输入: 3
     输出: "III"

     示例 2:
     输入: 4
     输出: "IV"

     示例 3:
     输入: 9
     输出: "IX"

     示例 4:
     输入: 58
     输出: "LVIII"
     解释: L = 50, V = 5, III = 3.

     示例 5:
     输入: 1994
     输出: "MCMXCIV"
     解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */

    /**方法1 暴力法*/
    public String intToRoman(int num) {
        String result="";
        while (num>=1000){
            result=result+"M";
            num=num-1000;
        }
        while (num>=900){
            result=result+"CM";
            num=num-900;
        }
        while (num>=500){
            result=result+"D";
            num=num-500;
        }
        while (num>=400){
            result=result+"CD";
            num=num-400;
        }
        while (num>=100){
            result=result+"C";
            num=num-100;
        }while (num>=90){
            result=result+"XC";
            num=num-90;
        }while (num>=50){
            result=result+"L";
            num=num-50;
        }
        while (num>=40){
            result=result+"XL";
            num=num-40;
        }
        while (num>=10){
            result=result+"X";
            num=num-10;
        }
        while (num>=9){
            result=result+"IX";
            num=num-9;
        }
        while (num>=5){
            result=result+"V";
            num=num-5;
        }
        while (num>=4){
            result=result+"IV";
            num=num-4;
        }
        while (num>=1){
            result=result+"I";
            num=num-1;
        }
        return result;
    }

    /**方法2，更优解*/
    public String intToRoman2(int num){
        String s = "";
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        // 对应的阿拉伯数字
        int[] numArray = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int i = 0;
        while (num != 0) {
            if (num >= numArray[i]) {
                s = s + roman[i];
                num = num - numArray[i];
            } else {
                i++;
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String rev=new intToRoman().intToRoman(2994);
//        String rev="MCMXCIV";


        System.out.println(rev);
    }
}
