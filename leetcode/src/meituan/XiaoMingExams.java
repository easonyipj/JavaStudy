package meituan;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/a1792d443f914f2b928d2a157cd7900d
 * 来源：牛客网
 *
 * 小明同学在参加一场考试，考试时间2个小时。试卷上一共有n道题目，小明要在规定时间内，完成一定数量的题目。  考试中不限制试题作答顺序，对于 i 第道题目，小明有三种不同的策略可以选择:  (1)直接跳过这道题目，不花费时间，本题得0分。
 *
 * (2)只做一部分题目，花费pi分钟的时间，本题可以得到ai分。  (3)做完整个题目，花费qi分钟的时间，本题可以得到bi分。
 *
 * 小明想知道，他最多能得到多少分。
 *
 *
 * 输入描述:
 * 第一行输入一个n数表示题目的数量。
 *
 * 接下来n行，每行四个数p_i，a_i，q_i，b_i。(1≤n≤100，1≤p_i≤q_i≤120，0≤a_i≤b_i≤1000
 * )。
 *
 *
 * 输出描述:
 * 输出一个数，小明的最高得分。
 * 示例1
 * 输入
 * 4
 * 20 20 100 60
 * 50 30 80 55
 * 100 60 110 88
 * 5 3 10 6
 * 输出
 * 94
 */
public class XiaoMingExams {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] p = new int[N][4];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 4; j++) {
                p[i][j] = sc.nextInt();
            }
        }

        int[] dp = new int[N + 1];
        dp[0] = 0;
        for(int i = 1; i < N; i++) {
            // not solve
            int r1 = 0;
            //  
        }
    }
}
