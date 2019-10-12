package sort;

public class MergeSort {
    public static void mergeSort(int[] nums, int l, int r) {
        if(l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, r, mid);
    }

    public static void merge(int[] nums, int l, int r, int mid) {
        int[] temp = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;

        while(p1 <= mid && p2 <= r) {
            temp[i++] = nums[p1] > nums[p2] ? nums[p2++] : nums[p1++];
        }

        while(p1 <= mid) {
            temp[i++] = nums[p1++];
        }

        while(p2 <= r) {
            temp[i++] = nums[p2++];
        }

        i = 0;
        for(int j = l; j <= r; j++) {
            nums[j] = temp[i++];
        }
    }
}
