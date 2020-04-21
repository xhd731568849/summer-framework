package com.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test94 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 第一次
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root.left != null) {
            inorderTraversal(root.left);
        } else {
            res.add(root.val);
        }
        res.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        } else {
            res.add(root.val);
        }
        return res;
    }


    /**
     * 每次递归都是一个新的 res  ， 这里要提取辅助函数！！！！！！！！！！！！！！！！！！
     * 每次递归，都将结果对象传下去
     *
     * @param root
     * @return
     */
    public List<Integer> order2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        res.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return res;
    }


    /**
     * 第三次；；；；
     *
     * @param root
     * @return
     */
    public List<Integer> order3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper3(root, res);
        return res;
    }

    public List<Integer> helper3(TreeNode root, List<Integer> res) {
        //这里root是可能为空的， 因为抽取了辅助函数。和第一次情况不同了！！！
        if (root.left != null) {
            helper3(root.left, res);
        }
        res.add(root.val);
        if (root.right != null) {
            helper3(root.right, res);
        }
        return res;
    }


    /**
     * 第4次
     *
     * @param root
     * @return
     */
    public List<Integer> order4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper3(root, res);
        return res;
    }

    public List<Integer> helper4(TreeNode root, List<Integer> res) {
        //这里root是可能为空的， 因为抽取了辅助函数。和第一次情况不同了！！！
        if (root != null) {
            if (root != null) {
                helper4(root.left, res);
            }
            res.add(root.val);
            if (root != null) {
                helper4(root.right, res);
            }
        }
        return res;
    }


    public List<Integer> 第五次看了一遍栈的答案后自己写(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                root = root.left;
                //这里root有可能是null ......
                stack.push(root.val);
            }
            Integer pop = stack.pop();
            res.add(pop);
            root = root.right;
        }
        return res;
    }


    public List<Integer> 第六次编写(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                //这里总不可能是null了吧、、、、、
                stack.push(root.val);
                root = root.left;
            }
            Integer pop = stack.pop();
            res.add(pop);
            //但是这里 会是null
            root = root.right;
        }
        return res;
    }


    public List<Integer> 第七次编写(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            //想保存一个前驱节点 ， 但是还是 不行，，大脑不够用了。。。
            TreeNode pre = null;
            while (root != null) {
                stack.push(root.val);
                pre = root;
                root = root.left;
            }
            Integer pop = stack.pop();
            res.add(pop);
            pre = pre.right;
        }
        return res;
    }


    public List<Integer> 第八次看了答案后编写(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            Integer val = stack.pop().val;
            res.add(val);
            //这里又空指针了。。。。。。。。。。。
            root = root.right;
        }
        return res;
    }

    /**
     * 运行成功。。。。
     *
     * @param root
     * @return
     */
    public List<Integer> 第九次看了答案后编写(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //这个栈里面竟然要存Node ，  但是我觉得直接存int可能也是可以的。。自己写不出来啊。。-，-
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //注意这里是替换root引用。。。。。。。。。。。。。。。。
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    //20200421
}


