import sort.*;

public class Main {

    public static void main(String[] args) {

        int[] nums = {4, 5, 3, 6, 2, 5, 1};

        QuickSort.quickSort(nums, 0, nums.length - 1);
        printArray(nums);

    }


    private static void printArray(int [] nums) {
        for(int i : nums) {
            System.out.println(i);
        }
    }
}
