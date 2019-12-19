package search;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(target == nums[mid]) {
                index = mid;
                break;
            }
            if(target < nums[mid]) {
                if(mid == 0 || target > nums[mid - 1]) {
                    index = mid;
                    break;
                }
                right = mid - 1;
            }
            if(target > nums[mid]) {
                if(mid == nums.length - 1 || target < nums[mid + 1]) {
                    index = mid + 1;
                    break;
                }
                left = mid + 1;
            }
        }
        return index;
    }
}

