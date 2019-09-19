package recursion;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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
            }
            sets.add(list);
        }

        lists.addAll(sets);

        return lists;
    }
}
