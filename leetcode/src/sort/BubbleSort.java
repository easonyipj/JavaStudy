package sort;

public class BubbleSort {
    public static void bubbleSort(int[] nums) {

        int flag = 0;
        for(int i = nums.length - 1; i > 0; i--) {

            for(int j = 0; j < i; j++) {
                if(nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = 1;
                }
            }

            if(flag == 0) {
                break;
            }
        }
    }
}
