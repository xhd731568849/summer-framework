package com.test;

public class ZheBan {
    public static void main(String[] args) {
        int[] arr = {3,5,6,7,8,9,10,15,184};
        //System.out.println(get(arr,8));
        //System.out.println(get(arr,5));
        System.out.println(get(arr,184));
        System.out.println(get(arr,200));
        String a = "";
    }

    public static int get(int[] arr , int k) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int i = 0 ;
        int j = arr.length - 1;
        while(i <= j ) {
            int mid = (i + j) / 2;
            System.out.println("mid is " + mid + "i : " + i + "j: " + j);
            if(arr[mid] == k) {
                return mid;
            }
            if(arr[mid] < k) {
                i = mid + 1;
                continue;
            }
            if(arr[mid] > k) {
                j = mid - 1;
                continue;
            }
        }
        return -1;

    }
}
