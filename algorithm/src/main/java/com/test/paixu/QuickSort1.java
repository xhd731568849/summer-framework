package com.test.paixu;

/**
 * 快速排序
 */
public class QuickSort1 {

    public static void main(String[] args) {
        int[] a = {9, 1, 12, 2, 4, 3, 10, 32};
        sort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.println(i);
        }
    }
    public static void sort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int base = arr[low];
        int i = low, j = high;
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        arr[low] = arr[i];
        arr[i] = base;
        sort(arr, low, i - 1);
        sort(arr, i + 1, high);
    }


}
