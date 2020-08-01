package dp;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromePartitioningII {
    public int minCut(String s) {
        boolean[][] dp = isPalindromeByDp(s);
        int[] f = new int[s.length() + 1];
        f[0] = 0;

        // 初始化前i个字符组成的字串最大划分次数
        for(int i = 1; i <= s.length(); i++) {
            f[i] = i;
        }
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[s.length()] - 1;
    }

    public boolean[][] isPalindromeByDp(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int len = 1; len <= s.length(); len++) {
            for(int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && (len < 3 || dp[i + 1][j - 1]);
            }
        }
        return dp;
    }
}
