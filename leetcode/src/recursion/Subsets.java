package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int deep = 0;

        dfs(deep, nums, list, lists);

        return lists;
    }

    private static void dfs(int deep, int[] nums, List<Integer> list, List<List<Integer>> lists) {
        if(deep == nums.length) {
            return;
        }
        // 先递归加上
        list.add(nums[deep]);
        List<Integer> temp = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
        Collections.copy(temp, list);
        lists.add(temp);
        dfs(deep + 1, nums, list, lists);

        // 后递归弹出
        list.remove(list.size() - 1);
        dfs(deep + 1, nums, list, lists);
    }

    /**
     * set为nums的长度
     * 0代表计入、1代表不计入
     * 000 - 111 为全排列
     */
    public static List<List<Integer>> subsetsByBits(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        int set = 1 << nums.length;
        for(int i = 0; i < set; i++) {
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++) {
                // 1 << j指示nums的第i个数（from 1）
                if((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }
            }
            lists.add(list);
        }
        return lists;
    }

}
