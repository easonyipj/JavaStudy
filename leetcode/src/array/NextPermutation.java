package array;

/**
 * Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int index = 0;
        // 第一次循环从尾到头找到连续的非降序数字的位置
        for(int i = nums.length - 1; i > 0; i--) {
            if(nums[i - 1] < nums[i]) {
                index = i - 1;
                break;
            }
        }

        // 第二次循环从尾到头找到替换index的位置
        int replace = index;
        for(int i = nums.length - 1; i >= index; i--) {
            if(nums[i] > nums[index]) {
                replace = i;
                break;
            }
        }

        // 替换index和replace位置的元素
        swap(nums, index, replace);

        if(index > replace) {
            // 逆序index后的数
            reverse(nums, index + 1);
        }else {
            reverse(nums, index);
        }

    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int replace) {
        int start = replace;
        int end = nums.length - 1;
        while(start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
