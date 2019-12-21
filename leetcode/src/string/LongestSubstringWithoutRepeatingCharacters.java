package string;

import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        List<Character> list = new ArrayList<>();
        int end = 0;
        if(s.length() <= 1) {
            return s.length();
        }

        while(end <= s.length() - 1) {
            if(list.contains(s.charAt(end))) {
                list.remove(0);
            }else {
                list.add(s.charAt(end));
                end++;
            }
            maxLength = Math.max(maxLength, list.size());
        }

        return maxLength;
    }

}
