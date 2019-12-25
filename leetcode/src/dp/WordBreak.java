package dp;

import java.util.List;

/**
 * Eason
 * 2019/12/25
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class WordBreak {

    /**
     * 回溯法
     * 如果前缀匹配上了，继续递归调用后面的字符串，如果后面的字符串不匹配，前缀增加一个字符
     */
    private Boolean[] marks;

    private boolean back(String s, List<String> wordDict, int start) {
        if(start == s.length()) {
            return true;
        }

        // 剪枝操作
        if(marks[start] != null) {
            return marks[start];
        }

        for(int i = start + 1; i <= s.length(); i++) {
            if(wordDict.contains(s.substring(start, i)) && back(s, wordDict, i)) {
                marks[start] = true;
                return true;
            }
        }

        marks[start] = false;
        return false;
    }

    private boolean dp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i; j++) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                if(dp[i]) {
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        marks = new Boolean[s.length()];
        return back(s, wordDict, 0);
    }
}
