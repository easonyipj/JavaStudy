package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * -4 -1 -1 0 1 2
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len < 3) {
            return  res;
        }

        Arrays.sort(nums);

        for(int i = 0; i < len - 2; i++) {
            if(nums[i] > 0) {
                continue;
            }

            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = len - 1;
            while(l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum == 0) {
                    res.add(Arrays.asList(nums[i] ,nums[l] ,nums[r]));
                    while(l < r && nums[l] == nums[l + 1]) l++;
                    while(l < r && nums[r] == nums[r - 1]) r--;
                    r--;
                    l++;
                }else if(sum > 0) {
                    r--;
                }else {
                    l++;
                }
            }
        }

        return res;
    }
}
