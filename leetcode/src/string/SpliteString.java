package string;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/d9162298cb5a437aad722fccccaae8a7?tpId=37&tqId=21227&tPage=1&rp=&ru=/ta/huawei&qru=/ta/huawei/question-ranking
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 *
 * 输出描述:
 * 输出到长度为8的新字符串数组
 *
 * 示例1
 * 输入
 * abc
 * 123456789
 * 输出
 * abc00000
 * 12345678
 * 90000000
 */
public class SpliteString {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            StringBuffer sb = new StringBuffer();
            if(s.length() < 8) {
                sb.append(s);
                for(int i = 0; i < 8 - s.length(); i++) {
                    sb.append("0");
                }
                System.out.println(sb);
                continue;
            }

            if(s.length() == 8) {
                sb.append(s);
                System.out.println(sb);
                continue;
            }

            int l = s.length();
            int i = 0;
            for(; l > 8; l -= 8, i += 8) {
                System.out.println(s.subSequence(i, i + 8));
            }
            sb.append(s.subSequence(i, i + l));
            for(int j = 0; j < 8 - l; j++) {
                sb.append("0");
            }
            System.out.println(sb);

        }

    }
}
