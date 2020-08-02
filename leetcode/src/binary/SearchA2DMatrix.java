package binary;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Example 1:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * Example 2:
 *
 * Input:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[m -1][n - 1]) {
            return false;
        }

        // 先找在哪一行
        int row = 0;
        int left = 0;
        int right = m - 1;
        int mid;
        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(matrix[mid][0] > target) {
                right = mid;
            }else {
                left = mid;
            }
        }

        if(matrix[left][0] <= target && matrix[right][0] > target) {
            row = left;
        }else{
            row = right;
        }

        // 找哪一列
        left = 0;
        right = matrix[row].length - 1;
        while(left + 1 < right) {
            mid = left + (right - left) / 2;
            if(matrix[row][mid] > target) {
                right = mid;
            }else {
                left = mid;
            }
        }

        return matrix[row][left] == target || matrix[row][right] == target;
    }
}
