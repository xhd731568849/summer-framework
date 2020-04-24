package com.test.leetcode;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test148 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 完全写不出来
     *
     * @param head
     * @return
     */
    public static ListNode 第一次编写(ListNode head) {
        while (head.next != null) {
            if (head.val > head.next.val) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = head;
            } else {
                head = head.next;
            }
        }
        return null;
    }


    /**
     * 看完答案后第一次写
     * <p>
     * 竟然一次性写对了。。。。。。。。。
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;   //切断。

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }


    //20200424 写错了。
    public static ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList1(head);
        ListNode right = sortList1(tmp);
        ListNode res = new ListNode(0);
        ListNode n = res;
        while (left != null && right != null) {
            if (left.val < right.val) {
                n.next = left;
                left = left.next;
            } else {
                n.next = right;
                right = right.next;
            }
            n = n.next;
        }
        n.next = right == null ? left : right;
        //这里出错了！！！！！！！1应该是res.next , 因为res 是一个头结点，毫无作用。。。。
        return res;

    }
}
