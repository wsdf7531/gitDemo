package acm;

import util.DateAndTimeUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Xusj
 * @Date: 2018/10/17
 * @Description:
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
    /**两数之和*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //dummyHead  伪节点
        ListNode dummyHead = new ListNode(0);
        //l1代表链表 p初始化为链表的头部 curr=节点
        ListNode p = l1, q = l2, curr = dummyHead;
        //进位符
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            //因为有这个  会让  curr.next 与 dummyHead 相等
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(3);
        ListNode listNode2=new ListNode(4);
        listNode.addTwoNumbers(listNode,listNode2);

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        while (start.compareTo(end) <= 0) {
            Calendar cal = start;
//            cal.add(Calendar.DAY_OF_YEAR, -1);
            Date now = new Date(cal.getTimeInMillis());
            String dateAt = "";
            try {
                dateAt = DateAndTimeUtil.formatToString(now, DateAndTimeUtil.Format_Date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long noonTime = 0;
            try {
                noonTime = DateAndTimeUtil.convertStringToMillisecond(dateAt + " 12:00:00.000", DateAndTimeUtil.Format_FullDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String fromTime = "00:00";
            if (now.getTime() < noonTime) {
                cal.add(Calendar.DAY_OF_YEAR, -1);
                try {
                    dateAt = DateAndTimeUtil.formatToString(new Date(cal.getTimeInMillis()), DateAndTimeUtil.Format_Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fromTime = "12:00";
            }

            int peroid = 12;
            System.out.println(dateAt+fromTime+peroid);
            start.add(Calendar.DAY_OF_YEAR,1);
        }


    }

}
