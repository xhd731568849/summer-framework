package com.test;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {7,5,9,8,3,2,4};
        quickSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }



    public static void quickSort(int[] arr ,int start , int end) {
        if(start > end) {
            return ;
        }
        int pivot = arr[start];
        int i = start , j = end;
        while(i<j) {
            while(i<j && arr[j] >= pivot) {
                j--;
            }
            while(i<j && arr[i] <= pivot) {
                i ++;
            }
            if(i<j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[start] = arr[i];
        arr[i] = pivot;
        quickSort(arr,start,i-1);
        quickSort(arr,i+1,end);
    }
}
