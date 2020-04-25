package com.test.kuaishou;

/**
 * 树的最远距离
 */
public class Test1_2 {

    //树的结构如下：
       /* 1
       4     2
         5  3  6
                 7*/
    static int maxLength = 0;

    public static void main(String[] args) {
        Node head = new Node();
        head.data = 1;        // 根节点赋初值
        makeTree(head);     // 构造一棵二叉树
        System.out.println("二叉树最大距离：" + calMaxLength(head));
    }

    public static int calMaxLength(Node head) {
        calREC(head);
        return maxLength;
    }

    //抽取辅助方法
    public static int calREC(Node head) {
        if (head == null) return 0;
        head.leftLen = head.left == null ? 0 : calREC(head.left) + 1;
        head.rightLen = head.right == null ? 0 : calREC(head.right) + 1;
        if (head.leftLen + head.rightLen > maxLength) maxLength = head.leftLen + head.rightLen;
        return head.leftLen > head.rightLen ? head.leftLen : head.rightLen;
    }

    public static void makeTree(Node head) {
       /* 1
       4     2
         5  3  6
                 7*/
        Node node4 = new Node();
        node4.data = 4;
        //node4.parent=head;
        head.left = node4;
        Node node5 = new Node();
        node5.data = 5;
        //node5.parent=node4;
        node4.right = node5;
        Node node2 = new Node();
        node2.data = 2;
        head.right = node2;
        //node2.parent=head;
        Node node3 = new Node();
        node3.data = 3;
        //node3.parent=node2;
        node2.left = node3;
        Node node6 = new Node();
        node6.data = 6;
        //node6.parent=node2;
        node2.right = node6;
        Node node7 = new Node();
        node7.data = 7;
        //node7.parent=node6;
        node6.right = node7;
    }

    public static class Node {
        //Node parent=null;
        Node left = null;
        Node right = null;
        Object data = null;
        int leftLen;
        int rightLen;
    }
}

/*定义二叉树的父节点，左右子节点，数据节点*/
