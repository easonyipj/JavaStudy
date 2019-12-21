package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for(int i = 0; i <= s.length() - 10; i++) {
            if(set.contains(s.substring(i, i + 10))) {
                if(!res.contains(s.substring(i, i + 10))) {
                    res.add(s.substring(i, i + 10));
                }
            }else {
                set.add(s.substring(i, i + 10));
            }
        }
        return res;
    }
}
