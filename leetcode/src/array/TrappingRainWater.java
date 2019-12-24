package array;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 题解：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
 */
public class TrappingRainWater {

    /**
     * O(n * m)超时
     */
    public int trap(int[] height) {

        boolean flag = true;
        boolean start = false;
        int count = 0;
        int sum = 0;
        int len = 1;

        while(flag) {
            flag = false;
            start = false;
            count = 0;
            for(int i = 0; i < height.length; i++) {
                if(height[i] >= len) {
                    flag = true;
                    start = true;
                    sum += count;
                    count = 0;
                }

                if(start && (height[i] < len)) {
                    count++;
                }
            }
            len++;
        }

        return sum;
    }

    /**
     * 按列求
     */
    public int trapByCol(int[] height) {
        int sum = 0;

        for(int i = 1; i < height.length - 1; i++) {
            int currCol = height[i];
            int leftMax = 0;
            int rightMax = 0;

            for(int j = 0; j < height.length; j++) {
                if(j < i ) {
                    leftMax = Math.max(leftMax, height[j]);
                }

                if(j > i) {
                    rightMax = Math.max(rightMax, height[j]);
                }
            }

            int min = Math.min(leftMax, rightMax);
            sum += min > currCol ? min - currCol : 0;
        }

        return sum;
    }

    /**
     * 按列求 + DP O(n)
     */
    public int trapByColAndDP(int[] height) {
        int sum = 0;

        int[] maxLeftDp = new int[height.length];
        int[] maxRightDp = new int[height.length];

        // 求左侧最高的列的高度
        for(int i = 1; i < maxLeftDp.length; i++) {
            maxLeftDp[i] = Math.max(maxLeftDp[i - 1], height[i - 1]);
        }

        // 求右侧最高的列的高度
        for(int i = maxRightDp.length - 2; i > 0; i--) {
            maxRightDp[i] = Math.max(maxRightDp[i + 1], height[i + 1]);
        }

        for(int i = 1; i < height.length - 1; i++) {
            int currCol = height[i];

            int min = Math.min(maxLeftDp[i], maxRightDp[i]);
            sum += min > currCol ? min - currCol : 0;
        }

        return sum;
    }

    public int trapByColAndDPAndTwoPoints(int[] height) {
        int sum = 0;

        int[] maxLeftDp = new int[height.length];
        int[] maxRightDp = new int[height.length];

        // 求左侧最高的列的高度
        for(int i = 1; i < maxLeftDp.length; i++) {
            maxLeftDp[i] = Math.max(maxLeftDp[i - 1], height[i - 1]);
        }

        // 求右侧最高的列的高度
        for(int i = maxRightDp.length - 2; i > 0; i--) {
            maxRightDp[i] = Math.max(maxRightDp[i + 1], height[i + 1]);
        }

        for(int i = 1; i < height.length - 1; i++) {
            int currCol = height[i];

            int min = Math.min(maxLeftDp[i], maxRightDp[i]);
            sum += min > currCol ? min - currCol : 0;
        }

        return sum;
    }




    public static void main(String[] args) {
        int[] nums = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new TrappingRainWater().trap(nums));
    }
}
