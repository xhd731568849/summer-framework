package com.summer.demos;

public class TestA {
    public static boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int length = s1.length();
        int[] flags = new int[length];
        for (int i = 0; i < length; i++) {
            char a = s1.charAt(i);
            boolean find = false;
            for (int j = 0; j < length; j++) {
                if (flags[j] == 1) {
                    continue;
                }
                char b = s2.charAt(j);
                if (a == b) {
                    flags[j] = 1;
                    find = true;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkPermutation("abc", "bad"));

    }
}