package com.test.mt;

import java.util.Stack;

public class Test2 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 搜索二叉树：中序遍历是左中右，而搜索二叉树就是 左 < 中 < 右，稍加改动，将弹出的值用一个变量保存（可以初始化为系统最小值），比较大小
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<Node>();
        int pre = Integer.MIN_VALUE;// 这里定义一个系统最小数，用于下面的和前面的数比较
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                // --------这里本来是中序遍历打印节点的地方，这里改成和前面的节点值比较，如果大于，直接返回false，否则迭代------------
                if (head.value < pre) { // 搜索二叉树默认节点不相等，若相等，就会变成同一个节点
                    return false;
                } else {
                    pre = head.value;
                }
                // -------------------------------------------
                head = head.right;
            }

        }

        return true;
    }

    //测试
    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(7);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isBST(head));
    }
}
