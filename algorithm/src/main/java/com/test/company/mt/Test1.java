package com.test.company.mt;

public class Test1 {

    public static class Node {
        private Node left;
        private Node right;
        private Integer value;

        public Node(Integer value) {
            this.value = value;
        }
    }

    public static class ReturnType {
        private boolean isBalance;
        private int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static ReturnType isBalance(Node head) {
        if (head == null) {
            return new ReturnType(true, 0);
        }
        Node left = head.left;
        Node right = head.right;

        ReturnType leftReturn = isBalance(left);
        if (!leftReturn.isBalance) {
            return new ReturnType(false, 0);
        }

        ReturnType rightReturn = isBalance(right);
        if (!rightReturn.isBalance) {
            return new ReturnType(false, 0);
        }

        if (Math.abs(leftReturn.height - rightReturn.height) > 1) {
            return new ReturnType(false, 0);
        }
        return new ReturnType(true, Math.max(leftReturn.height, rightReturn.height) + 1);

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.right = new Node(8);
        head.right.right.right.right = new Node(9);

        System.out.println(isBalance(head).isBalance);

    }


}
