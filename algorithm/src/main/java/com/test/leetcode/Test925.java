package com.test.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Test925 {

    

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);

    }

    public static boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while(i<name.length() && j<typed.length()) {
            if(name.charAt(i)==typed.charAt(j)) {
                i++;
                j++;
                continue;
            }
            else if(j>0 && typed.charAt(j)!=typed.charAt(j-1) || j==0) {
                return false;
            }
            while(j>0 && j<typed.length() && typed.charAt(j)==typed.charAt(j-1)) {
                j++;
            }
        }
        while(j>0 && j<typed.length() && typed.charAt(j)==typed.charAt(j-1)) {
            j++;
        }
        return i == name.length() && j == typed.length();

    }
}
