package string;

import java.util.HashMap;

/**
 * Eason
 * 2019/12/21
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 *
 * Example:
 *
 * Input:
 * "abccccdd"
 *
 * Output:
 * 7
 *
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LongestPalindrome {

    public int longestPalindrome(String s) {

        int length = 0;
        int sigle = 0;

        HashMap<Character, Integer> hashMap = new HashMap<>();
        Integer count = null;
        for(int i = 0; i < s.length(); i++) {
            count = hashMap.get(s.charAt(i));
            count = count == null ? 1 : count + 1;
            hashMap.put(s.charAt(i), count);
        }

        for(Character key : hashMap.keySet()) {
            if(hashMap.get(key) % 2 == 0) {
                length += hashMap.get(key);
            } else {
                length += hashMap.get(key) - 1;
                sigle = 1;
            }
        }

        return length + sigle;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("abccccdd"));
    }

}
