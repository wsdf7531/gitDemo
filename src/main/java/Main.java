import acm.ListNode;
import acm.Search;
import acm.TwoSum;
import com.sun.org.apache.xpath.internal.SourceTree;
import model.User;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: Xusj
 * @Date: 2018/9/4
 * @Description:
 */
public class Main {

    private ArrayList<T> storage = new ArrayList<T>();

    public enum Size{
        SMALL , MEDIUM , LARGE
    }

    public List<Long[]> getListIntArray(Long[] arr, int size) {
        List<Long[]> arrayList = new ArrayList<>();
        int length=arr.length;
        int amount = length / size;
        for (int i = 0; i < amount; i++) {
            Long[] arr0 = new Long[size];
            for (int j = 0; j < size; j++) {
                arr0[j] = arr[j + i * size];
            }
            arrayList.add(arr0);
        }
        if (length%size!=0){
            int lastLength=length%size;
            Long[] lastArr = new Long[lastLength];
            int j=lastLength;
            for (int i=length;i>length-(length%size);i--){
                lastArr[j-1]= arr[i-1];
                j--;
            }
            arrayList.add(lastArr);
        }
        return arrayList;
    }


    public static void main(String[] args) throws ParseException {
//        BigDecimal testPower= BigDecimal.valueOf(5);
//        User user=new User();
////        if (testPower.compareTo(user.getPower())>0){
////            System.out.println("2");
////        }
//        Size size=Size.LARGE;
//        System.out.println(size);
//
//        ListNode listNode=new ListNode(342);
//        ListNode listNode2=new ListNode(465);
//        listNode.addTwoNumbers(listNode,listNode2);
//        Integer lampSize=500;
//
//        Random rand=new Random(47);
        Long[] arr=new Long[]{0L, 1L, 2L,3L,4L,5L,6L,7L,11L,5L,8L,11L,25L,65L,99L,55L,88L,44L,22L,25L};
        int length=arr.length;
        Integer size=3;
        List<Long[]> res=new Main().getListIntArray(arr,size);
        System.out.println(res);


    }
}
