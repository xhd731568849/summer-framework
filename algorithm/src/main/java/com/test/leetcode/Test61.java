package com.test.leetcode;

import java.util.Arrays;
import java.util.HashSet;

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


    public boolean 看过答案第二次编写(int[] nums) {
        Arrays.sort(nums);
        int joke = 0;
        for (int i = 0; i < 4; i++) {

            //if(nums[i] == nums[i+1]) return false;
            //else if(nums[i]==0) joke ++;
            //错误写法，先判断前两个相不相等，这里是不对的，应该先判断是不是等于0

            if (nums[i] == 0) joke++;
            else if (nums[i] == nums[i + 1]) return false;
        }
        return nums[4] - nums[joke] < 5;
    }

    public boolean 第三次编写(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) continue;
            if (set.contains(num)) {
                return false;
            } else {
                set.add(num);
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }

}
