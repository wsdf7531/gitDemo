package acm;

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

    }

}
