package com.test.kuaishou;

import com.test.common.ListNode;
import com.test.common.TreeNode;

import java.util.List;

public class Test5 {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        n1.left = new TreeNode(2);
        n1.right = new TreeNode(3);
        n1.left.left = new TreeNode(4);
        n1.left.right = new TreeNode(5);
        n1.right.right = new TreeNode(1);
        n1.right.right.right = new TreeNode(7);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(2);
        n2.next.next = new ListNode(5);

        ListNode n3 = new ListNode(2);
        n3.next = new ListNode(1);

        ListNode n4 = new ListNode(1);
        n4.next = new ListNode(7);

        System.out.println(contain(n1,n2));
        System.out.println(contain(n1,n3));
        System.out.println(contain(n1,n4));
    }

    public static boolean contain(TreeNode n1 , ListNode n2) {
        if(n2 == null) {
            return true;
        }
        if(n1 == null) {
            return false;
        }

        if(n1.val == n2.val) {
            return contain(n1.left,n2.next) || contain(n1.right,n2.next);
        }else {
            return contain(n1.left,n2) || contain(n1.right,n2);
        }
    }

}
