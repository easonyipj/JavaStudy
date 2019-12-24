package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinationsOfAPhoneNumber {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if(digits.length() != 0) {
            backtrack("", digits);
        }

        return res;
    }

    private void backtrack(String letter, String nextDigits) {
        if(nextDigits.length() == 0) {
            res.add(letter);
            return;
        }

        String digit = nextDigits.substring(0, 1);
        String letters = phone.get(digit);
        for(int i = 0; i < letters.length(); i++) {
            String ch = letters.substring(i, i + 1);
            backtrack(letter + ch, nextDigits.substring(1));
        }

    }
}
