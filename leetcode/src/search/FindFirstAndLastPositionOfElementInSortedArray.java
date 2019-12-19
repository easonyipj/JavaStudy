package search;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = searchLeft(nums, target);
        res[1] = searchRight(nums, target);
        return res;
    }

    private int searchLeft(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(target == nums[mid]) {
                if(mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                right = mid - 1;
            }

            else if(target < nums[mid]) {
                right = mid - 1;
            }

            else  {
                left = mid + 1;
            }
        }

        return -1;

    }

    private int searchRight(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(target == nums[mid]) {
                if(mid == nums.length - 1 || nums[mid + 1] > target) {
                    return mid;
                }
                left = mid + 1;
            }

            else if(target < nums[mid]) {
                right = mid - 1;
            }

            else  {
                left = mid + 1;
            }
        }

        return -1;

    }
}
