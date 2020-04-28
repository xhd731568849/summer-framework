package com.test.zijie;

/**
 * 求二叉树第n层的节点个数
 *            5
 *     3           9
 *  1   6       12    10
 *                   8
 */
public class Test1 {
    public static void main(String[] args) {
        Node n1 = new Node(5);
        n1.left = new Node(3);
        n1.left.left = new Node(1);
        n1.left.right = new Node(6);
        n1.right = new Node(9);
        n1.right.left = new Node(12);
        n1.right.right = new Node(10);
        n1.right.right.left = new Node(8);

        getNums(n1,1,3);
        System.out.println(sum);
    }
    static int sum = 0;
    public static void getNums(Node n , int count , int height) {
        if(n!=null && count == height) {
            sum ++;
            return;
        }
        //一定要注意，不要用 ++count 或  count++ !!!!!!!!!!!!!!!!!!
        if(count < height && n.left!=null) {
            getNums(n.left,count+1,height);
        }
        if(count < height && n.right!=null) {
            getNums(n.right,count+1,height);
        }
        return ;
    }

    public static class Node{
        Node left;
        Node right;
        int val;
        public Node(int val){
            this.val = val;
        }
    }
}
