package com.test.kuaishou;

import com.test.common.MyUtil;
import com.test.common.TreeNode;

/**
 *
 * [1,2,3,4,null,6,7,8]
 *
 */
public class Test4 {


    public static void main(String[] args) {
        String case1 = "[1,2,3,4,null,6,7,8]";
        TreeNode res = get(case1);
        MyUtil.show(res);

        String case2 = "[1,2,null,4,5,null,null,8,9,10]";
        res = get(case2);
        MyUtil.show(res);
    }

    public static TreeNode get(String s) {
        s = s.substring(1, s.length()-1);
        String[] arr = s.split(",");
        return helper(arr,1);

    }


    public static TreeNode helper(String[] arr , int pos) {
        if(pos > arr.length ) {
            return null;
        }
        if(!"null".equals(arr[pos-1])) {
            TreeNode n = new TreeNode(Integer.valueOf(arr[pos-1]));
            n.left = helper(arr ,pos*2);
            n.right = helper(arr ,pos*2+1);
            return n;
        }else {
            return null;
        }
    }

}
