package aiqiyi;

import java.util.Scanner;

/**
 * 排列计数
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 给定一个长度为N-1且只包含0和1的序列A1到AN-1，如果一个1到N的排列P1到PN满足对于1≤i<N，当Ai=0时Pi<Pi+1，
 * 当Ai=1时Pi>Pi+1，则称该排列符合要求，那么有多少个符合要求的排列？
 *
 * 输入
 * 第一行包含一个整数N，1<N≤1000。
 *
 * 第二行包含N-1个空格隔开的整数A1到AN-1，0≤Ai≤1
 *
 * 输出
 * 输出符合要求的排列个数对109+7取模后的结果。
 *
 *
 * 样例输入
 * 4
 * 1 1 0
 * 样例输出
 * 3
 *
 * 提示
 * 样例解释
 *
 * 符合要求的排列为{3 2 1 4}、{4 2 1 3}和{4 3 1 2}。
 */
public class ArrangeCount {
    public static void main() {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String s = sc.nextLine();

        String[] nums = s.split(" ");

        int[] ints = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            ints[i] = Integer.valueOf(nums[i]);
        }




    }
}
