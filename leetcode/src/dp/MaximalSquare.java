package dp;

/**
 * Eason
 * 2020/1/18
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int result = 0;

        if(matrix == null || matrix.length == 0) {
            return result;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && matrix[i][j] == '1') {
                    dp[i][j] = 1;
                }else if(j == 0 && matrix[i][j] == '1') {
                    dp[i][j] = 1;
                }else {
                    if(matrix[i][j] == '1') {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j],dp[i - 1][j -1]),dp[i][j - 1]) + 1;
                    }
                }
                result = Math.max(result, dp[i][j]);
            }
        }



        return  result * result;
    }
}
