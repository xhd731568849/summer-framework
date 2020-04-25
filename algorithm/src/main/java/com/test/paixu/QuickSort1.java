package com.test.paixu;

/**
 * 快速排序
 */
public class QuickSort1 {

    public static void main(String[] args) {
        int[] a = {9, 3, 5, 2, 4, 3, 10, 32};
        sort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public static void sort(int[] arr, int low, int high) {
        //1这里要有判断。
        if (low > high) {
            return;
        }
        int base = arr[low];
        int i = low, j = high;
        //注意这是 < 或者 不等于 都可以
        while (i < j) {
            //2这里都是带等于的。
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
        //这两行死记硬背一下。。。
        arr[low] = arr[i];
        arr[i] = base;
        sort(arr, low, i - 1);
        sort(arr, i + 1, high);

    }
}
