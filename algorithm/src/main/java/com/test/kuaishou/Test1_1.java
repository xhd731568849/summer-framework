package com.test.kuaishou;

public class Test1_1 {

    public static void main(String[] args) {
        Node node = makeTree();
        calLength(node);
        System.out.println(maxLength);
    }

    static int maxLength = 0;

    private static int calLength(Node node) {
        if (node == null) {
            return 0;
        }
        node.leftLen = node.left == null ? 0 : calLength(node.left) + 1;
        node.rightLen = node.right == null ? 0 : calLength(node.right) + 1;
        if (node.leftLen + node.rightLen > maxLength) {
            maxLength = node.leftLen + node.rightLen;
        }
        return node.leftLen > node.rightLen ? node.leftLen : node.rightLen;
    }


    public static Node makeTree() {
       /* 1
       4     2
         5  3  6
                 7*/
        Node head = new Node();
        head.data = 1;
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

        return head;
    }


    /*定义二叉树的父节点，左右子节点，数据节点*/
    public static class Node {
        //Node parent=null;
        Node left = null;
        Node right = null;
        Object data = null;
        int leftLen;
        int rightLen;
    }
}


