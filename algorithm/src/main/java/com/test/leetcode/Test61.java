package com.test.leetcode;

import java.util.Arrays;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * <p>
 * 美团的一道面试题...
 */
public class Test61 {

    /**
     * 有问题........
     *
     * @param nums
     * @return
     */
    public boolean 第一次编写(int[] nums) {
        Arrays.sort(nums);
        boolean flag = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0 && nums[i + 1] != 0) {
                if (nums[i + 1] - nums[i] != 1) {
                    return false;
                }
            } else if (nums[i + 1] == 0 && nums[i] != 0) {
                nums[i + 1] = nums[i] + 1;
            } else if (nums[i] == 0 && nums[i + 1] != 0) {
                nums[i] = nums[i + 1] - 1;
            } else {
                i = i + 2;
            }
        }
        return flag;
    }


}
