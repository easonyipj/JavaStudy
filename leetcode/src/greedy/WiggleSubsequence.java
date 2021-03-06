package greedy;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate
 * between positive and negative. The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and
 * the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 *
 * Example 1:
 *
 * Input: [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 *
 * Example 2:
 *
 * Input: [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 *
 * Example 3:
 * Input: [1,2,3,4,5,6,7,8,9]
 * Output: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wiggle-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WiggleSubsequence {

    private static final int BEGIN = 0;
    private static final int UP = 1;
    private static final int DOWN = 2;

    public int wiggleMaxLength(int[] nums) {
        int maxLength = 1;
        int state = BEGIN;

        if(nums.length == 0) {
            return 0;
        }

        for(int i = 1; i < nums.length; i++) {
            switch (state) {
                case BEGIN:
                    if(nums[i] > nums[i - 1]) {
                        state = UP;
                        maxLength++;
                    }

                    if(nums[i] < nums[i - 1]){
                        state = DOWN;
                        maxLength++;
                    }

                    break;
                case UP:
                    if(nums[i] < nums[i - 1]) {
                        state = DOWN;
                        maxLength++;
                    }
                    break;
                case DOWN:
                    if(nums[i] > nums[i - 1]) {
                        state = UP;
                        maxLength++;
                    }
                    break;
            }
        }

        return  maxLength;
    }
}
