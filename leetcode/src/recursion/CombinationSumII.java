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
        List<Integer> list = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        // 使用set去重
        Set<List<Integer>> sets = new HashSet<>();

        dfs(0, 0, target, nums, sets, list);

        lists.addAll(sets);
        return lists;
    }

    public void dfs(int deep, int sum, int target, int[] nums, Set<List<Integer>> sets, List<Integer> list) {
        // 当sum > target时剪支操作
        if(deep == nums.length || sum > target) {
            return;
        }

        // 先递归加上
        list.add(nums[deep]);
        sum += nums[deep];

        if(target == sum) {
            List<Integer> temp = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
            Collections.copy(temp, list);
            sets.add(temp);
        }

        // 继续向下
        dfs(deep + 1, sum, target, nums, sets, list);

        // 弹出最后一个元素，继续向下
        list.remove(list.size() - 1);
        sum -= nums[deep];
        dfs(deep + 1, sum, target, nums, sets, list);
    }
}


