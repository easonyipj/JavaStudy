package greedy;

import java.util.LinkedList;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number
 * so that the new number is the smallest possible.
 *
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if(k == num.length()) {
            return "0";
        }
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i < num.length(); i++) {
            int number = num.charAt(i) - '0';

            while(!list.isEmpty() && k > 0 && list.getLast() > number) {
                list.removeLast();
                k--;
            }

            if(number != 0 || !list.isEmpty()) {
                list.addLast(number);
            }
        }

        while(!list.isEmpty() && k > 0) {
            list.removeLast();
            k--;
        }

        if(list.isEmpty()) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i : list) {
            sb.append(String.valueOf(i));
        }

        return sb.toString();
    }
}
