package binary;

/**
 * (This problem is an interactive problem.)
 *
 * You may recall that an array A is a mountain array if and only if:
 *
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. 
 * If such an index doesn't exist, return -1.
 *
 * You can't access the mountain array directly.  You may only access the array using a MountainArray interface:
 *
 * MountainArray.get(k) returns the element of the array at index k (0-indexed).
 * MountainArray.length() returns the length of the array.
 * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. 
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 *  
 *
 * Example 1:
 *
 * Input: array = [1,2,3,4,5,3,1], target = 3
 * Output: 2
 * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
 * Example 2:
 *
 * Input: array = [0,1,2,4,2,1], target = 3
 * Output: -1
 * Explanation: 3 does not exist in the array, so we return -1.
 *  
 *
 * Constraints:
 *
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-in-mountain-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindInMountainArray {
    /**
     * 先找到山峰的坐标
     * 然后分别从升序数组和降序数组中找目标值
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int top = findTopIndex(mountainArr);
        int left = findInIcreaseArray(mountainArr, top, target);
        int right = findInDecreaseArray(mountainArr, top, target);
        if(left != -1) {
            return left;
        }
        return right;
    }

    public int findTopIndex(MountainArray mountainArray) {
        int left = 0;
        int right = mountainArray.length() - 1;
        int mid;

        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(mountainArray.get(mid) > mountainArray.get(mid + 1)) {
                right = mid;
            }else {
                left = mid;
            }
        }

        if(mountainArray.get(left) > mountainArray.get(right)) {
            return left;
        }else {
            return right;
        }
    }

    public int findInIcreaseArray(MountainArray mountainArray, int top, int target) {
        int left = 0;
        int right = top;
        int mid;
        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(mountainArray.get(mid) > target) {
                right = mid;
            }else {
                left = mid;
            }
        }

        if(mountainArray.get(left) == target) {
            return left;
        }

        if (mountainArray.get(right) == target) {
            return right;
        }

        return -1;
    }

    public int findInDecreaseArray(MountainArray mountainArray, int top, int target) {
        int left = top;
        int right = mountainArray.length() - 1;
        int mid;
        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(mountainArray.get(mid) > target) {
                left = mid;
            }else {
                right = mid;
            }
        }

        if(mountainArray.get(left) == target) {
            return left;
        }

        if (mountainArray.get(right) == target) {
            return right;
        }

        return -1;
    }

}

class MountainArray {
    public int get(int index){
        return 0;
    }
    public int length() {
        return 0;
    }
}