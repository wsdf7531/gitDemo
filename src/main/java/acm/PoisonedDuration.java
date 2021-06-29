package acm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author:xusj
 * @Date:2020/12/11
 * @Description: LeetCode 495.提莫攻击
 */
public class PoisonedDuration {

    /**
     * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
     * 现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
     *
     * 提示：
     * 你可以假定时间序列数组的总长度不超过 10000。
     * 你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。
     *
     * 示例1:
     * 输入: [1,4], 2
     * 输出: 4
     * 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
     * 第 4 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。
     * 所以最终输出 4 秒。
     *
     * 示例2:
     * 输入: [1,2], 2
     * 输出: 3
     * 原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
     * 但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
     * 由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
     * 所以最终输出 3 。
     */

    public static void main(String[] args) {
        String code = String.valueOf(System.currentTimeMillis()).substring(4);
        System.out.println(code);


        byte[] data = new byte[]{0x60, 0x32, 0x31, 0x30, 0x31, 0x35, 0x46, 0x46, 0x31, 0x32, 0x33, 0x34, 0x35, 0x7b, 0x22, 0x6d, 0x69, 0x22, 0x3a, 0x31, 0x32, 0x33, 0x34, 0x35, 0x7d, 0x31, 0x35, 0x38, 0x61, 0x27};
        //去除包头包尾
        data = Arrays.copyOfRange(data,1,data.length-5);
        System.out.println(new String(data));
        //提取sn
        byte[] snByte = Arrays.copyOfRange(data,0,12);
        System.out.println(new String(snByte));
        byte[] bodyByte = Arrays.copyOfRange(data,12,data.length);
        System.out.println(new String(bodyByte));

        Double i = 0.553;
        i = i*1000;
        System.out.println(i);
        Integer current = i.intValue();
        System.out.println(current);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMdd");

        String newDate = simpleDateFormat.format(new Date());
        System.out.println(newDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String ts = sdf.format(new Date());
        System.out.println(ts);

        String as = "0508";
        String a = as.substring(0,2);
        String s = as.substring(2);
        System.out.println(a+"-"+s);
    }


}
