package recursion;

import java.util.*;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();

        // 排序
        Arrays.sort(nums);
        // 使用set去重
        Set<List<Integer>> sets = new HashSet<>();

        int set = 1 << nums.length;

        for(int i = 0; i < set; i++) {
            List<Integer> list = new ArrayList<>();
            int count = 0;
            for(int j = 0; j < nums.length; j++) {
                // 1 << j指示nums的第i个数（from 1）
                if((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                    count += nums[j];
                }
                if(count >= target) {
                    break;
                }
            }
            if(count == target) {
                sets.add(list);
            }
        }

        lists.addAll(sets);

        return lists;
    }
}
