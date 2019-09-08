package meituan;

import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/220361995bb64de08dc47c646ee111ab
 * 来源：牛客网
 *
 * 一条直线上等距离放置了n台路由器。路由器自左向右从1到n编号。
 * 第i台路由器到第j台路由器的距离为| i-j |。  每台路由器都有自己的信号强度，第i台路由器的信号强度为ai。
 * 所有与第i台路由器距离不超过ai的路由器可以收到第i台路由器的信号（注意，每台路由器都能收到自己的信号）。
 * 问一共有多少台路由器可以收到至少k台不同路由器的信号。
 *
 * 输入描述:
 * 输入第一行两个数n , k（1≤n , k≤10^5）
 *
 * 第二行n个数, a1 , a2 , a3……… , an（0≤ai≤10^9）
 *
 *
 * 输出描述:
 * 输出一个数，一共有多少台路由器可以收到至少k台不同路由器的信号。
 * 示例1
 * 输入
 * 4 4
 * 3 3 3 3
 * 输出
 * 4
 */
public class Router {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] r = new int[N];

        int n = 0;
        while(sc.hasNext()) {
            r[n++] = sc.nextInt();
        }

        // range[i][0]表示第i个路由器信号覆盖的左范围
        // range[i][1]表示第i个路由器信号覆盖的右范围 + 1
        int[][] range = new int[N][2];

        for(int i = 0; i < N; i++) {
            range[i][0] = Math.max(0, i - r[i]);
            range[i][1] = Math.min(i + r[i] + 1, N);
        }

        int[] diff = new int[N + 1];
        for(int i = 0; i < N; i++) {
            diff[range[i][0]] += 1;
            diff[range[i][1]] -= 1;
        }

        int sum = 0;
        int count = 0;
        for(int i = 0; i < N; i++) {
            sum += diff[i];
            if(sum >= K) {
                count++;
            }
        }

        System.out.println(count);

    }
}
