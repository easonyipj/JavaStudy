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

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            }

            if(target < nums[mid]) {
                if(nums[left] == nums[mid]) {
                    left = mid + 1;
                    continue;
                }

                if(nums[left] < nums[mid]) {
                    if(nums[left] <= target) {
                        right = mid - 1;
                    }else {
                        left = mid + 1;
                    }
                    continue;
                }

                if(nums[left] > nums[mid]) {
                    right = mid - 1;
                }
            }

            if(target > nums[mid]) {
                if(nums[left] == nums[mid]) {
                    left = mid + 1;
                    continue;
                }

                if(nums[left] < nums[mid]) {
                    left = mid + 1;
                    continue;
                }

                if(nums[left] > nums[mid]) {
                    if(nums[right] >= target) {
                        left = mid + 1;
                    }else {
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}
