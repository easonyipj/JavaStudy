package search;

public class BinarySearch {

    /**
     *  recursion
     */
    private boolean searchByRecursion(int[] nums, int right, int left, int target) {
        if(left > right) {
            return false;
        }

        int mid = (left + right) / 2;
        if(target == nums[mid]) {
            return true;
        }
        else if(target < nums[mid]) {
            return searchByRecursion(nums, left, mid - 1, target);
        }
        else {
            return searchByRecursion(nums, mid + 1, right, target);
        }
    }

    /**
     *  loop
     */
    private boolean searchByLoop(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(target == nums[mid]) {
                return true;
            }

            else if(target < nums[mid]) {
                right = mid - 1;
            }

            else  {
                left = mid + 1;
            }
        }

        return false;
    }
}
