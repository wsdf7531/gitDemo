import acm.ListNode;
import acm.Search;
import acm.TwoSum;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @Author: Xusj
 * @Date: 2018/9/4
 * @Description:
 */
public class Main {
    //调用二分查询
    public static int binarySearch(){
        int[] arr={4,5,8,9,7,4,2,11,15};
        int x=7;
        int res=new Search().binarySearch(arr,x);
        return res;
    }
    //调用两数之和
    public static int[] twoSum(){
        int[] nums={3,4,5,7};
        int target=9;
        int[] res=new TwoSum().twoSum(nums,target);
        return res;
    }

    public void oneToHundred(){
        for (int i=1;i<=100;i++){
            System.out.println(i);
        }
    }
    public static void main(String[] args) throws ParseException {
        ListNode listNode=new ListNode(342);
        ListNode listNode2=new ListNode(465);
        listNode.addTwoNumbers(listNode,listNode2);
        Integer lampSize=500;

        Random rand=new Random(47);
        System.out.println(rand);
        System.out.println(Arrays.toString(twoSum()));
//        System.out.println(System.currentTimeMillis());
        new Main().oneToHundred();

    }
}
