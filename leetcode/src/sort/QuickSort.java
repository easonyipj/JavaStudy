package sort;

import utils.Swap;

public class QuickSort {

    public static void quickSort(int[] nums, int left, int right) {
        if(left < right) {
            int cutPoint = partition(nums, left, right);
            quickSort(nums, left, cutPoint - 1);
            quickSort(nums, cutPoint + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int key = nums[left];
        while(left < right) {
            while(left < right && nums[right] >= key) {
                right--;
            }
            Swap.swapInt(nums, right, left);

            while(left < right && nums[left] <= key) {
                left++;
            }
            Swap.swapInt(nums, left, right);
        }
        return left;
    }

}
