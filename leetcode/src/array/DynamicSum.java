package array;

import java.util.Arrays;

public class DynamicSum {
    public int[] sum(int[] nums) {

        if(nums == null || nums.length == 0) {
            return new int[]{};
        }

        int[] result = new int[nums.length];
        result[0] = nums[0];

        for(int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 2, 10, 1};
        System.out.println(Arrays.toString(new DynamicSum().sum(nums)));
    }
}
