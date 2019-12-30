package dp;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        int dpMin = nums[0];
        int dpMax = nums[0];
        int max = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < 0) {
                int temp = dpMax;
                dpMax = dpMin;
                dpMin = temp;
            }
            dpMax = Math.max(dpMax * nums[i], nums[i]);
            dpMin = Math.min(dpMin * nums[i], nums[i]);
            max = Math.max(max, dpMax);

        }

        return max;
    }
}
