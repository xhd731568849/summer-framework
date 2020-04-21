package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 难度：中等
 * <p>
 * <p>
 * <p>
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Test15 {
    public static void main(String[] args) {
        int a[] = {0, 0, 0, 0};
        System.out.println(第四次(a));
    }

    public List<List<Integer>> 第一次我的思路(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        res.add(tmp);
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> 第二次看答案后自己写(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (nums[i] == nums[i + 1]) continue;   // 这里不太对 试想一下 -4,-1,-1,0,1,2
            int l = i + 1;
            int h = nums.length - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                    while (l < h && nums[l] == nums[l + 1]) l++;
                    while (l < h && nums[h] == nums[h - 1]) h--;
                    l++;
                    h--;
                }
                if (sum < 0) l++;
                if (sum > 0) h--;
            }
        }
        return res;
    }

    public static List<List<Integer>> 第三次(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 1 && nums[i] == nums[i - 1]) continue;// 这里  -4,-1,-1,0,1,2  我以为第i个应该和之前比。
            int l = i + 1;
            int h = nums.length - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                    while (l < h && nums[l] == nums[l + 1]) l++;
                    while (l < h && nums[h] == nums[h - 1]) h--;
                    l++;
                    h--;
                }
                if (sum < 0) l++;
                if (sum > 0) h--;
            }
        }
        return res;

    }

    //懵逼了。..........
    //懵逼了。..........
    //懵逼了。..........
    //懵逼了。..........
    //懵逼了。..........
    //懵逼了。..........


    /**
     * 运行成功
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> 第四次(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue; //重要！！！     看了答案， 这里马虎， 应该是从1开始，和之前元素比较
            int l = i + 1;
            int h = nums.length - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                    while (l < h && nums[l] == nums[l + 1]) l++;   //重要！！！
                    while (l < h && nums[h] == nums[h - 1]) h--;   //重要！！！
                    l++;
                    h--;
                }
                if (sum < 0) l++;
                if (sum > 0) h--;
            }
        }
        return res;
    }


    //20200421

}
