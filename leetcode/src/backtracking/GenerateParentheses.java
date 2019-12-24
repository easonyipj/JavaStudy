package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParentheses {
    private List<String> res = new ArrayList<>();
    private int left = 0;
    private int right = 0;

    public List<String> generateParenthesis(int n) {
        back("", n);
        return res;
    }

    private void back(String s, int n) {

        // 左右括号都不能大于n
        if(left > n || right > n) {
            return;
        }

        if(left == n && right == n) {
            res.add(new String(s));
            return;
        }

        // 右括号不能大于左括号
        if(right > left) {
            return;
        }

        // 添加左括号
        s += "(";
        left++;
        back(s, n);
        s = s.substring(0, s.length() - 1);
        left--;

        // 添加右括号
        s += ")";
        right++;
        back(s, n);
        s = s.substring(0, s.length() - 1);
        right--;
    }
}
