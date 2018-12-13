package acm;

/**
 * @Author: Xusj
 * @Date: 2018/11/8
 * @Description:
 */
public class isPalindrome {
    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

     示例 1:
     输入: 121
     输出: true

     示例 2:
     输入: -121
     输出: false
     解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。

     示例 3:
     输入: 10
     输出: false
     解释: 从右向左读, 为 01 。因此它不是一个回文数*/

    /**
     * 将整数 对半
     * 若存在负号 false
     * 如果 是偶数个 除以2  分为两份 判断右侧-倒序与 左侧-正序 相同(归并为一个判断方法handle) 即可 且第一位不为0
     *      是奇数个 除以2  分为三份 中间那份不计  1,3 调用handle
     */
    public boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        if (x < 10){
            return true;
        }
        String a=Integer.toString(x);
        int n=a.length()/2;
        String first;
        String end;
        if (a.length()%2==0){
            first=Integer.toString(x).substring(0,n);
            end=Integer.toString(x).substring(n);
        }else {
            first=Integer.toString(x).substring(0,n);
            String mid=Integer.toString(x).substring(n);
            end=mid.substring(1);
        }
        StringBuffer sb = new StringBuffer(end);
        if (first.equals(sb.reverse().toString())){
            return true;
        }else {
            return false;
        }

        /**方法2*/
//        // 特殊情况：
//        // 如上所述，当 x < 0 时，x 不是回文数。
//        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
//        // 则其第一位数字也应该是 0
//        // 只有 0 满足这一属性
//        if(x < 0 || (x % 10 == 0 && x != 0)) {
//            return false;
//        }//
//        int revertedNumber = 0;
//        while(x > revertedNumber) {
//            revertedNumber = revertedNumber * 10 + x % 10;
//            x /= 10;
//        }//
//        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
//        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
//        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
//        return x == revertedNumber || x == revertedNumber/10;

    }
    public static void main(String[] args) {
        Boolean rev=new isPalindrome().isPalindrome(11);
        System.out.println(rev);
    }
}
