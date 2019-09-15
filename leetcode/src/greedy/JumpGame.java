package greedy;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if(nums.length <= 1) {
            return true;
        }

        // 保存每个位置能到达的最远坐标
        int[] index = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            index[i] = i + nums[i];
        }

        // 记录从0到position，能到达的最远坐标
        int maxIndex = 0;

        int position = 0;
        for(; position < nums.length; position++) {
            // 如果position的位置超过了从0到position能到达的最远坐标，返回false
            if(position > maxIndex) {
                return false;
            }
            maxIndex = Math.max(maxIndex, index[position]);
        }

        return true;
    }
}

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class JumpGameII{
    public int jump(int[] nums) {
        if(nums.length <= 1) {
            return 0;
        }

        int steps = 1;

        // 记录从0到i能达到的最大位置
        int preMaxIndex = 0;
        // 记录当前能达到的最大位置
        int currMaxIndex;

        // 保存每个位置能到达的最远坐标
        int[] index = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            index[i] = i + nums[i];
        }

        currMaxIndex = nums[0];
        for(int i = 0; i < nums.length; i++) {
            // 无法再继续向前移动，则从前面位置中能跳到最远的位置起跳
            if(i > currMaxIndex) {
                steps++;
                currMaxIndex = preMaxIndex;
            }
            preMaxIndex = Math.max(preMaxIndex, index[i]);
        }

        return steps;
    }
}