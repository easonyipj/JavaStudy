package dp;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }

        int[][] dp = new int[s.length()][s.length()];

        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        int l = 0;
        int r = 0;
        // len is the length of the Pal. substring
        for(int len = 2; len <= s.length(); len++) {
            for(int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if((j - i == 1 && s.charAt(i) == s.charAt(j)) ||
                        (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1)) {
                    l = i;
                    r = j;
                    dp[i][j] = 1;
                }
            }
        }

        return s.substring(l, r + 1);
    }
}
