package sort;

import utils.Swap;

public class HeapSort {
    public static void heapSort(int[] nums) {
        int n = nums.length;
        for(int i = n / 2 - 1; i >= 0; i--) {
            maxHeap(nums, i, n);
        }

        for(int i = n - 1; i >= 0; i--) {
            Swap.swapInt(nums, i, 0);
            maxHeap(nums, 0, i);
        }
    }

    public static void maxHeap(int[] nums, int i, int n) {
        int parent = i;
        int child = parent * 2 + 1;
        while(child < n) {
            if(child + 1 < n && nums[child] < nums[child + 1]) {
                child++;
            }
            if(nums[child] > nums[parent]) {
                Swap.swapInt(nums, child, parent);
                parent = child;
                child = child * 2 + 1;
            }else {
                break;
            }
        }
    }
}
