package com.test.leetcode;

import java.util.Stack;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test110 {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  //。。。。。。。。。。。。。。。。第一次编写。。。。。。。。。。。。。。。。
    public boolean isBalanced(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !s.isEmpty()){
            while(cur!=null ){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            int leftHeight = height(cur.left);
            int rightHeight = height(cur.right);
            if(Math.abs(leftHeight-rightHeight)>1){
                return false;
            }
            cur = cur.right;
        }
        return true;

    }
    public int height(TreeNode root){
        if(root == null) {
            return 0;
        }
        int leftHeight = root.left == null ? 0 : height(root.left);
        int rightHeight = root.right == null ? 0 : height(root.right);
        return Math.max(leftHeight,rightHeight) + 1;
    }



    // ................第二次编写.....................
    public boolean isBalanced2(TreeNode root) {
        ReturnType r = helper(root);
        return r.isBalanced;
    }
    public ReturnType helper(TreeNode root) {
        if(root == null) {
            return new ReturnType(0,true);
        }
        ReturnType leftRetrun = helper(root.left);
        System.out.println("leftRetrun:"+leftRetrun.height + leftRetrun.isBalanced);
        if(!leftRetrun.isBalanced){
            return  new ReturnType(0,false);
        }
        ReturnType rightRetrun = helper(root.right);
        System.out.println("rightRetrun:"+rightRetrun.height + rightRetrun.isBalanced);
        if(!rightRetrun.isBalanced){
            return  new ReturnType(0,false);
        }
        if(Math.abs(leftRetrun.height - rightRetrun.height) > 1){
            return  new ReturnType(0,false);
        }
        return new ReturnType(Math.max(leftRetrun.height,rightRetrun.height)+1,true);
    }

    public  static class ReturnType{
        int height;
        boolean isBalanced;
        public ReturnType(int height , boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }


}
