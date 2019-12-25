package dp;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]以nums[i]结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        int max = 1;
        dp[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            int k = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    k = Math.max(k, dp[j] + 1);
                }
            }
            dp[i] = k;
            max = Math.max(k, max);
        }

        return max;
    }

    /**TODO
     * use a stack
     */

}
