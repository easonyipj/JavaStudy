package meituan;
// https://www.nowcoder.com/question/next?pid=16516326&qid=362169&tid=26620274

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/744eb5eb60c044e1b05e4dfb5f578dbe
 * 来源：牛客网
 *
 * 给你一个01字符串，定义答案=该串中最长的连续1的长度，现在你有至多K次机会，每次机会可以将串中的某个0改成1，现在问最大的可能答案
 *
 *
 * 输入描述:
 * 输入第一行两个整数N,K，表示字符串长度和机会次数
 *
 * 第二行输入N个整数，表示该字符串的元素
 *
 * ( 1 <= N <= 300000
 * , 0 <= K <= N )
 *
 *
 * 输出描述:
 * 输出一行表示答案
 * 示例1
 * 输入
 * 10 2
 * 1 0 0 1 0 1 0 1 0 1
 * 输出
 * 5
 */
public class MaxCompleteOneString {


    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] n = new int[N];

        for(int i = 0; i < N; i++) {
            n[i] = sc.nextInt();
        }

        // count the quantity of 1
        int count = 0;
        // the max 1
        int res = 0;

        for(int l = 0, r = 0; r < N; r++) {
            if(n[r] == 1) {
                count++;
            }
            while(r - l + 1 - count > K) {
                if(n[l++] == 1) {
                    count--;
                }
            }
            res = Math.max(res, r - l + 1);
        }

        System.out.println(res);
    }
}
