package com.test.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Test225 {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


    public static class MyStack {

        private Queue<Integer> q1;
        private Queue<Integer> q2;
        private Integer top;

        /** Initialize your data structure here. */
        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            q1.add(x);
            top = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(q1.size() > 1) {
                top = q1.remove();
                q2.add(top);
            }
            Integer b = q1.remove();
            Queue<Integer> tmp = q1;
            q1 = q2;
            q2 = tmp;
            return b;
        }

        /** Get the top element. */
        public int top() {
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty();
        }
    }


}
