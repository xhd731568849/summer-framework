package com.test.leetcode;

import java.util.HashMap;

public class Test659 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 4, 4, 5, 5};
        System.out.println(new Test659().isPossible(a));
    }

    public boolean isPossible(int[] nums) {
        Counter count = new Counter();
        Counter tails = new Counter();
        for (int x : nums) count.add(x, 1);

        for (int x : nums) {
            if (count.get(x) == 0) {
                continue;
            } else if (tails.get(x) > 0) {
                tails.add(x, -1);
                tails.add(x + 1, 1);
            } else if (count.get(x + 1) > 0 && count.get(x + 2) > 0) {
                count.add(x + 1, -1);
                count.add(x + 2, -1);
                tails.add(x + 3, 1);
            } else {
                return false;
            }
            count.add(x, -1);
        }
        return true;
    }

    static class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }
}

