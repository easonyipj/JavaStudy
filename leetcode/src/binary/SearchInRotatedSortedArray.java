package binary;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to binary.search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInRotatedSortedArray {

    /**
     * 依次判断mid两侧是否为升序且元素是否在其中
     */
    public int search(int[] nums, int target) {
        if(nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid;

        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }

            // 查看左区间是否为升序
            if(nums[left] < nums[mid]) {
                // 查看元素是否在左区间
                if(nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                }else {
                    left = mid;
                }
            }else {
                // 查看元素是否在右区间
                if(nums[mid + 1] <= target && target <= nums[right]) {
                    left = mid;
                }else {
                    right = mid;
                }
            }
        }

        if(nums[left] == target) {
            return left;
        }

        if(nums[right] == target) {
            return right;
        }

        return -1;
    }
}
