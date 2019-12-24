package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutations {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] visited;


    public List<List<Integer>> permute(int[] nums) {
        visited =  new int[nums.length];
        back(new ArrayList<>(), nums);
        return res;
    }

    private void back(List<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(visited[i] == 0) {
                list.add(nums[i]);
                visited[i] = 1;
                back(list, nums);
                list.remove(list.size() - 1);
                visited[i] = 0;
            }
        }
    }
}
