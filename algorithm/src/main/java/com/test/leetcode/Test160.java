package com.test.leetcode;

/**
 * 链表交叉点， 美团面试题。
 */
public class Test160 {


    public ListNode 看完答案后第二次编写(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;


    }


    /**
     * @param headA
     * @param headB
     * @return
     */
    public ListNode 看完答案后第一次编写(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        while (a != b) {
            //这里如果是null ， 应该设置为另个一链表的头部， 而不是b ， b是变化的
            a = a == null ? b : a.next;
            b = b == null ? a : b.next;
        }
        return a;


    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
