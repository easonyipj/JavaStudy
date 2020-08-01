package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 参考题解：https://leetcode-cn.com/problems/palindrome-partitioning/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-7/
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        return divideconquer(s, 0);
    }

    // 解法1 分治
    public List<List<String>> divideconquer(String s, int start) {
        if(start == s.length()) {
            List<String> list = new ArrayList<>();
            List<List<String>> res = new ArrayList<>();
            res.add(list);
            return res;
        }

        List<List<String>> res = new ArrayList<>();
        for(int i = start; i < s.length(); i++) {
            String left = s.substring(start, i + 1);
            if(isPalindrome(left)) {
                for(List<String> sub : divideconquer(s, i + 1)) {
                    sub.add(0, left);
                    res.add(sub);
                }
            }
        }
        return res;
    }

    // 解法2 回溯
    public void backtracking(List<List<String>> res, List<String> temp, String s, int start, boolean[][]dp) {
        if(start == s.length()) {
            res.add(new ArrayList<>(temp));
        }

        for(int i = start; i < s.length(); i++) {
            if(dp[start][i]) {
                temp.add(s.substring(start, i + 1));
                backtracking(res, temp, s, i + 1, dp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 改进：动态规划判断是否回文串
     * 将s[i][j]是否是字串的结果记在二维数组中
     * @param s
     * @return
     */
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
